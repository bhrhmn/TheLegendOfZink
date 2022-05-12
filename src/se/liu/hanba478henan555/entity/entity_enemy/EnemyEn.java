package se.liu.hanba478henan555.entity.entity_enemy;

import se.liu.hanba478henan555.entity.entity_abstract.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;

import java.awt.*;

/**
 * Enemy
 * Does not attack
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public class EnemyEn extends Enemy
{
    private static final int ENEMY_HEALTH = 2;

    public EnemyEn(ZinkPanel zp, Point pos){
	super(zp, pos, EntityType.EN);
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

	this.amountOfDamage = 1;
	this.canAttack = false;
    }




}
