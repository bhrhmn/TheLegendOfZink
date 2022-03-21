package se.liu.hanba478henan555;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Inventory-class handles showing the Player's inventory on screen
 */
public class Inventory
{
    private boolean showingMessage;
    private String message;
    private Font font;
    private ZinkPanel zinkPanel;
    private Player player;
    private BufferedImage image;

    public Inventory(ZinkPanel zp) {
	this.zinkPanel = zp;
	this.player = zinkPanel.player;
	this.showingMessage = true;
	this.message = "hej";
	this.font = new Font("Comic Sans MS", Font.BOLD, zinkPanel.getTileSize()*2 /3);
    	this.image = new Key(zinkPanel).image;
    }

    private void updateMessage() {
	message = String.format("x %d", player.getAmmountOfDoorKeys());
    }

    public void draw(Graphics2D g2) {
	updateMessage();

	g2.setFont(font);
	g2.setColor(Color.BLACK);
	int screensizeX = zinkPanel.getScreenStartPoint().x * zinkPanel.getTileSize() * zinkPanel.getColumns();
	int screensizeY = zinkPanel.getScreenStartPoint().y * zinkPanel.getTileSize() * zinkPanel.getRows();
	g2.drawImage(image, zinkPanel.getTileSize()/2 + screensizeX,
		     zinkPanel.getTileSize()/2 + screensizeY, null);
	g2.drawString(message,
		      zinkPanel.getTileSize() + screensizeX,
		      zinkPanel.getTileSize()/2 + screensizeY + font.getSize()/2
			);


    }
}
