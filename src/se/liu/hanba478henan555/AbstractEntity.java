package se.liu.hanba478henan555;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Abstract class of Entity
 * Some fields
 */
public abstract class AbstractEntity implements Entity
{

    protected ZinkPanel zinkPanel;
    protected CollisionHandler collisionHandler;
    protected BufferedImage currentImage = null;

    protected Point pos = new Point(0,0);//om något går fel så är den längst upp till vänster
    protected int speed;
    protected int maxHealth, health;

    protected BufferedImage up1 = null,up2 = null,
	    left1 = null,left2 = null,right1 = null,
	    	right2 = null,down1 = null,down2 = null;
    protected EntityInput entityInput = EntityInput.UP;

    protected int spriteCounter;
    protected int spriteFrames;

    protected int moveTick;

    protected Rectangle collisionArea = null;
    protected boolean collision = false;

    protected EntityType type = null;

    protected int ammountOfDamage = 0;
    protected boolean damaged = false;
    protected int damagedCounter = -1;
    protected int damagedFrameCounter = 0;


    protected AbstractEntity(ZinkPanel zp, CollisionHandler cl, Point pos, EntityType et){
	this.zinkPanel = zp;
	this.collisionHandler = cl;
	this.pos.x = pos.x * zinkPanel.getTileSize();
	this.pos.y = pos.y * zinkPanel.getTileSize();
	this.type = et;
    }

    protected BufferedImage setImage(final String s){
	try{
	    return ImageIO.read(getClass().getResourceAsStream(s));
	}catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public EntityInput getEntityInput(){return entityInput;}

    protected BufferedImage changeSprite(BufferedImage b1, BufferedImage b2){
	if (spriteCounter <= spriteFrames)
	    return b2;

	if(spriteCounter >= spriteFrames*2)
	    spriteCounter = 0;

	return b1;
    }

    protected void setCollisionAreaRelativePos() {
	this.collisionArea.x = this.pos.x + zinkPanel.getOriginalTileSize() / 2;
	this.collisionArea.y = this.pos.y + zinkPanel.getOriginalTileSize() / 2;
    }

    @Override public void moveEntity(EntityInput pi, int direction, int ammount){

	if (damaged) {
	    return;
	}
	switch (pi){
	    case UP: {
		changePosition(EntityInput.UP, PointXY.Y, -direction, ammount);
		break;
	    }
	    case DOWN: {
		changePosition(EntityInput.DOWN, PointXY.Y, direction, ammount);
		break;
	    }
	    case RIGHT: {
		changePosition(EntityInput.RIGHT, PointXY.X, direction, ammount);
		break;
	    }
	    case LEFT: {
		changePosition(EntityInput.LEFT, PointXY.X, -direction, ammount);
		break;
	    }
	}
    }

    protected void moveRandom(){
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

    public void knockback(){
	moveEntity(getEntityInput() ,-1, zinkPanel.getTileSize()/2);
    }

    public void changePosition(EntityInput input, PointXY xy, int direction, int ammount) {
	int add = ammount * direction;
	if (xy == PointXY.X) {
	    pos.x += add;
	    collisionArea.x += add;
	    if (collisionHandler.tileCollision(this, input)){
		pos.x -= add;
		collisionArea.x -= add;
	    }
	}
	else if (xy == PointXY.Y) {
	    pos.y += add;
	    collisionArea.y += add;
	    if (collisionHandler.tileCollision(this, input)){
		pos.y -= add;
		collisionArea.y -= add;
	    }
	}
    }

    protected BufferedImage setImageBasedOnDirection() {
	BufferedImage image = null;
	switch (entityInput){
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
	return image;
    }

    protected void shootProjectile() {
	Projectile p = new Projectile(zinkPanel, ObjectType.ENEMY_BOW, getEntityInput());
	p.setValues(pos.x, pos.y, getEntityInput());
	zinkPanel.getGameObjects().add(p);
    }

    @Override public boolean hasCollision(Rectangle rectangle) {
	return collisionArea.intersects(rectangle);
    }

    public int getMaxHealth() {
	return maxHealth;
    }

    public int getHealth() {
	return health;
    }

    public EntityType getType(){return  type;}

    public int getammountOfDamage(){return ammountOfDamage;}

    @Override public void takeDamage(int damage) {
	knockback();
	damaged = true;
	if (damagedCounter >= 0) {
	    return;
	}

	damagedCounter = 0;
	health-= damage;
	zinkPanel.sound.playSoundEffect(4);
	if (health <= 0){
	    death();
	}
    }


    protected void death(){
	zinkPanel.sound.playSoundEffect(3);
	zinkPanel.getEnemyList().remove(this);
	BloodPile bloodPile = new BloodPile(zinkPanel);
	bloodPile.setValues(this.pos.x/ zinkPanel.getTileSize(), this.pos.y/ zinkPanel.getTileSize());
	zinkPanel.getGameObjects().add(bloodPile);
    }

    /**
     * player switches between fully visible to partially visible 3 times
     * @param g2
     */
    protected void damageAnimation(final Graphics2D g2) {
	damagedFrameCounter++;
	int freezeTime = 10;
	if (damagedCounter == 3) {
	    damaged = false;
	    damagedCounter = -1;
	    return;
	}
	if (damagedFrameCounter < freezeTime) {
	    setAlphaComposite(g2, 0.2f);
	}
	else if (damagedFrameCounter >= freezeTime*2) {
	    setAlphaComposite(g2, 1.0f);
	    damagedCounter++;
	    damagedFrameCounter = 0;
	}
    }

    protected void setAlphaComposite(final Graphics2D g2, final float alpha) {
	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }
}
