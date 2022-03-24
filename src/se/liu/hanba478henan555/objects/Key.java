package se.liu.hanba478henan555.objects;

import se.liu.hanba478henan555.entity.AbstractEntity;
import se.liu.hanba478henan555.entity.EntityType;
import se.liu.hanba478henan555.game_mechanics.ZinkPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Key
 */
public class Key extends AbstractObject
{
    public Key(ZinkPanel zp){
	super(zp, ObjectType.KEY);
    }

    @Override public void readImage() {
	try {
	    image = ImageIO.read(getClass().getResourceAsStream("/images/objectImages/key.png"));
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
	zinkPanel.getScreen().showKeyMessage();
	zinkPanel.getPlayer().addAmmountOfDoorKeys();
	zinkPanel.getSound().playSoundEffect(1);
	zinkPanel.getGameObjects().remove(this);
    }
}
