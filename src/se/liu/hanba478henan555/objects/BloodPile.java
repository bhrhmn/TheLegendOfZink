package se.liu.hanba478henan555.objects;

import se.liu.hanba478henan555.entity.AbstractEntity;
import se.liu.hanba478henan555.game_mechanics.ZinkPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * A GameObject with which nothing happens when collided
 */
public class BloodPile extends AbstractObject
{

    public BloodPile(final ZinkPanel zp) {
	super(zp, ObjectType.BLOOD_PILE);
    }

    @Override public void update() {

    }

    @Override public void whenCollided(final AbstractEntity entity) {

    }

    @Override public void setCollisionArea() {
        Rectangle rectangle = new Rectangle(pos.x, pos.y, zinkPanel.getTileSize(), zinkPanel.getTileSize());
        collisionArea = rectangle;

    }

    @Override public void readImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/enemyImages/dead.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
