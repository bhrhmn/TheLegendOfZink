package se.liu.hanba478henan555.entity.entity_enemy;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_abstract.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.life.BloodPile;

import java.awt.*;

/**
 * Adds functions for entities with EntityType Enemy
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public abstract class Enemy extends AbstractEntity
{

    protected EntityType enemyType;

    protected Enemy(final ZinkPanel zp, final Point pos, EntityType enemyType) {
	super(zp, pos, EntityType.ENEMY);
	this.enemyType = enemyType;
    }

    @Override protected void die(){
	zinkPanel.sound.playSoundEffect(3);
	zinkPanel.getEnemyList().remove(this);
	BloodPile bloodPile = new BloodPile(zinkPanel);
	bloodPile.setValues(this.pos.x/ zinkPanel.getTileSize(), this.pos.y/ zinkPanel.getTileSize());
	zinkPanel.getGameObjects().add(bloodPile);
    }

    protected void changeImage() {
	currentImage = setImageBasedOnDirection();
    }

    public EntityType getEnemyType() {
	return enemyType;
    }
}
