package se.liu.hanba478henan555.game_mechanics;

/**
 * PlaySound
 */
public class PlaySound extends Sound
{
    public PlaySound(){
	super();
    }

    public void playMusic(){
	this.setClip(0);
	this.play();
	this.loop();
    }

    public void stopMusic(){
	this.stop();
    }

    public void playSoundEffect(int index){
	this.setClip(index);
	this.play();
    }

}
