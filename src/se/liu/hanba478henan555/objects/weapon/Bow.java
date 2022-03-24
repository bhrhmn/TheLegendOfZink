package se.liu.hanba478henan555.objects.weapon;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_enum.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * A weapon that allows a Player to shoot projectiles
 */
public class Bow extends AbstractObject
{
    public Bow(ZinkPanel zp){
	super(zp, ObjectType.PLAYER_BOW);
    }

    @Override public void readImage() {
	//TODO: ta bort "/"
	try {
	    String fs = File.separator;
	    image = ImageIO.read(getClass().getResourceAsStream("/images"+fs+"objectImages"+fs+"weapon"+fs+"bow_arrow"+fs+"bow.png"));
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
	zinkPanel.getPlayer().getInventory().add(this);
	zinkPanel.getGameObjects().remove(this);
    }
}
