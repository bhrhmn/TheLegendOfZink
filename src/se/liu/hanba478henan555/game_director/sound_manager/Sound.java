package se.liu.hanba478henan555.game_director.sound_manager;

import se.liu.hanba478henan555.LoggingManager;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;

/**
 * Handles audio
 */

public class Sound
{
    private Clip clip = null;
    private URL[] soundURL = new URL[7];
    public Sound(){
	String fs = File.separator;
	soundURL[0] = setURL("audio"+fs+"sounds"+fs+"music.wav");
	soundURL[1] = setURL("audio"+fs+"sounds"+fs+"key.wav");
	soundURL[2] = setURL("audio"+fs+"sounds"+fs+"door.wav");
	soundURL[3] = setURL("audio"+fs+"sounds"+fs+"death.wav");
	soundURL[4] = setURL("audio"+fs+"sounds"+fs+"hit.wav");
	soundURL[5] = setURL("audio"+fs+"sounds"+fs+"swosh_sword.wav");
	soundURL[6] = setURL("audio"+fs+"sounds"+fs+"heart.wav");
    }

    private URL setURL(String filePath){
	return ClassLoader.getSystemResource(filePath);
    }

    public void setClip(int index){
	try {
	    AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[index]);
	    clip = AudioSystem.getClip();
	    clip.open(ais);

	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	    LoggingManager.getLogr().log(Level.SEVERE, "setClip", e);
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
