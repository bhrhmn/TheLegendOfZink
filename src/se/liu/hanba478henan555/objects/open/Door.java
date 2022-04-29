package se.liu.hanba478henan555.objects.open;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_abstract.EntityInput;
import se.liu.hanba478henan555.entity.entity_abstract.EntityType;
import se.liu.hanba478henan555.entity.entity_player.Player;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.game_director.sound_manager.SoundType;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;


import java.awt.*;


/**
 * Game-object Door
 * Acts as a Wall-tile unless player has a Key-object
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public class Door extends AbstractObject
{
    public Door(ZinkPanel zp){
	super(zp, ObjectType.DOOR, SoundType.DOOR);
    }

    @Override public void readImage() {
	image = getImage(PATH + "door_key/door.png");
    }

    @Override public void setCollisionArea() {
	this.collisionArea = new Rectangle(this.pos.x, this.pos.y,
					   zinkPanel.getTileSize(), zinkPanel.getTileSize());
    }



    @Override public void whenCollided(AbstractEntity entity) {
	if (!entity.getEntityType().equals(EntityType.PLAYER))
	    return;
	Player player = zinkPanel.getPlayer();
	int playerSpeed = player.getSpeed();
	if (player.getAmmountOfDoorKeys() > 0){
	    player.removeAmmountOfDoorkeys();
	    zinkPanel.getGameObjects().remove(this);
	    zinkPanel.getWindowManager().showObjectMessage(this);
	    zinkPanel.getSound().playSoundEffect(soundEffect);
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
