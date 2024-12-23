package se.liu.hanba478henan555.objects.weapon;


import se.liu.hanba478henan555.entity.entity_enemy.Enemy;
import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_abstract.EntityInput;
import se.liu.hanba478henan555.entity.entity_abstract.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.game_director.sound_manager.SoundType;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;

import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * An object that can cause damage
 * will disappear after a fixed time
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public class Projectile extends AbstractObject
{

    private EntityInput direction;

    private static final int PROJECTILE_SPEED = 5;
    private static final int ARROW_DAMAGE = 1;
    private static final int SOUND_DISTANCE = 50;
    private static final int COLLISION_AREA_RESCALE_FACTOR = 3;
    private int soundDistance;
    private int lifeSpan;
    private AbstractEntity entity;
    private BufferedImage arrow = null, blobBall = null, fireBall = null;
    private int damage;



    public Projectile(final ZinkPanel zp, final ObjectType go, EntityInput ei, AbstractEntity entity) {
	super(zp, go, SoundType.DOOR);
	this.soundDistance = SOUND_DISTANCE;
	this.direction = ei;
	this.lifeSpan = 0;
	this.entity = entity;
    }

    private void playSound(){
	int x = zinkPanel.getPlayer().getPos().x - pos.x;
	int y = zinkPanel.getPlayer().getPos().y - pos.y;
	if(Math.sqrt(x*x + y*y) <= soundDistance){
	    zinkPanel.sound.playSoundEffect(soundEffect);
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
	setMoreValues(x, y, ei);
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
		    removeProjectile();
		}
	    }
	    case ENEMY_BOW -> {
		if (entity.getEntityType().equals(EntityType.PLAYER)) {
		    entity.takeDamage(damage);
		    removeProjectile();
		}
	    }
	}
    }
    private void removeProjectile(){
	zinkPanel.getGameObjects().remove(this);
    }

    @Override public void setCollisionArea() {
	int size = zinkPanel.getTileSize() / COLLISION_AREA_RESCALE_FACTOR;
	this.collisionArea = new Rectangle(this.pos.x+ size, this.pos.y+size, size, size);
    }


    @Override public void readImage() {
	arrow = getImage(PATH + "weapon/bow_arrow/arrow.png");
	blobBall = getImage("images/enemyImages/enemy_projectiles/blob_projectile.png");
	fireBall = getImage("images/enemyImages/enemy_projectiles/dragon_projectile.png");
	correctImage();
    }

    private void correctImage() {
	EntityType currentEntity = getEntityType();
	switch (currentEntity) {
	    case PLAYER -> image = arrow;
	    case BLOB -> image = blobBall;
	    case DRAGON -> image = fireBall;
	}
    }

    private EntityType getEntityType() {
	if (entity.getEntityType().equals(EntityType.ENEMY)) {
	    Enemy enemy = (Enemy) entity;
	    return enemy.getEnemyType();
	}
	return EntityType.PLAYER;
    }

}
