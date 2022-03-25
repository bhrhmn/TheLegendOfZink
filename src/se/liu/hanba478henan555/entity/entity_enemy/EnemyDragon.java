package se.liu.hanba478henan555.entity.entity_enemy;

import se.liu.hanba478henan555.entity.entity_abstract.EntityInput;
import se.liu.hanba478henan555.entity.entity_abstract.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;

import java.awt.*;
import java.io.File;

import static se.liu.hanba478henan555.game_director.Math.doubleInt;

/**
 * Big boss
 * Can shoot projectiles
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
	damageCalculation(damage);
    }

    @Override public void setImages() {
	String fs = File.separator;
	up1 = setImage("images"+fs+"enemyImages"+fs+"dragon"+fs+"dragon_1.png");
	up2 = setImage("images"+fs+"enemyImages"+fs+"dragon"+fs+"dragon_2.png");
    }

    @Override public void setDefaultValues() {
	setImages();

	this.size = doubleInt(tileSize);

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
	if (moveTick == doubleInt(zinkPanel.getFPS())){
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
	    damageAnimation(g2);
	}
	g2.drawImage(currentImage, pos.x, pos.y, size, size ,null);
	setAlphaComposite(g2, 1.0f);
    }


}
