package se.liu.hanba478henan555.objects.open;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_abstract.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.game_director.sound_manager.SoundType;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;

import java.awt.*;

/**
 * GameObject Key
 * Used to open GameObject Door
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public class Key extends AbstractObject
{
    public Key(ZinkPanel zp){
	super(zp, ObjectType.KEY, SoundType.PLING);
    }

    @Override public void readImage() {
	image = getImage(PATH + "door_key/key.png");
    }

    @Override public void setCollisionArea() {
	this.collisionArea = new Rectangle(this.pos.x, this.pos.y,
					   zinkPanel.getTileSize(), zinkPanel.getTileSize());
    }


    @Override public void whenCollided(AbstractEntity entity) {
	if (!entity.getEntityType().equals(EntityType.PLAYER))
	    return;
	zinkPanel.getWindowManager().showObjectMessage(this);
	zinkPanel.getPlayer().addAmountOfDoorKeys();
	zinkPanel.getSound().playSoundEffect(soundEffect);
	zinkPanel.getGameObjects().remove(this);
    }
}
