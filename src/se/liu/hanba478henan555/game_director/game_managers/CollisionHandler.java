package se.liu.hanba478henan555.game_director.game_managers;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_abstract.EntityInput;
import se.liu.hanba478henan555.game_director.map_manager.RoomManager;
import se.liu.hanba478henan555.objects.abstract_game_object.GameObject;

import java.awt.*;

/**
 * Handles collision between entity, objects and tiles.
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public class CollisionHandler
{
    private ZinkPanel zinkPanel;
    private int tileSize;
    public CollisionHandler(ZinkPanel zp){
        this.zinkPanel = zp;
        this.tileSize = zinkPanel.getTileSize();
    }

    public void checkObjectCollision(AbstractEntity entity) {
        for (int i = 0; i < zinkPanel.getGameObjects().size(); i++){
            GameObject gameObject = zinkPanel.getGameObjects().get(i);
            if (gameObject != null && gameObject.hasCollision(entity.getCollisionArea())) {
                gameObject.whenCollided(entity);
            }
        }
   }

   public void abstractEntityCollision(AbstractEntity entity){
       for (int i = 0; i < zinkPanel.getEnemyList().size(); i++){
           AbstractEntity enemy = zinkPanel.getEnemyList().get(i);
           if (enemy != null && enemy.hasCollision(entity.getCollisionArea()) && !enemy.getEntityType().equals(entity.getEntityType()) && enemy.getCollision()) {
               entity.takeDamage(enemy.getamountOfDamage());
           }
       }
   }

    public boolean isCollidingWithTile(AbstractEntity entity, EntityInput dir){
        RoomManager roomManager = zinkPanel.getRoomManager();
        Rectangle collisionArea = entity.getCollisionArea();

        Point topLeft  = new Point((collisionArea.x)/tileSize , (collisionArea.y)/tileSize);
        Point topRight = new Point((collisionArea.x+collisionArea.width)/tileSize , (collisionArea.y)/tileSize);

        Point bottomLeft  = new Point((collisionArea.x)/tileSize , (collisionArea.height+collisionArea.y)/tileSize);
        Point bottomRight = new Point((collisionArea.width+collisionArea.x)/tileSize , (collisionArea.height+collisionArea.y)/tileSize);

        switch (dir){
            case UP:{
                return roomManager.hasCollision(topRight) || roomManager.hasCollision(topLeft);
            }
            case DOWN:{
                return roomManager.hasCollision(bottomLeft) || roomManager.hasCollision(bottomRight);
            }
            case LEFT:{
                return roomManager.hasCollision(bottomLeft) || roomManager.hasCollision(topLeft);
            }
            case RIGHT:{
                return roomManager.hasCollision(topRight) || roomManager.hasCollision(bottomRight);
            }
            default:{return false;}
        }
    }


}
