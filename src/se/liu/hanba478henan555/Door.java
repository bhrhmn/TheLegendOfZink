package se.liu.hanba478henan555;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Game-object Door
 * Acts as a Wall-tile unless player has a Key-object
 */
public class Door extends AbstractObject
{
    public Door(ZinkPanel zp){
	super(zp,ObjectType.DOOR);
	readImage();
    }

    private void readImage() {
	try {
	    image = ImageIO.read(getClass().getResourceAsStream("./objects/door.png"));
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
	Player player = zinkPanel.player;
	if (player.getAmmountOfDoorKeys() > 0){
	    player.removeAmmountOfDoorkeys();
	    zinkPanel.gameObjects.remove(this);
	    zinkPanel.getInventory().showDoorMessage();
	    zinkPanel.sound.playSoundEffect(2);
	    return;
	}
	EntityInput lastKey = player.getCurrentKey();
	switch (lastKey){
	    case UP    -> player.moveEntity(EntityInput.DOWN);
	    case DOWN  -> player.moveEntity(EntityInput.UP);
	    case RIGHT -> player.moveEntity(EntityInput.LEFT);
	    case LEFT  -> player.moveEntity(EntityInput.RIGHT);
	}


    }
}
