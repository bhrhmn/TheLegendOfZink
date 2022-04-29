package se.liu.hanba478henan555.entity.entity_enemy;

import se.liu.hanba478henan555.entity.entity_abstract.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

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
	this.canAttack = false;
    }


    @Override public void setImages() {
	String path = "images/enemyImages/red_enemy/";
	up1    = getImage(path + "en_up_1.png");
	up2    = getImage(path + "en_up_2.png");
	down1  = getImage(path + "en_down_1.png");
	down2  = getImage(path + "en_down_2.png");
	right1 = getImage(path + "en_right_1.png");
	right2 = getImage(path + "en_right_2.png");
	left1  = getImage(path + "en_left_1.png");
	left2  = getImage(path + "en_left_2.png");

    }

}
