package se.liu.hanba478henan555.game_director.map_manager;
import java.awt.image.BufferedImage;

/**
 * Tile
 * Has fields BufferedIMage and a boolean collision
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
