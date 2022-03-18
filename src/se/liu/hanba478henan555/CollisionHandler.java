package se.liu.hanba478henan555;

import java.awt.*;

/**
 * Handles collision
 */
public class CollisionHandler
{
    private ZinkPanel zinkPanel = null;
    private int tileSize;
    public CollisionHandler(ZinkPanel zp){
        this.zinkPanel = zp;
        this.tileSize = zinkPanel.getTileSize();
    }

    public boolean tileCollision(AbstractEntity entity, PlayerInput dir){

        Point topLeft  = new Point((entity.pos.x+entity.collisionArea.x)/tileSize , (entity.pos.y+entity.collisionArea.y)/tileSize);
        Point topRight = new Point((entity.pos.x+tileSize-entity.collisionArea.x)/tileSize , (entity.pos.y+entity.collisionArea.y)/tileSize);

        Point bottomLeft  = new Point((entity.pos.x+entity.collisionArea.x)/tileSize , (entity.pos.y+tileSize-entity.collisionArea.y)/tileSize);
        Point bottomRight = new Point((entity.pos.x+tileSize-entity.collisionArea.x)/tileSize , (entity.pos.y+tileSize-entity.collisionArea.y)/tileSize);

        switch (dir){
            case UP:{
                return zinkPanel.roomManager.tileHasCollision(topRight) || zinkPanel.roomManager.tileHasCollision(topLeft);
            }
            case DOWN:{
                return zinkPanel.roomManager.tileHasCollision(bottomLeft) || zinkPanel.roomManager.tileHasCollision(bottomRight);
            }
            case LEFT:{
                return zinkPanel.roomManager.tileHasCollision(bottomLeft) || zinkPanel.roomManager.tileHasCollision(topLeft);
            }
            case RIGHT:{
                return zinkPanel.roomManager.tileHasCollision(topRight) || zinkPanel.roomManager.tileHasCollision(bottomRight);
            }
            default:{return false;}
        }
    }


}
