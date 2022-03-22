package se.liu.hanba478henan555;

import java.awt.*;

/**
 * Maincharacter of the game
 * character which user controlls
 */
public class Player extends AbstractEntity
{
    private KeyHandler keyHandler;
    private int tileSize;
    private int ammountOfDoorKeys;
    private static final int PLAYER_HEALTH = 3;

    public Player(ZinkPanel zp,CollisionHandler cl, Point pos, KeyHandler keyHandler) {
	super(zp,cl, pos);
	this.keyHandler = keyHandler;

	this.tileSize = zp.getTileSize();

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

    @Override public void setDefaultValues() {
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
	entityInput = pi;
	moveEntity(entityInput);
    }

    @Override public void draw(Graphics2D g2) {
	g2.drawImage(setImageBasedOnDirection(), pos.x, pos.y, tileSize, tileSize ,null);
    }

    @Override public void attack() {

    }

    @Override public void takeDamage() {
	health--;
	if (health == 0){
	    zinkPanel.setIsGameOver(true);
	}
	
    }

    @Override public void heal() {
	if (health < PLAYER_HEALTH) health++;
    }

    public int getHealth() {
	return health;
    }



}
