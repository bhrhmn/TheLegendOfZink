package se.liu.hanba478henan555.objects;

import se.liu.hanba478henan555.entity.AbstractEntity;
import se.liu.hanba478henan555.entity.EntityInput;
import se.liu.hanba478henan555.entity.EntityType;
import se.liu.hanba478henan555.entity.Player;
import se.liu.hanba478henan555.game_mechanics.ZinkPanel;

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
	super(zp, ObjectType.DOOR);
    }

    @Override public void readImage() {
	try {
	    image = ImageIO.read(getClass().getResourceAsStream("/images/objectImages/door.png"));
	}catch (IOException e){
	    e.printStackTrace();
	}
    }

    @Override public void setCollisionArea() {
	this.collisionArea = new Rectangle(this.pos.x, this.pos.y,
					   zinkPanel.getTileSize(), zinkPanel.getTileSize());
    }


    @Override public void update() {

    }

    @Override public void whenCollided(AbstractEntity entity) {
	if (!entity.getType().equals(EntityType.PLAYER))
	    return;
	Player player = zinkPanel.getPlayer();
	int playerSpeed = player.getSpeed();
	if (player.getAmmountOfDoorKeys() > 0){
	    player.removeAmmountOfDoorkeys();
	    zinkPanel.getGameObjects().remove(this);
	    zinkPanel.getScreen().showDoorMessage();
	    zinkPanel.getSound().playSoundEffect(2);
	    return;
	}
	EntityInput lastKey = player.getEntityInput();
	switch (lastKey){
	    case UP    -> player.moveEntity(EntityInput.DOWN,1, playerSpeed);
	    case DOWN  -> player.moveEntity(EntityInput.UP,1,playerSpeed);
	    case RIGHT -> player.moveEntity(EntityInput.LEFT,1,playerSpeed);
	    case LEFT  -> player.moveEntity(EntityInput.RIGHT,1,playerSpeed);
	}


    }
}
