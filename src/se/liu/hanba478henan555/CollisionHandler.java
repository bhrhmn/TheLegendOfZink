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

    public void objectCollision(AbstractEntity entity) {
        for (int i = 0; i < zinkPanel.gameObjects.size(); i++){
            GameObject gameObject = zinkPanel.gameObjects.get(i);
            if (gameObject != null && gameObject.hasCollision(entity.collisionArea)) {
                gameObject.whenCollided();
            }
        }
   }

   public void abstractEntityCollision(AbstractEntity entity){
       for (int i = 0; i < zinkPanel.enemyList.size(); i++){
           AbstractEntity enemy = zinkPanel.enemyList.get(i);
           if (enemy != null && enemy.hasCollision(entity.collisionArea)) {
               entity.takeDamage();
           }
       }
   }

    public boolean tileCollision(AbstractEntity entity, EntityInput dir){

        Point topLeft  = new Point((entity.collisionArea.x)/tileSize , (entity.collisionArea.y)/tileSize);
        Point topRight = new Point((entity.collisionArea.x+entity.collisionArea.width)/tileSize , (entity.collisionArea.y)/tileSize);

        Point bottomLeft  = new Point((entity.collisionArea.x)/tileSize , (entity.collisionArea.height+entity.collisionArea.y)/tileSize);
        Point bottomRight = new Point((entity.collisionArea.width+entity.collisionArea.x)/tileSize , (entity.collisionArea.height+entity.collisionArea.y)/tileSize);

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
