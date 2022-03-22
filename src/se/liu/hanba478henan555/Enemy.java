package se.liu.hanba478henan555;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends AbstractEntity
{
    private static final int ENEMY_HEALTH = 2;

    private Point position;
    private EntityInput ei = EntityInput.UP;
    private int moveTick;

    public Enemy(ZinkPanel zp,CollisionHandler cl, Point pos){
	super(zp,cl);
	this.position = pos;
	setDefaultValues();
	setImages();
    }

    @Override public void setDefaultValues() {
	this.pos.x  = position.x * zinkPanel.getTileSize();
	this.pos.y  = position.y * zinkPanel.getTileSize();

	this.collisionArea = new Rectangle();
	collisionArea.width = zinkPanel.getTileSize();
	collisionArea.height = zinkPanel.getTileSize();

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
	spriteCounter++;
	moveTick++;
	moveRandom();
	moveEntity(ei);
    }

    private void moveRandom(){
	if (moveTick == 120){
	    Random random = new Random();
	    int i = random.nextInt(101);
	    if(i <= 25){
		ei = EntityInput.UP;
	    }
	    else if(i <= 50){
		ei = EntityInput.LEFT;
	    }
	    else if(i <= 75){
		ei = EntityInput.RIGHT;
	    }
	    else {
		ei = EntityInput.DOWN;
	    }
	    moveTick = 0;
	}

    }

    @Override public void draw(Graphics2D g2){
	BufferedImage image = changeSprite(up1, up2);

	switch (ei){
	    case UP: {
		image = changeSprite(up1,up2);
		break;
	    }
	    case DOWN: {
		image = changeSprite(down1,down2);
		break;
	    }
	    case RIGHT: {
		image = changeSprite(right1,right2);
		break;
	    }
	    case LEFT: {
		image = changeSprite(left1,left2);
		break;
	    }
	}
	g2.drawImage(image, pos.x, pos.y, zinkPanel.getTileSize(), zinkPanel.getTileSize() ,null);
    }

    @Override public void attack() {

    }

    @Override public void takeDamage() {

    }

    @Override public void heal() {

    }



}
