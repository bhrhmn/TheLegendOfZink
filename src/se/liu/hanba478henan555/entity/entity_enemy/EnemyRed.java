package se.liu.hanba478henan555.entity.entity_enemy;

import se.liu.hanba478henan555.entity.entity_abstract.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Enemy
 * Does not attack
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public class EnemyRed extends Enemy
{
    private static final int ENEMY_HEALTH = 2;

    private BufferedImage image = null;

    public EnemyRed(ZinkPanel zp, Point pos){
	super(zp, pos, EntityType.RED);
	setDefaultValues();
	setImages();
    }

    @Override public void setDefaultValues() {
	setCollisionArea();

	this.collision = true;
	this.spriteFrames = 10;
	this.speed = 2;

	this.maxHealth = ENEMY_HEALTH;
	this.health = maxHealth;

	this.moveTick = 0;

	this.ammountOfDamage = 1;
    }


    @Override public void setImages() {
	//TODO: ta bort "/"
	String fs = File.separator;
	up1    = setImage("images"+fs+"enemyImages"+fs+"red_enemy"+fs+"en_up_1.png");
	up2    = setImage("images"+fs+"enemyImages"+fs+"red_enemy"+fs+"en_up_2.png");
	down1  = setImage("images"+fs+"enemyImages"+fs+"red_enemy"+fs+"en_down_1.png");
	down2  = setImage("images"+fs+"enemyImages"+fs+"red_enemy"+fs+"en_down_2.png");
	right1 = setImage("images"+fs+"enemyImages"+fs+"red_enemy"+fs+"en_right_1.png");
	right2 = setImage("images"+fs+"enemyImages"+fs+"red_enemy"+fs+"en_right_2.png");
	left1  = setImage("images"+fs+"enemyImages"+fs+"red_enemy"+fs+"en_left_1.png");
	left2  = setImage("images"+fs+"enemyImages"+fs+"red_enemy"+fs+"en_left_2.png");

    }

    @Override public void update() {
	updateEntity();
	changeImage();
    }

    @Override public void draw(Graphics2D g2){
	if (damaged) {
	    animateDamage(g2);
	}
	g2.drawImage(currentImage, pos.x, pos.y, tileSize, tileSize ,null);
	setAlphaComposite(g2, 1.0f);
    }

    @Override public void attack() {

    }


}
