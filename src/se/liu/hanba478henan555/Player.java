package se.liu.hanba478henan555;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Maincharacter of the game
 * character which user controlls
 */
public class Player extends AbstractEntity
{
    private KeyHandler keyHandler;
    private EntityInput currentKey;
    private int rows;
    private int columns;
    private int tileSize;
    private int ammountOfDoorKeys;
    private static final int PLAYER_HEALTH = 3;


    public Player(ZinkPanel zp,CollisionHandler cl, KeyHandler keyHandler) {
	super(zp,cl);
	this.keyHandler = keyHandler;
	this.currentKey = EntityInput.UP;

	this.tileSize = zp.getTileSize();

	this.columns = zp.getColumns();
	this.rows = zp.getRows();


	setDefaultValues();
	setImages();
    }

    public void removeAmmountOfDoorkeys(){
	ammountOfDoorKeys -= 1;
    }

    public void addAmmountOfDoorKeys(){
	ammountOfDoorKeys += 1;
    }

    public int getAmmountOfDoorKeys(){
	return ammountOfDoorKeys;
    }

    public EntityInput getCurrentKey(){
	return currentKey;
    }

    @Override public void setDefaultValues() {
	this.pos = new Point(columns * tileSize / 2, rows * tileSize / 2); // sets default position of player to the middle of the screen
	this.collisionArea = new Rectangle();
	collisionArea.width = tileSize*2/3;
	collisionArea.height = tileSize*2/3;

	this.ammountOfDoorKeys = 0;
	this.collision = true;
	this.spriteFrames = 10;
	this.speed = 4; // sets speed of player

	this.maxHealth = PLAYER_HEALTH;
	this.health = maxHealth;
    }

    @Override public void setImages() {
	up1    = setImage("./player/player_up_1.png");
	up2    = setImage("./player/player_up_2.png");
	left1  = setImage("./player/player_left_1.png");
	left2  = setImage("./player/player_left_2.png");
	right1 = setImage("./player/player_right_1.png");
	right2 = setImage("./player/player_right_2.png");
	down1  = setImage("./player/player_down_1.png");
	down2  = setImage("./player/player_down_2.png");
    }

    /**
     * Updates position
     */
    @Override public void update() {
	setCollisionAreaRelativePos();
	if (keyHandler.getKey(EntityInput.UP) || keyHandler.getKey(EntityInput.DOWN) ||
	    keyHandler.getKey(EntityInput.LEFT) || keyHandler.getKey(EntityInput.RIGHT)) {
	    spriteCounter++;
	    if (keyHandler.getKey(EntityInput.UP)) {
		movePlayerBasedOnInput(EntityInput.UP);
	    }
	    else if (keyHandler.getKey(EntityInput.DOWN)) {
		movePlayerBasedOnInput(EntityInput.DOWN);
	    }
	    else if (keyHandler.getKey(EntityInput.LEFT)) {
		movePlayerBasedOnInput(EntityInput.LEFT);
	    }
	    else if (keyHandler.getKey(EntityInput.RIGHT)) {
		movePlayerBasedOnInput(EntityInput.RIGHT);
	    }
	}
	cl.objectCollision(this);
	cl.abstractEntityCollision(this);
    }

    private void movePlayerBasedOnInput(EntityInput pi){
	currentKey = pi;
	moveEntity(currentKey);
    }

    @Override public void draw(Graphics2D g2) {
	BufferedImage image = null;

	switch (currentKey){
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
	g2.drawImage(image, pos.x, pos.y, tileSize, tileSize ,null);
    }

    @Override public void attack() {

    }

    @Override public void takeDamage() {
	System.out.println("aj!");
    }

    @Override public void heal() {

    }



}
