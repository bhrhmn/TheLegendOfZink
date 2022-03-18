package se.liu.hanba478henan555;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Key
 */
public class Key extends AbstractObject
{
	public Key(){
	    this.name = "key";
	    readImage();
	}

	private void readImage() {
	    try {
		image = ImageIO.read(getClass().getResourceAsStream("./objects/key.png"));
	    }catch (IOException e){
		e.printStackTrace();
	    }
	}
}
