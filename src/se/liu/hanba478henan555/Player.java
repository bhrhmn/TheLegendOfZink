package se.liu.hanba478henan555;

import java.awt.*;

/**
 * Maincharacter of the game
 * charcter which user controlls
 */
public class Player extends AbstractEntity
{
    private KeyHandler keyHandler;

    public Player(KeyHandler keyHandler) {
	setDefaultValues();
	this.keyHandler = keyHandler;
    }

    private void setDefaultValues() {
	this.speed = 4; // sets speed of player
	this.pos = new Point(100, 100); // sets default position of player
    }

    /**
     * Updates position
     */
    public void update() {
	if (keyHandler.getKey(Direction.UP)) {
	    pos.y -= speed;
	}
	else if (keyHandler.getKey(Direction.DOWN)) {
	    pos.y += speed;
	}
	if (keyHandler.getKey(Direction.LEFT)) {
	    pos.x -= speed;
	}
	else if (keyHandler.getKey(Direction.RIGHT)) {
	    pos.x += speed;
	}
    }

    public void draw(Graphics2D g2) {
	g2.setColor(new Color(73, 11, 171));
	g2.fillRect(pos.x, pos.y, ZinkPanel.TILE_SIZE, ZinkPanel.TILE_SIZE);

	g2.dispose(); //?????
    }

    @Override public void attack() {

    }

    @Override public void takeDamage() {

    }

    @Override public void heal() {

    }

}
