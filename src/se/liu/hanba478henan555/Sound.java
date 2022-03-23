package se.liu.hanba478henan555;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

/**
 * Handles audio
 */
public class Sound
{
    private Clip clip = null;
    private URL[] soundURL = new URL[6];

    public Sound(){
	soundURL[0] = getClass().getResource("sounds/music.wav");
	soundURL[1] = getClass().getResource("sounds/key.wav");
	soundURL[2] = getClass().getResource("sounds/door.wav");
	soundURL[3] = getClass().getResource("sounds/death.wav");
	soundURL[4] = getClass().getResource("sounds/hit.wav");
	soundURL[5] = getClass().getResource("sounds/swosh_sword.wav");


    }

    public void setClip(int index){
	try {
	    AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[index]);
	    clip = AudioSystem.getClip();
	    clip.open(ais);

	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	    e.printStackTrace();
	}
    }

    public void play(){
	clip.start();
    }

    public void loop(){
	clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
	clip.stop();
    }


}
