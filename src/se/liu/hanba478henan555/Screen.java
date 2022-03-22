package se.liu.hanba478henan555;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Inventory-class handles showing the Player's inventory on screen
 */
public class Screen
{
    private boolean showingMessage = false;
    private String message = "hejsan";
    private Font font;
    private Timer messageTimer = null;

    private ZinkPanel zinkPanel;
    private Player player;
    private Heart[] hearts;
    private Point imagePoint;
    private int playerHearts;

    private int screensizeX, screensizeY;
    private int playerChoice = 0;
    private int arrowCounter = 0; //TODO: counter är bara en templösning, fixa bättre lösning
    private int confirmCounter = 0;

    private boolean howToPlay = false;

    public Screen(ZinkPanel zp) {
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
	if (zinkPanel.isShowingTitleScreen()) {
	    showTitleScreen(g2);
	    return;
	}
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

    public void showTitleScreen(Graphics2D g2) {
	if (howToPlay) {
	    showHowToPlay(g2);
	    return;
	}

	int amountOptions = 2;
	Font font = new Font("Times New Roman", Font.BOLD, 60);
	g2.setFont(font);
	moveArrow(amountOptions);
	zinkPanel.setBackground(new Color(169, 108, 40));

	String title1 = "The Adventure";
	String title2 = "of Zink";
	int shadow = 4;
	int textLength1 = getStringLength(g2, title1);
	int textLength2 = getStringLength(g2, title2);
	int screenMiddleX = screensizeX/2;
	int screenMiddleY = zinkPanel.getTileSize() *2;

	g2.setColor(Color.darkGray);
	g2.drawString(title1, screenMiddleX - textLength1/2 +shadow, screenMiddleY +shadow);
	g2.drawString(title2, screenMiddleX - textLength2/2 +shadow, screenMiddleY + font.getSize() +shadow);
	g2.setColor(Color.black);
	g2.drawString(title1, screenMiddleX - textLength1/2, screenMiddleY);
	g2.drawString(title2, screenMiddleX - textLength2/2, screenMiddleY + font.getSize());

	Point[] options = new Point[amountOptions];
	Font font2 = new Font("Arial", Font.PLAIN, zinkPanel.getTileSize()*2 /3);
	g2.setFont(font2);
	String text = "START GAME";
	int x1 = screenMiddleX - getStringLength(g2, text)/2;
	int y1 = zinkPanel.getTileSize() *7;
	g2.drawString(text, x1, y1);
	options[0] = new Point(x1, y1);

	String text2 = "How to play";
	int x2 = screenMiddleX - getStringLength(g2, text2)/2;
	int y2 = zinkPanel.getTileSize() *8;
	g2.drawString(text2, x2, y2);
	options[1] = new Point(x2, y2);

	g2.drawString(">", options[playerChoice].x - zinkPanel.getTileSize(), options[playerChoice].y);
    }

    private void showHowToPlay(Graphics2D g2) {
	if (playerConfirm()) {
	    howToPlay = false;
	}
	Font font = new Font("Times New Roman", Font.PLAIN, 30);
	g2.setFont(font);
	String instructions1 = "Move with wasd-keys ";
	String instructions2 = "Attack enemies with ...";
	String instructions3 = "Open inventory with ...";
	g2.drawString(instructions1, zinkPanel.getTileSize(), zinkPanel.getTileSize());
	g2.drawString(instructions2, zinkPanel.getTileSize(), zinkPanel.getTileSize() + font.getSize());
	g2.drawString(instructions3, zinkPanel.getTileSize(), zinkPanel.getTileSize() + font.getSize()*2);
	String exit = "Exit How To Play by pressing ENTER";
	g2.drawString(exit, screensizeX - getStringLength(g2, exit) - zinkPanel.getTileSize(), screensizeY - g2.getFont().getSize());
    }

    private void moveArrow(int amountOptions) {
	arrowCounter++;
	if (playerConfirm()) {
	    playerChoice();
	}
	if (arrowCounter <= 5) {
	    return;
	}
	updatePlayeChoice(EntityInput.UP, -1, amountOptions);
	updatePlayeChoice(EntityInput.DOWN, 1, amountOptions);
	arrowCounter = 0;
    }

    private void playerChoice() {
	if (playerChoice == 0) {
	    startGameChoice();
	}
	else if (playerChoice == 1) {
	    startHowToPlay();
	}
    }

    private boolean playerConfirm() {
	confirmCounter++;
	if (confirmCounter <= 3) return false;
	confirmCounter = 0;
	return zinkPanel.getKeyHandler().getKey(EntityInput.CONFIRM);
    }

    private void startHowToPlay() {
	howToPlay = true;
    }

    private void startGameChoice() {
	zinkPanel.stopShowingTitleScreen();
    }

    private void updatePlayeChoice(EntityInput entityInput, int step, int amountOptions) {
	if (zinkPanel.getKeyHandler().getKey(entityInput)) {
	    playerChoice += step;
	    if (playerChoice < 0 || playerChoice == amountOptions) playerChoice = 0;
	}
    }

    private int getStringLength(Graphics2D g2, String text) {
	return (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    }

    public void showGameOverMessage() {
	message = "Game Over";
	showingMessage = true;
    }
}
