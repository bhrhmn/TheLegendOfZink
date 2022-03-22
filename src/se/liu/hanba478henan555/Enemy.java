package se.liu.hanba478henan555;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 */
public class Enemy extends AbstractEntity
{
    private static final int ENEMY_HEALTH = 2;

    private int moveTick;

    public Enemy(ZinkPanel zp,CollisionHandler cl, Point pos){
	super(zp,cl, pos);
	setDefaultValues();
	setImages();
    }

    @Override public void setDefaultValues() {

	this.collisionArea = new Rectangle();
	collisionArea.width = zinkPanel.getTileSize()*2/3;
	collisionArea.height = zinkPanel.getTileSize()*2/3;

	this.collision = true;
	this.spriteFrames = 10;
	this.speed = 2; // sets speed of player

	this.maxHealth = ENEMY_HEALTH;
	this.health = maxHealth;

	this.moveTick = 0;
    }


    @Override public void setImages() {
	up1    = setImage("./enemy/en_up_1.png");
	up2    = setImage("./enemy/en_up_2.png");
	down1  = setImage("./enemy/en_up_1.png");
	down2  = setImage("./enemy/en_up_2.png");
	right1 = setImage("./enemy/en_up_1.png");
	right2 = setImage("./enemy/en_up_2.png");
	left1  = setImage("./enemy/en_up_1.png");
	left2  = setImage("./enemy/en_up_2.png");

    }

    @Override public void update() {
	setCollisionAreaRelativePos();
	currentImage = setImageBasedOnDirection();
	spriteCounter++;
	moveTick++;
	moveRandom();
	moveEntity(entityInput);
    }

    private void moveRandom(){
	if (moveTick == zinkPanel.getFPS() *2){
	    Random random = new Random();
	    int i = random.nextInt(4);
	    if(i == 0){
		entityInput = EntityInput.UP;
	    }
	    else if(i == 1){
		entityInput = EntityInput.LEFT;
	    }
	    else if(i == 2){
		entityInput = EntityInput.RIGHT;
	    }
	    else {
		entityInput = EntityInput.DOWN;
	    }
	    moveTick = 0;
	}

    }

    @Override public void draw(Graphics2D g2){
	BufferedImage image = changeSprite(up1, up2);
	g2.drawImage(image, pos.x, pos.y, zinkPanel.getTileSize(), zinkPanel.getTileSize() ,null);
    }

    @Override public void attack() {

    }

    @Override public void takeDamage() {

    }

    @Override public void heal() {

    }



}
