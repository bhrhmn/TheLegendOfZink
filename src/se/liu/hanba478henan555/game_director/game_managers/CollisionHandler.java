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

    public void testObjectCollision(AbstractEntity entity) {
        for (int i = 0; i < zinkPanel.getGameObjects().size(); i++){
            GameObject gameObject = zinkPanel.getGameObjects().get(i);
            if (gameObject != null && gameObject.hasCollision(entity.getCollisionArea())) {
                gameObject.whenCollided(entity);
            }
        }
   }

   public void abstractEntityCollision(AbstractEntity entity){
       for (int i = 0; i < zinkPanel.getEnemies().size(); i++){
           AbstractEntity enemy = zinkPanel.getEnemies().get(i);
           if (enemy != null && enemy.hasCollision(entity.getCollisionArea()) && !enemy.getEntityType().equals(entity.getEntityType()) && enemy.getCollision()) {
               entity.takeDamage(enemy.getAmountOfDamage());
           }
       }
   }

   private boolean hasSideCollision(RoomManager roomManager ,Point a,Point b){
        return roomManager.hasCollision(a) || roomManager.hasCollision(b);
   }
    public boolean isCollidingWithTile(AbstractEntity entity, EntityInput dir){
        RoomManager roomManager = zinkPanel.getRoomManager();
        Rectangle collisionArea = entity.getCollisionArea();

        int columnLeft  = (collisionArea.x)/tileSize;
        int rowTop      = (collisionArea.y)/tileSize;
        int columnRight = (collisionArea.x+collisionArea.width)/tileSize;
        int rowBottom   = (collisionArea.height+collisionArea.y)/tileSize;

        Point topLeft  = new Point(columnLeft , rowTop);
        Point topRight = new Point(columnRight , rowTop);

        Point bottomLeft  = new Point(columnLeft ,  rowBottom);
        Point bottomRight = new Point(columnRight , rowBottom);

        switch (dir){
            case UP:{
                return hasSideCollision(roomManager, topRight, topLeft);
            }
            case DOWN:{
                return hasSideCollision(roomManager, bottomLeft, bottomRight);
            }
            case LEFT:{
                return hasSideCollision(roomManager, bottomLeft, topLeft);
            }
            case RIGHT:{
                return hasSideCollision(roomManager, bottomRight, topRight);
            }
            default:{return false;}
        }
    }


}
