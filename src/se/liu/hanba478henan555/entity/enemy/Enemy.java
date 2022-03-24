package se.liu.hanba478henan555.entity.enemy;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_abstract.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.life.BloodPile;

import java.awt.*;

/**
 * Adds functions for entities with EntityType Enemy
 */
public abstract class Enemy extends AbstractEntity
{

    protected EntityType enemyType;

    protected Enemy(final ZinkPanel zp, final Point pos, EntityType enemyType) {
	super(zp, pos, EntityType.ENEMY);
	this.enemyType = enemyType;
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

    public EntityType getEnemyType() {
	return enemyType;
    }
}
