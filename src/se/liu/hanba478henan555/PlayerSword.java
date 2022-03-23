package se.liu.hanba478henan555;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class PlayerSword extends AbstractObject
{

    int lifeSpan = 0;
    protected PlayerSword(final ZinkPanel zp, final ObjectType gameObject) {
        super(zp, gameObject);
    }

    public void setValues(int x, int y, EntityInput ei) {
        this.pos = new Point(x*zinkPanel.getTileSize(), y*zinkPanel.getTileSize());
        setCollisionArea();
        readImage();
    }

    @Override public void readImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("./player/sword_weak.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override public void whenCollided(AbstractEntity entity) {
        if (!entity.getType().equals(EntityType.ENEMY)){return;}
        entity.takeDamage(1);
    }

    @Override public void setCollisionArea() {
        this.collisionArea = new Rectangle(this.pos.x, this.pos.y,
                                           zinkPanel.getTileSize(), zinkPanel.getTileSize());
    }

}
