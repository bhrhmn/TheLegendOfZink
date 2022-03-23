package se.liu.hanba478henan555;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Big boss
 * Can shoot projectiles
 */
public class Dragon extends AbstractEntity
{
    private static final int DRAGON_HEALTH = 10;

    private BufferedImage image1 = null, image2 = null;

    private int size;

    protected Dragon(final ZinkPanel zp, final CollisionHandler cl, final Point pos) {
	super(zp, cl, pos, EntityType.ENEMY);
	setDefaultValues();
    }

    /**
     * TODO: draken kan bara skjuta pil uppåt och vänster??
     */
    @Override public void attack() {
	shootProjectile(ObjectType.ENEMY_BOW, EntityInput.RIGHT);
    }

    @Override public void heal() {

    }

    @Override public void setImages() {
	image1  = setImage("./enemy/dragon_1.png");
	image2  = setImage("./enemy/dragon_2.png");
    }

    @Override public void setDefaultValues() {
	setImages();

	this.size = zinkPanel.getTileSize()*2;

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

	this.entityInput = EntityInput.DOWN;
    }

    @Override public void update() {
	attackRandom();
	setCollisionAreaRelativePos();
	collisionHandler.objectCollision(this);
	currentImage = changeSprite(image1, image2);
	spriteCounter++; moveTick++;
	moveRandom();
	moveEntity(entityInput,1,speed);
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

}
