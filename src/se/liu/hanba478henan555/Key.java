package se.liu.hanba478henan555;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Key
 */
public class Key extends AbstractObject
{
    public Key(ZinkPanel zp){
	super(zp);
	readImage();
    }

    private void readImage() {
	try {
	    image = ImageIO.read(getClass().getResourceAsStream("./objects/key.png"));
	}catch (IOException e){
	    e.printStackTrace();
	}
    }

    @Override public void setCollisionArea() {
	int size = zinkPanel.getOriginalTileSize();
	this.collisionArea = new Rectangle(size /2 + this.pos.x, size + this.pos.y,
					   size * 2, size * 2);
    }

    @Override public void whenCollided(int i) {
	//TODO detta är cringe vi kan inte bara .remove(this), VARFÖR? bajs
	zinkPanel.gameObjects[i] = null;
    }
}
