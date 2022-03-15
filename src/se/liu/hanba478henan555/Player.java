package se.liu.hanba478henan555;

import java.awt.*;

public class Player implements Character
{
    /**
     * position
     */
    public Point pos;
    /**
     * speed
     */
    public int speed;
    
    private KeyHandler keyHandler;

    public Player(KeyHandler keyHandler) {
	setDefaultValues();
	this.keyHandler = keyHandler;
    }

    private void setDefaultValues() {
	this.speed = 4;
	setDefaultPosition();
    }

    private void setDefaultPosition() {
	this.pos = new Point(100, 100);
    }

    /**
     * Updates position
     */
    public void update() {
	if (keyHandler.getKey("up")) {
	    pos.y -= speed;
	}
	else if (keyHandler.getKey("down")) {
	    pos.y += speed;
	}
	if (keyHandler.getKey("left")) {
	    pos.x -= speed;
	}
	else if (keyHandler.getKey("right")) {
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
