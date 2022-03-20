package se.liu.hanba478henan555;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Maincharacter of the game
 * character which user controlls
 */
public class Player extends AbstractEntity
{
    private KeyHandler keyHandler;
    private PlayerInput currentKey;
    private int rows,colums,tileSize, originalTileSize;
    private CollisionHandler cl;


    public Player(ZinkPanel zp,KeyHandler keyHandler) {
	this.keyHandler = keyHandler;
	this.currentKey = PlayerInput.UP;
	this.cl = zp.collisionHandler;

	this.tileSize = zp.getTileSize();
	this.originalTileSize = zp.getOriginalTileSize();
	this.colums = zp.getColumns();
	this.rows = zp.getRows();

	setDefaultValues();
	loadPlayerImage();

    }

    private void setDefaultValues() {
	this.pos = new Point(rows*tileSize / 2, colums*tileSize / 2); // sets default position of player to the middle of the screen
	this.collisionArea = new Rectangle();
	collisionArea.width = tileSize*2/3;
	collisionArea.height = tileSize*2/3;

	this.collision = true;
	this.spriteFrames = 10;
	this.speed = 4; // sets speed of player
    }

    private void loadPlayerImage(){
	try {
	    up1 = ImageIO.read(new File("src/se/liu/hanba478henan555/player/boy_up_1.png"));
	    up2 = ImageIO.read(new File("src/se/liu/hanba478henan555/player/boy_up_2.png"));
	    left1 = ImageIO.read(new File("src/se/liu/hanba478henan555/player/boy_left_1.png"));
	    left2 = ImageIO.read(new File("src/se/liu/hanba478henan555/player/boy_left_2.png"));
	    down1 = ImageIO.read(new File("src/se/liu/hanba478henan555/player/boy_down_1.png"));
	    down2 = ImageIO.read(new File("src/se/liu/hanba478henan555/player/boy_down_2.png"));
	    right1 = ImageIO.read(new File("src/se/liu/hanba478henan555/player/boy_right_1.png"));
	    right2 = ImageIO.read(new File("src/se/liu/hanba478henan555/player/boy_right_2.png"));
	}catch (IOException e){
	    e.printStackTrace();
	}
    }

    /**
     * Updates position
     */
    public void update() {
	setCollisionAreaRelativePos();
	if (keyHandler.getKey(PlayerInput.UP) || keyHandler.getKey(PlayerInput.DOWN) ||
	    keyHandler.getKey(PlayerInput.LEFT) || keyHandler.getKey(PlayerInput.RIGHT)) {
	    spriteCounter++;

	    if (keyHandler.getKey(PlayerInput.UP)) {
		movePlayer(PlayerInput.UP);
	    }
	    else if (keyHandler.getKey(PlayerInput.DOWN)) {
		movePlayer(PlayerInput.DOWN);
	    }
	    if (keyHandler.getKey(PlayerInput.LEFT)) {
		movePlayer(PlayerInput.LEFT);
	    }
	    else if (keyHandler.getKey(PlayerInput.RIGHT)) {
		movePlayer(PlayerInput.RIGHT);
	    }
	}
	cl.objectCollision(this);

    }

    /**
     * Moves player and checks for collision
     * @param pi
     */
    private void movePlayer(PlayerInput pi){
	Point currentPos = new Point(pos.x, pos.y);
	switch (pi){
	    case UP: {
		changePosition(PlayerInput.UP, PointXY.Y, -1);
		break;
	    }
	    case DOWN: {
		changePosition(PlayerInput.DOWN, PointXY.Y, 1);
		break;
	    }
	    case RIGHT: {
		changePosition(PlayerInput.RIGHT, PointXY.X, 1);
		break;
	    }
	    case LEFT: {
		changePosition(PlayerInput.LEFT, PointXY.X, -1);
		break;
	    }
	}

    }

    private void changePosition(PlayerInput input, PointXY xy, int direction) {
	currentKey = input;
	int add = speed * direction;
	if (xy == PointXY.X) {
	    pos.x += add;
	    collisionArea.x += add;
	    if (cl.tileCollision(this,input)){
		pos.x -= add;
		collisionArea.x -= add;
	    }
	}
	else if (xy == PointXY.Y) {
	    pos.y += add;
	    collisionArea.y += add;
	    if (cl.tileCollision(this,input)){
		pos.y -= add;
		collisionArea.y -= add;
	    }
	}
    }

    private void setCollisionAreaRelativePos() {
	collisionArea.x = pos.x + originalTileSize / 2;
	collisionArea.y = pos.y + originalTileSize / 2;
    }

    /**
     * Shows the player on screen facing the direction of the last pressed key
     * @param g2
     */
    public void draw(Graphics2D g2) {
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

    private BufferedImage changeSprite(BufferedImage b1, BufferedImage b2){
	if (spriteCounter <= spriteFrames)
	    return b2;

	if(spriteCounter >= spriteFrames*2)
	    spriteCounter = 0;

	return b1;
    }

    @Override public void attack() {

    }

    @Override public void takeDamage() {

    }

    @Override public void heal() {

    }

}
