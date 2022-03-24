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
    private URL[] soundURL = new URL[6];
    public Sound(){
	String fs = File.separator;
	soundURL[0] = setURL("audio"+fs+"sounds"+fs+"music.wav");
	soundURL[1] = setURL("audio"+fs+"sounds"+fs+"key.wav");
	soundURL[2] = setURL("audio"+fs+"sounds"+fs+"door.wav");
	soundURL[3] = setURL("audio"+fs+"sounds"+fs+"death.wav");
	soundURL[4] = setURL("audio"+fs+"sounds"+fs+"hit.wav");
	soundURL[5] = setURL("audio"+fs+"sounds"+fs+"swosh_sword.wav");


    }

    @SuppressWarnings("ProhibitedExceptionCaught")
    private URL setURL(String s){
	try{
	    return (getClass().getResource("/" + s));
	} catch (NullPointerException e){
	    LoggingManager.LOGR.log(Level.SEVERE, "setURL", e);
	}
	return null;
    }

    @SuppressWarnings("ProhibitedExceptionCaught")
    public void setClip(int index){
	try {
	    AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[index]);
	    clip = AudioSystem.getClip();
	    clip.open(ais);

	} catch (NullPointerException | UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	    LoggingManager.LOGR.log(Level.SEVERE, "setClip", e);
	}
    }

    @SuppressWarnings("ProhibitedExceptionCaught")
    public void play(){
	try {
	    clip.start();
	}
	catch (NullPointerException e ) {
	    LoggingManager.LOGR.log(Level.SEVERE, "play clip", e);
	}
    }

    @SuppressWarnings("ProhibitedExceptionCaught")
    public void loop(){

	try {
	    clip.loop(Clip.LOOP_CONTINUOUSLY);
	} catch (NullPointerException e ) {
	    LoggingManager.LOGR.log(Level.SEVERE, "loop clip", e);
	}
    }

    @SuppressWarnings("ProhibitedExceptionCaught")
    public void stop(){
	try {
	    clip.stop();
	} catch (NullPointerException e ) {
	    LoggingManager.LOGR.log(Level.SEVERE, "stop clip", e);
	}
    }


}
