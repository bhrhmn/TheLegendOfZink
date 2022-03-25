package se.liu.hanba478henan555.game_director.game_managers;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_abstract.EntityInput;
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

    public void objectCollision(AbstractEntity entity) {
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
               entity.takeDamage(enemy.getammountOfDamage());
           }
       }
   }

    public boolean tileCollision(AbstractEntity entity, EntityInput dir){
        Point topLeft  = new Point((entity.getCollisionArea().x)/tileSize , (entity.getCollisionArea().y)/tileSize);
        Point topRight = new Point((entity.getCollisionArea().x+entity.getCollisionArea().width)/tileSize , (entity.getCollisionArea().y)/tileSize);

        Point bottomLeft  = new Point((entity.getCollisionArea().x)/tileSize , (entity.getCollisionArea().height+entity.getCollisionArea().y)/tileSize);
        Point bottomRight = new Point((entity.getCollisionArea().width+entity.getCollisionArea().x)/tileSize , (entity.getCollisionArea().height+entity.getCollisionArea().y)/tileSize);

        switch (dir){
            case UP:{
                return zinkPanel.getRoomManager().hasCollision(topRight) || zinkPanel.getRoomManager().hasCollision(topLeft);
            }
            case DOWN:{
                return zinkPanel.getRoomManager().hasCollision(bottomLeft) || zinkPanel.getRoomManager().hasCollision(bottomRight);
            }
            case LEFT:{
                return zinkPanel.getRoomManager().hasCollision(bottomLeft) || zinkPanel.getRoomManager().hasCollision(topLeft);
            }
            case RIGHT:{
                return zinkPanel.getRoomManager().hasCollision(topRight) || zinkPanel.getRoomManager().hasCollision(bottomRight);
            }
            default:{return false;}
        }
    }


}
