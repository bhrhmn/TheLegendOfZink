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
	super(zp,ObjectType.KEY);
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
	this.collisionArea = new Rectangle(this.pos.x, this.pos.y,
					   zinkPanel.getTileSize(), zinkPanel.getTileSize());
    }

    @Override public void whenCollided() {
	zinkPanel.getScreen().showKeyMessage();
	zinkPanel.getPlayer().addAmmountOfDoorKeys();
	zinkPanel.getSound().playSoundEffect(1);
	zinkPanel.getGameObjects().remove(this);

    }
}
