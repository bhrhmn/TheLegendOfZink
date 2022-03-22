package se.liu.hanba478henan555;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Inventory-class handles showing the Player's inventory on screen
 */
public class Inventory
{
    private boolean showingMessage = false;
    private String inventoryMessage = "";
    private String message = "hejsan";
    private Font font;

    private ZinkPanel zinkPanel;
    private Player player;
    private BufferedImage image;
    private Point imagePoint;

    private Timer messageTimer;

    int screensizeX, screensizeY;

    public Inventory(ZinkPanel zp) {
	this.zinkPanel = zp;
	this.player = zinkPanel.player;
	this.font = new Font("Comic Sans MS", Font.BOLD, zinkPanel.getTileSize()*2 /3);
    	this.image = new Key(zinkPanel).image;
	this.messageTimer = new Timer(3000, action);
	setValues();
    }

    ActionListener action = e -> {
	showingMessage = false;
	messageTimer.stop();
    };

    private void setValues() {
	this.screensizeX = zinkPanel.getTileSize() * zinkPanel.getColumns();
	this.screensizeY = zinkPanel.getTileSize() * zinkPanel.getRows();
	int coordinate = zinkPanel.getTileSize()/3;
	this.imagePoint = new Point(coordinate, coordinate);
    }

    private void updateMessage() {
	inventoryMessage = String.format("x %d", player.getAmmountOfDoorKeys());
    }

    public void showKeyMessage() {
	//System.out.println("key message");
	showObjectMessage(ObjectType.KEY);
    }

    public void showDoorMessage() {
	//System.out.println("door message");
	showObjectMessage(ObjectType.DOOR);
    }

    private void showObjectMessage(ObjectType objectType) {
	switch (objectType) {
	    case KEY -> message = "You got a key!";
	    case DOOR -> message = "You opened a door!";
	    case CHEST -> message = "You opened a chest!";
	}
	showingMessage = true;
	messageTimer.start();
    }

    public void draw(Graphics2D g2) {
	updateMessage();

	g2.setFont(font);
	g2.setColor(Color.BLACK);
	int currentScreenX = zinkPanel.getScreenStartPoint().x * zinkPanel.getTileSize() * zinkPanel.getColumns();
	int currentScreenY = zinkPanel.getScreenStartPoint().y * zinkPanel.getTileSize() * zinkPanel.getRows();
	int imageSize = zinkPanel.getTileSize()*2 /3;

	g2.drawImage(image, zinkPanel.getTileSize()/4 + currentScreenX,
		     zinkPanel.getTileSize()/4 + currentScreenY, imageSize, imageSize, null);

	g2.drawString(inventoryMessage,
		      zinkPanel.getTileSize() + currentScreenX + imagePoint.x/2,
		      zinkPanel.getTileSize()/2 + currentScreenY + font.getSize()/2
			);
	if (showingMessage) {
	    //TODO: Fixa bättre plats för message
	    g2.drawString(message, currentScreenX + screensizeX/3 + font.getSize(),
			  currentScreenY + screensizeY/2);
	}
    }
}
