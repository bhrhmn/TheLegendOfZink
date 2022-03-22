package se.liu.hanba478henan555;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Inventory-class handles showing the Player's inventory on screen
 */
public class Inventory
{
    private boolean showingMessage = false;
    //private String inventoryMessage = "";
    private String message = "hejsan";
    private Font font;

    private ZinkPanel zinkPanel;
    private Player player;
    private Heart[] hearts;
    private Point imagePoint;
    private int playerHearts;

    private Timer messageTimer = null;

    private int screensizeX, screensizeY;

    public Inventory(ZinkPanel zp) {
	this.zinkPanel = zp;
	this.player = zinkPanel.getPlayer();
	this.hearts  = new Heart[player.getMaxHealth()];
	this.font = new Font("Comic Sans MS", Font.BOLD, zinkPanel.getTileSize()*2 /3);

	setValues();
	setTimer();
    }

    private void setTimer() {
	ActionListener action = e -> {
	    showingMessage = false;
	    messageTimer.stop();
	};
	this.messageTimer = new Timer(3000, action);
    }

    private void setValues() {
	this.screensizeX = zinkPanel.getTileSize() * zinkPanel.getColumns();
	this.screensizeY = zinkPanel.getTileSize() * zinkPanel.getRows();
	int coordinate = zinkPanel.getTileSize()/3;
	this.imagePoint = new Point(coordinate, coordinate);
	for (int i = 0; i < 3; i++) {
	    Heart h = new Heart(zinkPanel);
	    h.setFullHeart();
	    hearts[i] = h;
	}
    }

    //private void updateMessage() {inventoryMessage = String.format("x %d", player.getAmmountOfDoorKeys());}

    private void updateHearts() {
	int playerHearts = player.getHealth();
	for (int i = 0; i < hearts.length; i++) {
	    if (i >= playerHearts) hearts[i].setEmptyHeart();
	    else hearts[i].setFullHeart();
	}
    }

    public void showKeyMessage() {showObjectMessage(ObjectType.KEY);}

    public void showDoorMessage() {showObjectMessage(ObjectType.DOOR);}

    private void showObjectMessage(ObjectType objectType) {
	switch (objectType) {
	    case KEY -> message = "You got a key!";
	    case DOOR -> message = "You opened a door!";
	    case CHEST -> message = "You opened a chest!";
	    case HEART -> message = "You got one life!";
	}
	showingMessage = true;
	messageTimer.stop();
	messageTimer.start();
    }

    public void draw(Graphics2D g2) {
	//TODO: Fixa alla magiska konstanter
	updateHearts();
	g2.setFont(font);
	g2.setColor(Color.BLACK);
	int currentScreenX = zinkPanel.getScreenStartPoint().x * zinkPanel.getTileSize() * zinkPanel.getColumns();
	int currentScreenY = zinkPanel.getScreenStartPoint().y * zinkPanel.getTileSize() * zinkPanel.getRows();
	int imageSize = zinkPanel.getTileSize()*2 /3;
	for (int i = 0; i < hearts.length; i++) {
	    g2.drawImage(hearts[i].image, zinkPanel.getTileSize()*(i+1) + currentScreenX,
			 imagePoint.y + currentScreenY, imageSize, imageSize, null);
	}
	if (showingMessage) {
	    //TODO: Fixa bättre plats för message
	    g2.drawString(message, currentScreenX + screensizeX/3 + font.getSize(),
			  currentScreenY + screensizeY/2);
	}
    }

    public void showGameOverMessage() {
	message = "Game Over";
	showingMessage = true;
    }
}
