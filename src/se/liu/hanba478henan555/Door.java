package se.liu.hanba478henan555;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Door extends AbstractObject
{
    public Door(ZinkPanel zp,int i){
	super(zp,ObjectType.DOOR,i);
	readImage();
    }

    private void readImage() {
	try {
	    image = ImageIO.read(getClass().getResourceAsStream("./objects/door.png"));
	}catch (IOException e){
	    e.printStackTrace();
	}
    }

    @Override public void setCollisionArea() {
	int size = zinkPanel.getOriginalTileSize();
	this.collisionArea = new Rectangle(this.pos.x, this.pos.y,
					   zinkPanel.getTileSize(), zinkPanel.getTileSize());
    }

    @Override public void whenCollided() {
	Player player = zinkPanel.player;
	if (player.getAmmountOfDoorKeys() > 0){
	    player.removeAmmountOfDoorkeys();
	    zinkPanel.gameObjects[index] = null;
	    return;
	}
	PlayerInput lastKey = player.getCurrentKey();
	switch (lastKey){
	    case UP -> player.movePlayer(PlayerInput.DOWN);
	    case DOWN -> player.movePlayer(PlayerInput.UP);
	    case RIGHT -> player.movePlayer(PlayerInput.LEFT);
	    case LEFT -> player.movePlayer(PlayerInput.RIGHT);
	}


    }
}
