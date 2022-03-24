package se.liu.hanba478henan555.objects.life;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_enum.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Gameobject
 * Can show a full heart or an empty heart
 */
public class Heart extends AbstractObject
{
    private BufferedImage imageFull = null, imageEmpty = null;

    public Heart(final ZinkPanel zinkPanel) {
	super(zinkPanel, ObjectType.HEART);
	readImage();
    }

    @Override public void readImage() {
	try {
	    imageFull = ImageIO.read(getClass().getResourceAsStream("/images/objectImages/heart/heart_full.png"));
	    imageEmpty = ImageIO.read(getClass().getResourceAsStream("/images/objectImages/heart/heart_empty.png"));
	}catch (IOException e){
	    e.printStackTrace();
	}

    }

    public void setFullHeart() {
	image = imageFull;
    }

    public void setEmptyHeart() {
	image = imageEmpty;
    }

    @Override public void update() {

    }

    @Override public void whenCollided(AbstractEntity entity) {
	if (!entity.getType().equals(EntityType.PLAYER))
	    return;
	entity.heal();
	zinkPanel.getGameObjects().remove(this);
    }

    @Override public void setCollisionArea() {
	this.collisionArea = new Rectangle(this.pos.x, this.pos.y,
					   zinkPanel.getTileSize()/2, zinkPanel.getTileSize()/2);
    }
}
