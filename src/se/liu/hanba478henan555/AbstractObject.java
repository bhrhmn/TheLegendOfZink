package se.liu.hanba478henan555;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractObject
{
    protected BufferedImage image = null;
    protected String name = null;
    protected boolean collision = false;
    protected Point pos = null;

    public void draw(Graphics2D g2, ZinkPanel zinkPanel){
        g2.drawImage(image, pos.x * zinkPanel.getTileSize(), pos.y * zinkPanel.getTileSize(), zinkPanel.getTileSize(), zinkPanel.getTileSize(), null);
    }

    public String getName() {
        return name;
    }
}
