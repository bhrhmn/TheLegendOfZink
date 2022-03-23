package se.liu.hanba478henan555;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Maincharacter of the game
 * character which user controlls
 */
public class Player extends AbstractEntity
{
    private KeyHandler keyHandler;
    private int tileSize;
    private static final int PLAYER_HEALTH = 3;

    private int attackSpeed = zinkPanel.getFPS()/3;
    private int attackCounter = 0;

    private boolean walkable = true;

    private List<AbstractObject> inventory = new ArrayList<>();
    private int invetorySize = 20;

    public Player(ZinkPanel zp,CollisionHandler cl, Point pos, KeyHandler keyHandler) {
	super(zp,cl, pos, EntityType.PLAYER);
	this.keyHandler = keyHandler;

	this.tileSize = zp.getTileSize();

	setDefaultValues();
	setImages();
	setItems();
    }

    private void setItems(){
	PlayerSword playerSword = new PlayerSword(zinkPanel, ObjectType.PLAYER_SWORD_BAD);
	playerSword.setValues(0, 0);
	addItem(playerSword);
    }

    public void removeAmmountOfDoorkeys(){
	for (AbstractObject object: inventory) {
	    if (object.getGameObject() == ObjectType.KEY) {
		inventory.remove(object);
		return;
	    }
	}
    }

    public void addAmmountOfDoorKeys(){
	Key key = new Key(zinkPanel);
	key.readImage();
	inventory.add(key);
    }

    public int getAmmountOfDoorKeys(){
	int ammountOfDoorKeys = 0;
	for (AbstractObject abstractObject : inventory) {
	    if (abstractObject.getGameObject() == ObjectType.KEY)
		ammountOfDoorKeys += 1;
	}
	return ammountOfDoorKeys;
    }

    public void addItem(AbstractObject object) {
	inventory.add(object);
    }

    @Override public void setDefaultValues() {
	this.collisionArea = new Rectangle();
	collisionArea.width = tileSize*2/3;
	collisionArea.height = tileSize*2/3;

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
	attacking();
	if (!walkable && attackSpeed < attackCounter) {
	    attackCounter++;
	    return;
	}
	attackCounter = 0;
	setCollisionAreaRelativePos();
	if (keyHandler.getKey(EntityInput.ATTACK)){
	    attack();
	}
	if ((keyHandler.getKey(EntityInput.UP) || keyHandler.getKey(EntityInput.DOWN) ||
	    keyHandler.getKey(EntityInput.LEFT) || keyHandler.getKey(EntityInput.RIGHT)) && walkable) {

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
	collisionHandler.objectCollision(this);
	collisionHandler.abstractEntityCollision(this);
    }

    private void attacking() {
	for ( AbstractObject ob:zinkPanel.getGameObjects() ) {
	    ObjectType temp = ob.getGameObject();
	    if(temp == ObjectType.PLAYER_SWORD_BAD ||temp == ObjectType.PLAYER_BOW ||temp == ObjectType.PLAYER_SWORD_GOOD ){
		walkable = false;
		return;
	    }
	    walkable = true;
	}
    }

    private void movePlayerBasedOnInput(EntityInput pi){
	entityInput = pi;
	moveEntity(entityInput,1, speed);
    }

    @Override public void draw(Graphics2D g2) {
	if (damaged) {
	    damageAnimation(g2);
	}
	g2.drawImage(setImageBasedOnDirection(), pos.x, pos.y, tileSize, tileSize ,null);
	setAlphaComposite(g2, 1.0f);
    }

    @Override public void attack() {
	PlayerSword pl = new PlayerSword(zinkPanel,ObjectType.PLAYER_SWORD_BAD);
	pl.setValues(pos.x,pos.y, getEntityInput());
	zinkPanel.getGameObjects().add(pl);
    }

    protected void death() {
	zinkPanel.setIsGameOver(true);
    }

    @Override public void heal() {
	if (health < PLAYER_HEALTH) health++;
    }

    public int getAttackSpeed() {
	return attackSpeed;
    }

    public List<AbstractObject> getInventory() {
	return inventory;
    }
}
