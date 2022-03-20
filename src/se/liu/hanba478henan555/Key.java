package se.liu.hanba478henan555;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Key
 */
public class Key extends AbstractObject
{
    int index;
    public Key(ZinkPanel zp,int i){
	super(zp);
	this.index = i;
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
					   zinkPanel.getTileSize(), zinkPanel.getTileSize());
    }

    @Override public void whenCollided() {
	//TODO detta är cringe vi kan inte bara .remove(this), VARFÖR? bajs
	zinkPanel.gameObjects[index] = null;
    }
}
