package se.liu.hanba478henan555.objects.weapon;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_abstract.EntityInput;
import se.liu.hanba478henan555.entity.entity_abstract.EntityType;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;


import java.awt.*;
import java.io.File;

import java.util.EnumMap;
import java.util.Map;

/**
 * An object which allows a player to attack using it
 * will cause damage when collision with an enemy happen
 */
public class PlayerSword extends AbstractObject
{

    private int lifeSpan = 0;
    private boolean onGround;

    private Map<ObjectType, Integer> swordDamage = new EnumMap<>(Map.ofEntries(
            Map.entry(ObjectType.PLAYER_SWORD_BAD, 1),
            Map.entry(ObjectType.PLAYER_SWORD_GOOD, 2)));


    public PlayerSword(final ZinkPanel zp, final ObjectType gameObject, boolean onGround) {
        super(zp, gameObject);
        this.onGround = onGround;

        if (!onGround){zinkPanel.sound.playSoundEffect(5);}
    }

    public void setValues(int x, int y,  EntityInput ei) {
        moreValues(x,y,ei);
    }

    @Override public void draw(Graphics2D g2){
        if (!onGround) {
            lifeSpan++;
            if (lifeSpan >= zinkPanel.getPlayer().getAttackSpeed()) {
                zinkPanel.getGameObjects().remove(this);
                return;
            }
        }

        g2.drawImage(image, pos.x, pos.y, zinkPanel.getTileSize(), zinkPanel.getTileSize(), null);
    }

    @Override public void update() {

    }

    @Override public void readImage() {
        //TODO: ta bort "/"
        String fs = File.separator;
        switch (gameObject){
            case PLAYER_SWORD_BAD -> {image = setImage(
                    "images"+fs+"objectImages"+fs+"weapon"+fs+"sword"+fs+"sword_weak.png");}
            case PLAYER_SWORD_GOOD -> {image = setImage(
                    "images"+fs+"objectImages"+fs+"weapon"+fs+"sword"+fs+"sword_strong.png");}
        }

    }

    @Override public void whenCollided(AbstractEntity entity) {
        if (onGround) {
            if (entity.getEntityType().equals(EntityType.PLAYER)) {
                zinkPanel.getGameObjects().remove(this);
                zinkPanel.getPlayer().getInventory().add(this);
            }
            return;
        }
        if (!entity.getEntityType().equals(EntityType.ENEMY)){return;}
        entity.takeDamage(swordDamage.get(gameObject));
    }

    @Override public void setCollisionArea() {
        this.collisionArea = new Rectangle(this.pos.x, this.pos.y,
                                           zinkPanel.getTileSize(), zinkPanel.getTileSize());
    }

}
