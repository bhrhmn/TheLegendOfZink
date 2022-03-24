package se.liu.hanba478henan555.entity.player_entity;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_enum.EntityInput;
import se.liu.hanba478henan555.entity.entity_enum.EntityType;
import se.liu.hanba478henan555.game_director.input_manager.KeyHandler;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.open.Key;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;
import se.liu.hanba478henan555.objects.weapon.PlayerSword;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Maincharacter of the game
 * Entity which user controlls
 */
public class Player extends AbstractEntity
{
    private KeyHandler keyHandler;
    private static final int PLAYER_HEALTH = 3;

    private List<AbstractObject> inventory = new ArrayList<>();

    private ObjectType currentWeapoon = null;

    public Player(ZinkPanel zp, Point pos, KeyHandler keyHandler) {
	super(zp, pos, EntityType.PLAYER);
	this.keyHandler = keyHandler;

	setDefaultValues();
	setImages();

    }

    public void selectCurrentWeapon(int i){
	ObjectType temp = inventory.get(i).getGameObject();
	if(!(temp == ObjectType.PLAYER_SWORD_BAD || temp == ObjectType.PLAYER_SWORD_GOOD|| temp == ObjectType.PLAYER_BOW)){
	    return;
	}
	zinkPanel.sound.playSoundEffect(1);
	currentWeapoon = temp;
    }

    public ObjectType getCurrentWeapoon() {
	return currentWeapoon;
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

    @Override public void setDefaultValues() {
	this.collisionArea = new Rectangle();
	collisionArea.width = tileSize*2/3;
	collisionArea.height = tileSize*2/3;

	this.collision = true;
	this.spriteFrames = 10;
	this.speed = 3; // sets speed of player

	this.maxHealth = PLAYER_HEALTH;
	this.health = maxHealth;

	this.attackSpeed = zinkPanel.getFPS()/3;
	this.attackCounter = 0;
	this.canAttack = true;
    }

    @Override public void setImages() {
	up1    = setImage("/images/playerImages/player_up_1.png");
	up2    = setImage("/images/playerImages/player_up_2.png");
	left1  = setImage("/images/playerImages/player_left_1.png");
	left2  = setImage("/images/playerImages/player_left_2.png");
	right1 = setImage("/images/playerImages/player_right_1.png");
	right2 = setImage("/images/playerImages/player_right_2.png");
	down1  = setImage("/images/playerImages/player_down_1.png");
	down2  = setImage("/images/playerImages/player_down_2.png");
    }

    /**
     * Updates position
     */
    @Override public void update() {
	changeCanAttack();
	setCollisionAreaRelativePos();

	if (keyHandler.getKey(EntityInput.ATTACK)){
	    attack();
	}
	if ((keyHandler.getKey(EntityInput.UP) || keyHandler.getKey(EntityInput.DOWN) ||
	     keyHandler.getKey(EntityInput.LEFT) || keyHandler.getKey(EntityInput.RIGHT)) && canAttack) {

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
	if(checkCanAttack()){
	    return;
	}
	attackCounter = 0;
	if (currentWeapoon.equals(ObjectType.PLAYER_BOW)){
	    shootProjectile(ObjectType.PLAYER_BOW, entityInput);
	    return;
	}
	addSword();
    }

    @Override protected boolean checkCanAttack() {
	return !canAttack || currentWeapoon == null;
    }

    private void addSword() {
	PlayerSword pl = new PlayerSword(zinkPanel, currentWeapoon, false);
	pl.setValues(pos.x,pos.y, getEntityInput());
	zinkPanel.getGameObjects().add(pl);
    }

    @Override public void heal() {
	if (health < PLAYER_HEALTH) health++;
    }



    public List<AbstractObject> getInventory() {
	return inventory;
    }

    public int getSpeed() {
	return speed;
    }
}
