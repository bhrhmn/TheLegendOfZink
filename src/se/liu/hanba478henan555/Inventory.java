package se.liu.hanba478henan555;

import java.awt.*;

/**
 * Inventory-class handles showing the Player's inventory on screen
 */
public class Inventory
{
    private boolean showingMessage;
    private String message;
    private Font font;
    private ZinkPanel zinkPanel;

    public Inventory(ZinkPanel zp) {
	this.zinkPanel = zp;
	this.showingMessage = true;
	this.message = "hej";
	this.font = new Font("Comic Sans MS", Font.BOLD, zinkPanel.getTileSize());
    }

    public void draw(Graphics2D g2) {
	g2.setFont(font);
	g2.setColor(Color.MAGENTA);
	if (showingMessage) {
	    int screensizeX = zinkPanel.getScreenStartPoint().x * zinkPanel.getTileSize() * zinkPanel.getColumns();
	    int screensizeY = zinkPanel.getScreenStartPoint().y * zinkPanel.getTileSize() * zinkPanel.getRows();
	    g2.drawString(message,
			  zinkPanel.getTileSize() + screensizeX,
			  zinkPanel.getTileSize() + screensizeY + font.getSize()/2
	    );
	}
    }
}
