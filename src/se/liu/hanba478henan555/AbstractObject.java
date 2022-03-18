package se.liu.hanba478henan555;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractObject
{
    public BufferedImage image = null;
    public String name = null;
    public boolean collision = false;
    public Point pos = null;

    public void draw(Graphics2D g2, ZinkPanel zinkPanel){
        g2.drawImage(image, pos.x * zinkPanel.getTileSize(), pos.y * zinkPanel.getTileSize(), zinkPanel.getTileSize(), zinkPanel.getTileSize(), null);
    }
}
