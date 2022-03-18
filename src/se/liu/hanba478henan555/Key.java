package se.liu.hanba478henan555;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Key extends AbstractObject
{
	public Key(){
	    this.name = "key";
	    try {
		image = ImageIO.read(new File("src/se/liu/hanba478henan555/objects/key.png"));
	    }catch (IOException e){
		e.printStackTrace();
	    }
	}
}
