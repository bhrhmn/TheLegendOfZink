package se.liu.hanba478henan555.entity;

import se.liu.hanba478henan555.game_mechanics.ZinkPanel;
import se.liu.hanba478henan555.objects.BloodPile;

import java.awt.*;

/**
 * Adds functions for entities with EntityType Enemy
 */
public abstract class Enemy extends AbstractEntity
{

    protected Enemy(final ZinkPanel zp, final Point pos) {
	super(zp, pos, EntityType.ENEMY);
    }

    @Override protected void death(){
	zinkPanel.sound.playSoundEffect(3);
	zinkPanel.getEnemyList().remove(this);
	BloodPile bloodPile = new BloodPile(zinkPanel);
	bloodPile.setValues(this.pos.x/ zinkPanel.getTileSize(), this.pos.y/ zinkPanel.getTileSize());
	zinkPanel.getGameObjects().add(bloodPile);
    }

    protected void changeImage() {
	currentImage = setImageBasedOnDirection();
    }
}
