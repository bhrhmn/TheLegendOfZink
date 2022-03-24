package se.liu.hanba478henan555.objects.open;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_enum.EntityInput;
import se.liu.hanba478henan555.entity.entity_enum.EntityType;
import se.liu.hanba478henan555.entity.player_entity.Player;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;

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
	    image = ImageIO.read(getClass().getResourceAsStream("/images/objectImages/door_key/door.png"));
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
	if (!entity.getEntityType().equals(EntityType.PLAYER))
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
