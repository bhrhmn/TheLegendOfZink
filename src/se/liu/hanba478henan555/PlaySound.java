package se.liu.hanba478henan555;

public class PlaySound extends Sound
{
    private Sound sound;
    public PlaySound(){
	this.sound = new Sound();
    }

    public void playMusic(){
	sound.setClip(0);
	sound.play();
	sound.loop();
    }

    public void stopMusic(){
	sound.stop();
    }

    public void playSoundEffect(int index){
	sound.setClip(index);
	sound.play();
    }

}
