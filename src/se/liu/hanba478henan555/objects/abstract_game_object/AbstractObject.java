package se.liu.hanba478henan555.objects.abstract_game_object;


import se.liu.hanba478henan555.LoggingManager;
import se.liu.hanba478henan555.entity.entity_abstract.EntityInput;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.game_director.sound_manager.SoundType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Abstract for the game objects.
 * Defines the functions draw, setValues, hasCollided and getters.
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public abstract class AbstractObject implements GameObject
{

    protected BufferedImage image = null;
    protected Point pos = null;
    protected ZinkPanel zinkPanel;

    protected Rectangle collisionArea = null;
    protected ObjectType gameObject;
    protected SoundType soundEffect;

    protected static final String PATH = "images/objectImages/";

    protected AbstractObject(final ZinkPanel zp,final ObjectType go, SoundType soundEffect) {
        this.zinkPanel = zp;
        this.gameObject = go;
        this.soundEffect = soundEffect;
    }

    @Override public void draw(Graphics2D g2){
        g2.drawImage(image, pos.x, pos.y, zinkPanel.getTileSize(), zinkPanel.getTileSize(), null);
    }

    public ObjectType getGameObject() {
        return gameObject;
    }

    public void setCollisionAreaRelativePos(){
        this.collisionArea.x = this.pos.x + zinkPanel.getOriginalTileSize()/2;
        this.collisionArea.y = this.pos.y + zinkPanel.getOriginalTileSize()/2;
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


    protected BufferedImage getImage(final String filePath){
        BufferedImage result, readFile = null;
        try{
            readFile = ImageIO.read(ClassLoader.getSystemClassLoader().getResource(filePath));
        } catch (IOException e){
            LoggingManager.getLogr().log(Level.SEVERE, "AbstractObject", e);
            readFile = new BufferedImage(zinkPanel.getOriginalTileSize(),zinkPanel.getOriginalTileSize(),BufferedImage.TYPE_BYTE_GRAY );
        }finally {
            result = readFile;
        }
        return result;
    }

    /**
     * Depending on ei the image will call on the rotate-function with the corresponding angle
     * @param x
     * @param y
     * @param ei
     */
    protected void setMoreValues(int x, int y, EntityInput ei){
        readImage();
        BufferedImage rotatedImage = image;
        switch (ei) {
            case UP -> {
                this.pos = new Point(x, y-zinkPanel.getTileSize());
                image = rotate(rotatedImage, 0);
            }
            case DOWN -> {this.pos = new Point(x, y+zinkPanel.getTileSize());
                image = rotate(rotatedImage, Math.PI);
            }
            case LEFT -> {
                this.pos = new Point(x-zinkPanel.getTileSize(), y);
                image = rotate(rotatedImage, -Math.PI/2);
            }
            case RIGHT -> {this.pos = new Point(x+zinkPanel.getTileSize(), y);
                image = rotate(rotatedImage, Math.PI / 2);
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
        int width = image.getWidth(), height = image.getHeight();
        int newWidth = (int) Math.floor(width * cos + height * sin), newHeight = (int) Math.floor(height
                                                                                * cos + width * sin);
        int transparency = image.getColorModel().getTransparency();
        BufferedImage result = gc.createCompatibleImage(newWidth, newHeight, transparency);
        Graphics2D g = result.createGraphics();
        g.translate((newWidth - width) / 2, (newHeight - height) / 2);
        g.rotate(angle, width / 2.0f, height / 2.0f);
        g.drawRenderedImage(image, null);
        return result;
    }
}
