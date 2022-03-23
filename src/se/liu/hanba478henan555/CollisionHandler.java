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
        for (int i = 0; i < zinkPanel.getGameObjects().size(); i++){
            GameObject gameObject = zinkPanel.getGameObjects().get(i);
            if (gameObject != null && gameObject.hasCollision(entity.collisionArea)) {
                gameObject.whenCollided(entity);
            }
        }
   }

   public void abstractEntityCollision(AbstractEntity entity){
       for (int i = 0; i < zinkPanel.getEnemyList().size(); i++){
           AbstractEntity enemy = zinkPanel.getEnemyList().get(i);
           if (enemy != null && enemy.hasCollision(entity.collisionArea) && !enemy.getType().equals(entity.getType()) && enemy.collision) {
               entity.takeDamage(enemy.getammountOfDamage());
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
                return zinkPanel.getRoomManager().tileHasCollision(topRight) || zinkPanel.getRoomManager().tileHasCollision(topLeft);
            }
            case DOWN:{
                return zinkPanel.getRoomManager().tileHasCollision(bottomLeft) || zinkPanel.getRoomManager().tileHasCollision(bottomRight);
            }
            case LEFT:{
                return zinkPanel.getRoomManager().tileHasCollision(bottomLeft) || zinkPanel.getRoomManager().tileHasCollision(topLeft);
            }
            case RIGHT:{
                return zinkPanel.getRoomManager().tileHasCollision(topRight) || zinkPanel.getRoomManager().tileHasCollision(bottomRight);
            }
            default:{return false;}
        }
    }


}
