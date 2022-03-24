package se.liu.hanba478henan555.entity;

import se.liu.hanba478henan555.game_mechanics.ZinkPanel;
import se.liu.hanba478henan555.objects.ObjectType;

import java.awt.*;
import java.util.Random;

/**
 * Big boss
 * Can shoot projectiles
 */
public class EnemyDragon extends Enemy
{
    private static final int DRAGON_HEALTH = 10;

    private int size;

    public EnemyDragon(final ZinkPanel zp, final Point pos) {
	super(zp, pos);
	setDefaultValues();
    }

    @Override public void attack() {
	shootProjectile(ObjectType.ENEMY_BOW, EntityInput.RIGHT);
    }

    @Override public void takeDamage(int damage) {
	damageCalculation(damage);
    }

    @Override public void setImages() {
	up1 = setImage("/images/enemyImages/dragon_1.png");
	up2 = setImage("/images/enemyImages/dragon_2.png");
    }

    @Override public void setDefaultValues() {
	setImages();

	this.size = tileSize*2;

	this.collisionArea = new Rectangle();
	collisionArea.width = size;
	collisionArea.height = size;

	this.collision = true;
	this.spriteFrames = 10;
	this.speed = 1;
	this.moveTick = 0;

	this.maxHealth = DRAGON_HEALTH;
	this.health = maxHealth;
	this.ammountOfDamage = 2;
	this.attackBound = 6;

	this.entityInput = EntityInput.DOWN;
    }

    @Override public void update() {
	attackRandom(attackBound);
	setCollisionAreaRelativePos();
	collisionHandler.objectCollision(this);
	changeImage();
	spriteCounter++; moveTick++;
	moveRandom();
	moveEntity(entityInput,1,speed);
    }

    @Override protected void changeImage() {
	currentImage = changeSprite(up1, up2);
    }

    @Override protected void moveRandom(){
	if (moveTick == zinkPanel.getFPS() *2){
	    Random random = new Random();
	    int i = random.nextInt(3);
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
	    damageAnimation(g2);
	}
	g2.drawImage(currentImage, pos.x, pos.y, size, size ,null);
	setAlphaComposite(g2, 1.0f);
    }

    @Override public void heal() {

    }

}
