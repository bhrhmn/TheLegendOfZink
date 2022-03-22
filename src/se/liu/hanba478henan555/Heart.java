package se.liu.hanba478henan555;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Heart extends AbstractObject
{
    private BufferedImage imageFull = null, imageEmpty = null;

    public Heart(final ZinkPanel zinkPanel) {
	super(zinkPanel, ObjectType.HEART);
	readImage();
    }

    private void readImage() {
	try {
	    imageFull = ImageIO.read(getClass().getResourceAsStream("./objects/heart_full.png"));
	    imageEmpty = ImageIO.read(getClass().getResourceAsStream("./objects/heart_empty.png"));
	}catch (IOException e){
	    e.printStackTrace();
	}

    }

    public void setFullHeart() {
	image = imageFull;
    }

    public void setEmptyHeart() {
	image = imageEmpty;
    }

    @Override public void whenCollided() {

    }

    @Override public void setCollisionArea() {
	this.collisionArea = new Rectangle(this.pos.x, this.pos.y,
					   zinkPanel.getTileSize()/2, zinkPanel.getTileSize()/2);
    }
}
