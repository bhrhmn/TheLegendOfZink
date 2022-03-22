package se.liu.hanba478henan555;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Abstract class of Entity
 * Some fields
 */
public abstract class AbstractEntity implements Entity
{

    protected ZinkPanel zinkPanel;
    protected CollisionHandler cl;

    protected Point pos = new Point(0,0);//om något går fel så är den längst upp till vänster
    protected int speed;
    protected int maxHealth, health;

    protected BufferedImage up1 = null,up2 = null,
	    left1 = null,left2 = null,right1 = null,
	    	right2 = null,down1 = null,down2 = null;

    protected int spriteCounter;
    protected int spriteFrames;

    protected Rectangle collisionArea = null;
    protected boolean collision = false;

    protected AbstractEntity(ZinkPanel zp, CollisionHandler cl){
	this.zinkPanel = zp;
	this.cl = cl;
    }

    protected BufferedImage setImage(final String s){
	try{
	    return ImageIO.read(getClass().getResourceAsStream(s));
	}catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

    protected BufferedImage changeSprite(BufferedImage b1, BufferedImage b2){
	if (spriteCounter <= spriteFrames)
	    return b2;

	if(spriteCounter >= spriteFrames*2)
	    spriteCounter = 0;

	return b1;
    }

    protected void setCollisionAreaRelativePos() {
	this.collisionArea.x = this.pos.x + zinkPanel.getOriginalTileSize() / 2;
	this.collisionArea.y = this.pos.y + zinkPanel.getOriginalTileSize() / 2;
    }

    @Override public void moveEntity(EntityInput pi){
	switch (pi){
	    case UP: {
		changePosition(EntityInput.UP, PointXY.Y, -1);
		break;
	    }
	    case DOWN: {
		changePosition(EntityInput.DOWN, PointXY.Y, 1);
		break;
	    }
	    case RIGHT: {
		changePosition(EntityInput.RIGHT, PointXY.X, 1);
		break;
	    }
	    case LEFT: {
		changePosition(EntityInput.LEFT, PointXY.X, -1);
		break;
	    }
	}
    }

    private void changePosition(EntityInput input, PointXY xy, int direction) {
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
    @Override public boolean hasCollision(Rectangle rectangle) {
	return collisionArea.intersects(rectangle);
    }


}
