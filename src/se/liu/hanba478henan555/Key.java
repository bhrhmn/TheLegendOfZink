package se.liu.hanba478henan555;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Key
 */
public class Key extends AbstractObject
{
	public Key(final ZinkPanel zp){
	    super(zp);
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

	private void setCollisionArea() {
	    this.collisionArea = new Rectangle();
	    int size = zp.getOriginalTileSize();
	    collisionArea.x =  size / 2;
	    collisionArea.y = size;
	    collisionArea.width = size * 2;
	    collisionArea.height = size * 2;
	}
}
