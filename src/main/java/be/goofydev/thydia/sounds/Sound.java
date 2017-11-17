package be.goofydev.thydia.sounds;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * Created by Jeroen on 1/11/2015.
 */
public class Sound {

    public static float volume_main = 1.0f;
    public static float volume_music = -20.0f;
    public static float volume_player = -10.0f;

    public static Sound music_bg = loadSound("/sound/music/bg.wav", -20f, true);
    public static Sound proj_pop = loadSound("/sound/game/projectiles/pop.wav", -10f, false);

    private static Sound loadSound(String path, float volume,  boolean repeat) {
        Sound sound = new Sound();
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(Sound.class.getResource(path));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            sound.clip = clip;
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        sound.setVolume(volume);
        sound.repeat = repeat;
        return sound;
    }

    private Clip clip;
    private float volume;
    private Boolean repeat;

    public void play() {
        try {
            if(clip != null) {
                new Thread() {
                    @Override
                    public void run() {
                        synchronized (clip) {
                            clip.stop();
                            clip.setFramePosition(0);
                            clip.start();
                            if(repeat) clip.loop(Clip.LOOP_CONTINUOUSLY);
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVolume(final float volume) {
        FloatControl vol = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        vol.setValue(volume);
        this.volume = volume;
    }

    public float getVolume() {
        return volume;
    }

    public void stop() {
        clip.stop();
    }

    public static void updateVolume() {
        proj_pop.setVolume(volume_main * volume_player);
        music_bg.setVolume(volume_main * volume_music);
    }
}
