package se.liu.hanba478henan555.objects;

import se.liu.hanba478henan555.entity.AbstractEntity;
import se.liu.hanba478henan555.entity.EntityType;
import se.liu.hanba478henan555.game_mechanics.ZinkPanel;

import javax.imageio.ImageIO;
import java.awt.*;
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
	try {
	    image = ImageIO.read(getClass().getResourceAsStream("/images/objectImages/bow.png"));
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
	zinkPanel.getPlayer().getInventory().add(this);
	zinkPanel.getGameObjects().remove(this);
    }
}
