package se.liu.hanba478henan555;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Enemy
 * Attacks player
 * When an Enemy dies it creates a BloodPile and puts it on the enemy's position
 */
public class Enemy extends AbstractEntity
{
    private static final int ENEMY_HEALTH = 2;

    private BufferedImage image = null;

    public Enemy(ZinkPanel zp,CollisionHandler cl, Point pos){
	super(zp,cl, pos, EntityType.ENEMY);
	setDefaultValues();
	setImages();
    }

    @Override public void setDefaultValues() {
	this.collisionArea = new Rectangle();
	collisionArea.width = zinkPanel.getTileSize()*2/3;
	collisionArea.height = zinkPanel.getTileSize()*2/3;

	this.collision = true;
	this.spriteFrames = 10;
	this.speed = 2;

	this.maxHealth = ENEMY_HEALTH;
	this.health = maxHealth;

	this.moveTick = 0;

	this.ammountOfDamage = 1;
    }


    @Override public void setImages() {
	up1    = setImage("./enemy/en_up_1.png");
	up2    = setImage("./enemy/en_up_2.png");
	down1  = setImage("./enemy/en_down_1.png");
	down2  = setImage("./enemy/en_down_2.png");
	right1 = setImage("./enemy/en_right_1.png");
	right2 = setImage("./enemy/en_right_2.png");
	left1  = setImage("./enemy/en_left_1.png");
	left2  = setImage("./enemy/en_left_2.png");

    }

    @Override public void update() {
	setCollisionAreaRelativePos();
	collisionHandler.objectCollision(this);
	currentImage = setImageBasedOnDirection();
	spriteCounter++; moveTick++;
	moveRandom();
	moveEntity(entityInput,1,speed);
    }

    @Override public void draw(Graphics2D g2){
	if (damaged) {
	    damageAnimation(g2);
	}
	g2.drawImage(currentImage, pos.x, pos.y, zinkPanel.getTileSize(), zinkPanel.getTileSize() ,null);
	setAlphaComposite(g2, 1.0f);
    }

    @Override public void attack() {

    }


    @Override public void heal() {

    }



}
