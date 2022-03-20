package se.liu.hanba478henan555;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Key
 */
public class Key extends AbstractObject
{
    public Key(ZinkPanel zp,int i){
	super(zp,ObjectType.KEY,i);
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
	this.collisionArea = new Rectangle(this.pos.x, this.pos.y,
					   zinkPanel.getTileSize(), zinkPanel.getTileSize());
    }

    @Override public void whenCollided() {
	zinkPanel.player.addAmmountOfDoorKeys();
	zinkPanel.gameObjects[index] = null; //TODO detta är cringe vi kan inte bara .remove(this), VARFÖR? bajs
    }
}
