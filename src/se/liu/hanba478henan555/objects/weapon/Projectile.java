package se.liu.hanba478henan555.objects.weapon;


import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_enum.EntityInput;
import se.liu.hanba478henan555.entity.entity_enum.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * An object that can cause damage
 * will disappear after a fixed time
 */
public class Projectile extends AbstractObject
{
    private EntityInput direction = null;
    private static final int PROJ_SPEED = 5;
    private int soundDistance = -1;
    private int lifeSpan;
    public Projectile(final ZinkPanel zp, final ObjectType go, EntityInput ei) {
	super(zp, go);
	this.soundDistance = zp.getTileSize() * zp.getRows();
	this.direction = ei;
	this.lifeSpan = 0;
    }

    private void playSound(){
	int x = zinkPanel.getPlayer().getPos().x - pos.x;
	int y = zinkPanel.getPlayer().getPos().y - pos.y;
	if(Math.sqrt(x*x + y*y) <= soundDistance){
	    zinkPanel.sound.playSoundEffect(2);
	}
    }

    private void changePos(){
	lifeSpan++;
	switch (direction){
	    case UP -> {pos.y-=PROJ_SPEED;}
	    case DOWN -> {pos.y+=PROJ_SPEED;}
	    case RIGHT -> {pos.x+=PROJ_SPEED;}
	    case LEFT -> {pos.x-=PROJ_SPEED;}
	}
    }

    public void update() {
	changePos();
	setCollisionAreaRelativePos();
	if (lifeSpan >= zinkPanel.getFPS()) {
	    zinkPanel.getGameObjects().remove(this);
	}
    }

    public void setValues(int x, int y, EntityInput ei) {
	moreValues(x,y,ei);
	playSound();
    }

    @Override public void whenCollided(final AbstractEntity entity) {
	switch (gameObject){
	    case PLAYER_BOW -> {
		if ((entity.getType() == EntityType.ENEMY)){
		    entity.takeDamage(1);
		    zinkPanel.getGameObjects().remove(this);
		}

	    }
	    case ENEMY_BOW -> {
		if (entity.getType() == EntityType.PLAYER) {
		    entity.takeDamage(2);
		    zinkPanel.getGameObjects().remove(this);
		}
	    }
	}


    }

    @Override public void setCollisionArea() {
	this.collisionArea = new Rectangle(this.pos.x+zinkPanel.getTileSize()/3, this.pos.y+zinkPanel.getTileSize()/3,
					   zinkPanel.getTileSize()/3, zinkPanel.getTileSize()/3);
    }

    @Override public void readImage() {
	try {
	    image = ImageIO.read(getClass().getResourceAsStream("/images/objectImages/weapon/bow_arrow/arrow.png"));
	}catch (IOException e){
	    e.printStackTrace();
	}
    }

}
