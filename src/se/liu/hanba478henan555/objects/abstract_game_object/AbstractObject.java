package se.liu.hanba478henan555.objects.abstract_game_object;


import se.liu.hanba478henan555.entity.entity_enum.EntityInput;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Abstract for the game objects.
 * Defines the functions draw, setValues, hasCollided and getters.
 */
public abstract class AbstractObject implements GameObject
{

    protected BufferedImage image = null;
    protected Point pos = null;
    protected ZinkPanel zinkPanel;

    protected Rectangle collisionArea = null;
    protected ObjectType gameObject = null;

    protected AbstractObject(final ZinkPanel zp,final ObjectType go) {
        this.zinkPanel = zp;
        this.gameObject = go;
    }

    @Override public void draw(Graphics2D g2){
        g2.drawImage(image, pos.x, pos.y, zinkPanel.getTileSize(), zinkPanel.getTileSize(), null);
    }

    public ObjectType getGameObject() {
        return gameObject;
    }

    public void setCollisionAreaRelativePos(){
        this.collisionArea.x = this.pos.x + zinkPanel.getOriginalTileSize() / 2;
        this.collisionArea.y = this.pos.y + zinkPanel.getOriginalTileSize() / 2;
    }

    /**
     * Sets startvalues such as position and collisionArea
     * @param x x-coordinate for position
     * @param y y-coordinate for position
     */
    @Override public void setValues(int x, int y) {
        this.pos = new Point(x*zinkPanel.getTileSize(), y*zinkPanel.getTileSize());
        setCollisionArea();
        readImage();
    }


    protected BufferedImage setImage(final String s){
        try{
            return ImageIO.read(getClass().getResourceAsStream(s));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void moreValues(int x, int y, EntityInput ei){
        readImage();
        BufferedImage temp = image;
        switch (ei) {
            case UP -> {
                this.pos = new Point(x, y-zinkPanel.getTileSize());
                image = rotate(temp, 0);
            }
            case DOWN -> {this.pos = new Point(x, y+zinkPanel.getTileSize());
                image = rotate(temp, Math.PI);
            }
            case LEFT -> {
                this.pos = new Point(x-zinkPanel.getTileSize(), y);
                image = rotate(temp, -Math.PI / 2);
            }
            case RIGHT -> {this.pos = new Point(x+zinkPanel.getTileSize(), y);
                image = rotate(temp, Math.PI / 2);
            }

        }
        setCollisionArea();
    }

    public BufferedImage getImage() {
        return image;
    }

    /**
     * Checks if collisionArea intersects with the given Rectangle
     * @param rectangle
     * @return Returns a boolean
     */
    @Override public boolean hasCollision(Rectangle rectangle) {
        return collisionArea.intersects(rectangle);
    }






    /**
     * http://www.java2s.com/Tutorials/Java/Graphics_How_to/Image/Rotate_BufferedImage_Image_.htm
     * @param image
     * @param angle
     * @return
     */
    public BufferedImage rotate(BufferedImage image, double angle) {
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
        g.rotate(angle, w / 2.0f, h / 2.0f);
        g.drawRenderedImage(image, null);
        return result;
    }
}
