package se.liu.hanba478henan555.objects.life;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
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
        //TODO: ta bort "/"
        try {
            String fs = File.separator;
            image = ImageIO.read(getClass().getResourceAsStream("/images"+fs+"enemyImages"+fs+"death"+fs+"dead.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
