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
    }

    @Override public void readImage() {
	try {
	    image = ImageIO.read(getClass().getResourceAsStream("./objects/door.png"));
	}catch (IOException e){
	    e.printStackTrace();
	}
    }

    @Override public void setCollisionArea() {
	this.collisionArea = new Rectangle(this.pos.x, this.pos.y,
					   zinkPanel.getTileSize(), zinkPanel.getTileSize());
    }


    @Override public void whenCollided(AbstractEntity entity) {
	if (!entity.getType().equals(EntityType.PLAYER))
	    return;
	Player player = zinkPanel.getPlayer();
	if (player.getAmmountOfDoorKeys() > 0){
	    player.removeAmmountOfDoorkeys();
	    zinkPanel.getGameObjects().remove(this);
	    zinkPanel.getScreen().showDoorMessage();
	    zinkPanel.getSound().playSoundEffect(2);
	    return;
	}
	EntityInput lastKey = player.getEntityInput();
	switch (lastKey){
	    case UP    -> player.moveEntity(EntityInput.DOWN,1, player.speed);
	    case DOWN  -> player.moveEntity(EntityInput.UP,1,player.speed);
	    case RIGHT -> player.moveEntity(EntityInput.LEFT,1,player.speed);
	    case LEFT  -> player.moveEntity(EntityInput.RIGHT,1,player.speed);
	}


    }
}
