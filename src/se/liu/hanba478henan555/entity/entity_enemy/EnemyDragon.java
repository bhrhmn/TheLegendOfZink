package se.liu.hanba478henan555.entity.entity_enemy;

import se.liu.hanba478henan555.entity.entity_abstract.EntityInput;
import se.liu.hanba478henan555.entity.entity_abstract.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;

import java.awt.*;
import java.io.File;

/**
 * Big boss
 * Will shoot projectiles at a random interval
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public class EnemyDragon extends Enemy
{
    private static final int DRAGON_HEALTH = 10;

    private int size;

    public EnemyDragon(final ZinkPanel zp, final Point pos) {
	super(zp, pos, EntityType.DRAGON);
	setDefaultValues();
    }

    @Override public void attack() {
	shootProjectile(ObjectType.ENEMY_BOW, EntityInput.RIGHT);
    }

    @Override public void takeDamage(int damage) {
	calculateDamage(damage);
    }

    @Override public void setImages() {
	String fs = File.separator;
	up1 = setImage("images"+fs+"enemyImages"+fs+"dragon"+fs+"dragon_1.png");
	up2 = setImage("images"+fs+"enemyImages"+fs+"dragon"+fs+"dragon_2.png");
    }

    @Override public void setDefaultValues() {
	setImages();

	this.size = tileSize *2;

	this.collisionArea = new Rectangle();
	collisionArea.width = size;
	collisionArea.height = size;

	this.collision = true;
	this.spriteFrames = 10;
	this.speed = 1;
	this.moveTick = 0;

	this.maxHealth = DRAGON_HEALTH;
	this.health = maxHealth;
	this.ammountOfDamage = zinkPanel.getPlayer().getMaxHealth();
	this.attackBound = 1;

	this.entityInput = EntityInput.DOWN;
    }

    @Override public void update() {
	attackRandom(attackBound);
	setCollisionAreaRelativePos();
	updateEntity();
	changeImage();
    }

    @Override protected void changeImage() {
	currentImage = changeSprite(up1, up2);
    }

    @Override protected void moveRandom(){
	if (moveTick == zinkPanel.getFPS() *2){
	    int i = RANDOM.nextInt(3);
	    if(i == 0){
		entityInput = EntityInput.UP;
	    }
	    else if(i == 1){
		entityInput = EntityInput.DOWN;
	    }
	    moveTick = 0;
	}
    }

    @Override public void draw(final Graphics2D g2) {
	if (damaged) {
	    animateDamage(g2);
	}
	g2.drawImage(currentImage, pos.x, pos.y, size, size ,null);
	setAlphaComposite(g2, 1.0f);
    }


}
