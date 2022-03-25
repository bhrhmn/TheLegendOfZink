package se.liu.hanba478henan555.game_director.map_manager;
import java.awt.image.BufferedImage;

/**
 * Tile
 * Has fields BufferedIMage and a boolean collision
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public class Tile
{
    private BufferedImage image = null;
    private boolean collision = false;

    public BufferedImage getImage() {
        return image;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setImage(final BufferedImage image) {
        this.image = image;
    }

    public void setCollision(final boolean collision) {
        this.collision = collision;
    }
}
