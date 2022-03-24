package se.liu.hanba478henan555.entity.entity_abstract;

import se.liu.hanba478henan555.entity.entity_enum.EntityInput;
import se.liu.hanba478henan555.entity.entity_enum.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.CollisionHandler;
import se.liu.hanba478henan555.game_director.input_manager.PointXY;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;
import se.liu.hanba478henan555.objects.weapon.Projectile;

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

    protected int tileSize;

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

    protected EntityType entityType = null;

    protected int ammountOfDamage = 0;
    protected boolean damaged = false;
    protected int damagedCounter = -1;
    protected int damagedFrameCounter = 0;
    protected int attackSpeed;
    protected int attackCounter;
    protected boolean canAttack;
    protected int attackBound;

    protected AbstractEntity(ZinkPanel zp, Point pos, EntityType et){
	this.zinkPanel = zp;
	this.tileSize = zinkPanel.getTileSize();
	this.collisionHandler = zinkPanel.getCollisionHandler();
	this.pos.x = pos.x * tileSize;
	this.pos.y = pos.y * tileSize;
	this.entityType = et;

    }

    protected BufferedImage setImage(final String s){
	try{
	    return ImageIO.read(getClass().getResourceAsStream("/"+s));
	}catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public EntityInput getEntityInput(){return entityInput;}

    public int getAttackSpeed() {
	return attackSpeed;
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
	moveEntity(getEntityInput() ,-1, tileSize/2);
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

    protected BufferedImage changeSprite(BufferedImage b1, BufferedImage b2){
	if (spriteCounter <= spriteFrames)
	    return b2;

	if(spriteCounter >= spriteFrames*2)
	    spriteCounter = 0;

	return b1;
    }

    protected void shootProjectile(ObjectType ob, EntityInput ei) {
	Projectile p = new Projectile(zinkPanel, ob, ei, this);
	p.setValues(pos.x, pos.y, ei);
	zinkPanel.getGameObjects().add(p);
    }

    public int getSpeed() {
	return speed;
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

    public EntityType getEntityType(){return entityType;}

    public int getammountOfDamage(){return ammountOfDamage;}

    protected void attackRandom(int procent) {
	Random random = new Random();
	int randomInt = random.nextInt(100);
	if (randomInt <= procent) {
	    attack();
	}
    }

    protected void changeCanAttack() {
	if (attackCounter <= attackSpeed){
	    canAttack = false;
	}else{
	    canAttack = true;
	}
	attackCounter++;
    }

    protected boolean checkCanAttack() {
	return !canAttack;
    }

    @Override public void takeDamage(int damage) {
	knockback();
	damageCalculation(damage);
    }

    protected void damageCalculation(int damage) {
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


    protected void death() {
	zinkPanel.setIsGameOver(true);
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

    public Point getPos() {
	return pos;
    }

    public Rectangle getCollisionArea() {
	return collisionArea;
    }

    public boolean getCollision() {
	return collision;
    }
}
