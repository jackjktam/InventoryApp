package ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;

/*
Code borrowed and modifies from: http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
 */

// Helper class for sound effects
public class SoundManager {

    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
