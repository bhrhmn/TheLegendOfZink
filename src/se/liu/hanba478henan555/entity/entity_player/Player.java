package se.liu.hanba478henan555.entity.entity_player;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_abstract.EntityInput;
import se.liu.hanba478henan555.entity.entity_abstract.EntityType;
import se.liu.hanba478henan555.game_director.input_manager.KeyHandler;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.open.Key;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;
import se.liu.hanba478henan555.objects.weapon.PlayerSword;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Maincharacter of the game
 * Entity which user controlls
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public class Player extends AbstractEntity
{
    private KeyHandler keyHandler;

    private static final int PLAYER_HEALTH = 3;
    private static final int SPRITE_FRAMES = 10;
    private static final int SPEED = 3;

    private List<AbstractObject> inventory = new ArrayList<>();

    private ObjectType weapon = null;

    public Player(ZinkPanel zp, Point pos, KeyHandler keyHandler) {
	super(zp, pos, EntityType.PLAYER);
	this.keyHandler = keyHandler;

	setDefaultValues();
	setImages();

    }

    public void selectCurrentWeapon(int i){
	ObjectType chooseWeapon = inventory.get(i).getGameObject();
	if(!(chooseWeapon == ObjectType.PLAYER_SWORD_BAD || chooseWeapon == ObjectType.PLAYER_SWORD_GOOD|| chooseWeapon == ObjectType.PLAYER_BOW)){
	    return;
	}
	zinkPanel.sound.playSoundEffect(1);
	weapon = chooseWeapon;
    }

    public ObjectType getWeapon() {
	return weapon;
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
	setCollisionArea();

	this.collision = true;
	this.speed = SPEED;

	this.maxHealth = PLAYER_HEALTH;
	this.health = maxHealth;

	this.attackSpeed = zinkPanel.getFPS()/3;
	this.attackCounter = 0;
	this.canDelayAttack = true;

	this.spriteFrames = SPRITE_FRAMES;
    }

    @Override public void setImages() {
	String fs = File.separator;
	up1    = getImage("images" + fs + "playerImages" + fs + "player_up_1.png");
	up2    = getImage("images" + fs + "playerImages" + fs + "player_up_2.png");
	left1  = getImage("images" + fs + "playerImages" + fs + "player_left_1.png");
	left2  = getImage("images" + fs + "playerImages" + fs + "player_left_2.png");
	right1 = getImage("images" + fs + "playerImages" + fs + "player_right_1.png");
	right2 = getImage("images" + fs + "playerImages" + fs + "player_right_2.png");
	down1  = getImage("images" + fs + "playerImages" + fs + "player_down_1.png");
	down2  = getImage("images" + fs + "playerImages" + fs + "player_down_2.png");
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
	     keyHandler.getKey(EntityInput.LEFT) || keyHandler.getKey(EntityInput.RIGHT)) && canDelayAttack) {

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
	checkCollision();
	changeImage();
    }

    private void checkCollision(){
	collisionHandler.checkObjectCollision(this);
	collisionHandler.abstractEntityCollision(this);
    }

    private void movePlayerBasedOnInput(EntityInput pi){
	entityInput = pi;
	moveEntity(entityInput,1, speed);
    }

    @Override public void attack() {
	if(!isCanAttack()){
	    return;
	}
	attackCounter = 0;
	if (weapon.equals(ObjectType.PLAYER_BOW)){
	    shootProjectile(ObjectType.PLAYER_BOW, entityInput);
	    return;
	}
	addSword();
    }

    protected boolean isCanAttack() {
	return canDelayAttack && weapon != null;
    }

    private void addSword() {
	PlayerSword pl = new PlayerSword(zinkPanel, weapon, false);
	pl.setValues(pos.x,pos.y, getEntityInput());
	zinkPanel.getGameObjects().add(pl);
    }

    public List<AbstractObject> getInventory() {
	return inventory;
    }

}
