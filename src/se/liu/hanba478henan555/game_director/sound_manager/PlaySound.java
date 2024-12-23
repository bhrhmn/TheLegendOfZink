package se.liu.hanba478henan555.game_director.sound_manager;
/**
 * PlaySound
 * implement functions to play and stop audio
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public class PlaySound extends Sound
{
    public void playMusic(){
	this.setClip(SoundType.MUSIC);
	this.play();
	this.loop();
    }

    public void stopMusic(){
	this.stop();
    }

    public void playSoundEffect(SoundType soundType){
	this.setClip(soundType);
	this.play();
    }

}
