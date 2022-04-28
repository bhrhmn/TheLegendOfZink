package se.liu.hanba478henan555.objects.life;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_abstract.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


/**
 * Gameobject
 * Can show a full heart or an empty heart
 * When this has a collision with a Player, the Player will heal
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public class Heart extends AbstractObject
{
    private BufferedImage imageFull = null, imageEmpty = null;

    public Heart(final ZinkPanel zinkPanel) {
	super(zinkPanel, ObjectType.HEART);
	readImage();
    }

    @Override public void readImage() {
	String fs = File.separator;
	imageFull = getImage("images" + fs + "objectImages" + fs + "heart" + fs + "heart_full.png");
	imageEmpty = getImage("images" + fs + "objectImages" + fs + "heart" + fs + "heart_empty.png");

    }

    public void setFullHeart() {
	image = imageFull;
    }

    public void setEmptyHeart() {
	image = imageEmpty;
    }

    @Override public void whenCollided(AbstractEntity entity) {
	if (!entity.getEntityType().equals(EntityType.PLAYER))
	    return;
	entity.heal();
	zinkPanel.getSound().playSoundEffect(6);
	zinkPanel.getWindowManager().showObjectMessage(this);
	zinkPanel.getGameObjects().remove(this);
    }

    @Override public void setCollisionArea() {
	this.collisionArea = new Rectangle(this.pos.x, this.pos.y,
					   zinkPanel.getTileSize(), zinkPanel.getTileSize());
    }
}
