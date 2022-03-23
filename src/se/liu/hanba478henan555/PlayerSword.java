package se.liu.hanba478henan555;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerSword extends AbstractObject
{

    int lifeSpan = 0;
    protected PlayerSword(final ZinkPanel zp, final ObjectType gameObject) {
        super(zp, gameObject);
    }

    public void setValues(int x, int y, EntityInput ei) {
        readImage();
        BufferedImage temp = image;
        switch (ei) {
            case UP -> {
                this.pos = new Point(x, y-zinkPanel.getTileSize());
                image = create(temp, 0);
            }
            case DOWN -> {this.pos = new Point(x, y+zinkPanel.getTileSize());
                image = create(temp, Math.PI);
            }
            case LEFT -> {
                this.pos = new Point(x-zinkPanel.getTileSize(), y);
                image = create(temp, -Math.PI / 2);
            }
            case RIGHT -> {this.pos = new Point(x+zinkPanel.getTileSize(), y);
                image = create(temp, Math.PI / 2);
            }

        }
        setCollisionArea();

    }

    @Override public void draw(Graphics2D g2){
        lifeSpan++;
        if (lifeSpan >= zinkPanel.getPlayer().getAttackSpeed()) {
            zinkPanel.getGameObjects().remove(this);
            return;
        }
        g2.drawImage(image, pos.x, pos.y, zinkPanel.getTileSize(), zinkPanel.getTileSize(), null);
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

    /**
     * http://www.java2s.com/Tutorials/Java/Graphics_How_to/Image/Rotate_BufferedImage_Image_.htm
     * @param image
     * @param angle
     * @return
     */
    private BufferedImage create(BufferedImage image, double angle) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h
                                                                                * cos + w * sin);
        int transparency = image.getColorModel().getTransparency();
        BufferedImage result = gc.createCompatibleImage(neww, newh, transparency);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        return result;
    }

}