package se.liu.hanba478henan555;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Maincharacter of the game
 * charcter which user controlls
 */
public class Player extends AbstractEntity
{
    private KeyHandler keyHandler;
    private Direction currentKey;


    public Player(KeyHandler keyHandler) {
	setDefaultValues();
	getPlayerImage();
	this.keyHandler = keyHandler;
	this.currentKey = Direction.UP;

    }

    private void setDefaultValues() {
	this.spriteFrames = 10;
	this.speed = 4; // sets speed of player
	this.pos = new Point(100, 100); // sets default position of player
    }

    private void getPlayerImage(){
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
	if (keyHandler.getKey(Direction.UP) || keyHandler.getKey(Direction.DOWN) ||
	    keyHandler.getKey(Direction.LEFT) || keyHandler.getKey(Direction.RIGHT)) {

	    spriteCounter++;

	    if (keyHandler.getKey(Direction.UP)) {
		currentKey = Direction.UP;
		pos.y -= speed;
	    }
	    else if (keyHandler.getKey(Direction.DOWN)) {
		currentKey = Direction.DOWN;
		pos.y += speed;
	    }
	    if (keyHandler.getKey(Direction.LEFT)) {
		currentKey = Direction.LEFT;
		pos.x -= speed;
	    }
	    else if (keyHandler.getKey(Direction.RIGHT)) {
		currentKey = Direction.RIGHT;
		pos.x += speed;
	    }
	}

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
	g2.drawImage(image, pos.x, pos.y, ZinkPanel.TILE_SIZE, ZinkPanel.TILE_SIZE ,null);
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
