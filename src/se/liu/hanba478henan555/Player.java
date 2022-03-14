package se.liu.hanba478henan555;

import java.awt.*;

public class Player implements Character
{
    public Point pos;
    public int speed;

    public Player() {
	setDefaultValues();
    }

    private void setDefaultValues() {
	this.speed = 3;
	setDefaultPosition();
    }

    private void setDefaultPosition() {
	this.pos = new Point(100, 100);
    }

    @Override public void attack() {

    }

    @Override public void takeDamage() {

    }

    @Override public void heal() {

    }

}
