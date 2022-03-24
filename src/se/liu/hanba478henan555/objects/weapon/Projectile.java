package se.liu.hanba478henan555.objects.weapon;


import se.liu.hanba478henan555.entity.enemy.Enemy;
import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_abstract.EntityInput;
import se.liu.hanba478henan555.entity.entity_abstract.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


/**
 * An object that can cause damage
 * will disappear after a fixed time
 */
public class Projectile extends AbstractObject
{

    private EntityInput direction = null;

    private static final int PROJECTILE_SPEED = 5;
    private static final int ARROW_DAMAGE = 1;

    private int soundDistance;
    private int lifeSpan;
    private AbstractEntity entity;
    private BufferedImage arrow = null, blobBall = null, fireBall = null;
    private int damage;



    public Projectile(final ZinkPanel zp, final ObjectType go, EntityInput ei, AbstractEntity entity) {
	super(zp, go);
	this.soundDistance = zp.getTileSize() * zp.getRows();
	this.direction = ei;
	this.lifeSpan = 0;
	this.entity = entity;
    }

    private void playSound(){
	int x = zinkPanel.getPlayer().getPos().x - pos.x;
	int y = zinkPanel.getPlayer().getPos().y - pos.y;
	if(Math.sqrt(x*x + y*y) <= soundDistance){
	    zinkPanel.sound.playSoundEffect(2);
	}
    }

    private void changePos(){
	lifeSpan++;
	switch (direction){
	    case UP -> {pos.y-= PROJECTILE_SPEED;}
	    case DOWN -> {pos.y+= PROJECTILE_SPEED;}
	    case RIGHT -> {pos.x+= PROJECTILE_SPEED;}
	    case LEFT -> {pos.x-= PROJECTILE_SPEED;}
	}
    }

    public void update() {
	changePos();
	setCollisionAreaRelativePos();
	if (lifeSpan >= zinkPanel.getFPS()) {
	    zinkPanel.getGameObjects().remove(this);
	}
    }

    public void setValues(int x, int y, EntityInput ei) {
	moreValues(x,y,ei);
	setDamage();
	playSound();
    }

    private void setDamage() {
	damage = ARROW_DAMAGE;
	if (getEntityType().equals(EntityType.DRAGON)) damage++;
    }

    @Override public void whenCollided(final AbstractEntity entity) {
	switch (gameObject){
	    case PLAYER_BOW -> {
		if (entity.getEntityType().equals(EntityType.ENEMY)){
		    entity.takeDamage(ARROW_DAMAGE);
		    zinkPanel.getGameObjects().remove(this);
		}

	    }
	    case ENEMY_BOW -> {
		if (entity.getEntityType().equals(EntityType.PLAYER)) {
		    entity.takeDamage(damage);
		    zinkPanel.getGameObjects().remove(this);
		}
	    }
	}


    }

    @Override public void setCollisionArea() {
	this.collisionArea = new Rectangle(this.pos.x+zinkPanel.getTileSize()/3, this.pos.y+zinkPanel.getTileSize()/3,
					   zinkPanel.getTileSize()/3, zinkPanel.getTileSize()/3);
    }


    @Override public void readImage() {
	String fs = File.separator;
	arrow = setImage("images"+fs+"objectImages"+fs+"weapon"+fs+"bow_arrow"+fs+"arrow.png");
	blobBall = setImage("images"+fs+"enemyImages"+fs+"enemy_projectiles"+fs+"blob_projectile.png");
	fireBall = setImage("images"+fs+"enemyImages"+fs+"enemy_projectiles"+fs+"dragon_projectile.png");
	correctImage();
    }

    private void correctImage() {
	switch (getEntityType()) {
	    case PLAYER -> image = arrow;
	    case BLOB -> image = blobBall;
	    case DRAGON -> image = fireBall;
	}
    }

    private EntityType getEntityType() {
	if (entity.getEntityType() == EntityType.ENEMY) {
	    Enemy enemy = (Enemy) entity;
	    return enemy.getEnemyType();
	}
	return EntityType.PLAYER;
    }

}
