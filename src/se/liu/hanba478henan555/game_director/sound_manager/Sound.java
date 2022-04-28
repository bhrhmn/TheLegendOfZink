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
import java.util.Map;
import java.util.logging.Level;

/**
 * Handles audio
 * Reads audiofiles into an URL-array
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */

public class Sound
{
    private Clip clip = null;
    private URL[] soundURL = new URL[7];
    private Map<SoundType, Integer> soundMap = Map.ofEntries(
	    Map.entry(SoundType.MUSIC, 0),
	    Map.entry(SoundType.KEY, 1),
	    Map.entry(SoundType.DOOR, 2),
	    Map.entry(SoundType.DEATH, 3),
	    Map.entry(SoundType.HIT, 4),
	    Map.entry(SoundType.SWORD, 5),
	    Map.entry(SoundType.HEART, 6)
    );

    public Sound(){
	String fs = File.separator;
	//Backgroundmusic
	soundURL[SoundType.MUSIC.ordinal()] = setURL("music.wav");
	//Key sound
	soundURL[SoundType.KEY.ordinal()] = setURL("key.wav");
	//Door sound
	soundURL[SoundType.DOOR.ordinal()] = setURL("door.wav");
	//Enemy dying sound
	soundURL[SoundType.DEATH.ordinal()] = setURL("death.wav");
	//Entity taking damage sound
	soundURL[SoundType.HIT.ordinal()] = setURL("hit.wav");
	//PlayerSword sound
	soundURL[SoundType.SWORD.ordinal()] = setURL("swosh_sword.wav");
	//Heart sound
	soundURL[SoundType.HEART.ordinal()] = setURL("heart.wav");
    }

    private URL setURL(String filePath){
	return ClassLoader.getSystemResource("audio/sounds/"+filePath);
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
