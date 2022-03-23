package se.liu.hanba478henan555;


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
    private static final int PROJ_SPEED = 10;
    private int lifeSpan;
    protected Projectile(final ZinkPanel zp, final ObjectType go, EntityInput ei) {
	super(zp, go);
	this.direction = ei;
	zinkPanel.sound.playSoundEffect(2);
	this.lifeSpan = 0;
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

    @Override public void update() {
	changePos();
	setCollisionAreaRelativePos();
	if (lifeSpan >= zinkPanel.getFPS()) {
	    zinkPanel.getGameObjects().remove(this);
	}
    }

    public void setValues(int x, int y, EntityInput ei) {
	moreValues(x,y,ei);
    }

    @Override public void whenCollided(final AbstractEntity entity) {
	switch (gameObject){
	    case PLAYER_BOW -> {
		if ((entity.getType() == EntityType.ENEMY))
		    entity.takeDamage(1);
	    }
	    case ENEMY_BOW -> {
		if (entity.getType() == EntityType.PLAYER)
		    entity.takeDamage(2);
	    }
	}
	zinkPanel.getGameObjects().remove(this);

    }

    @Override public void setCollisionArea() {
	this.collisionArea = new Rectangle(this.pos.x+zinkPanel.getTileSize()/3, this.pos.y+zinkPanel.getTileSize()/3,
					   zinkPanel.getTileSize()/3, zinkPanel.getTileSize()/3);
    }

    @Override public void readImage() {
	try {
	    image = ImageIO.read(getClass().getResourceAsStream("./objects/arrow.png"));
	}catch (IOException e){
	    e.printStackTrace();
	}
    }

}
