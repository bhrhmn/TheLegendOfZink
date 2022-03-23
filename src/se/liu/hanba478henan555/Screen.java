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
    private Point currentScreen = new Point(0, 0);
    private int playerChoice = 0;
    private int arrowCounter = 0; //TODO: bättre lösning än counter

    private boolean howToPlay = false;
    private boolean showingInventory = false;

    private Point slotPos = new Point(0,0);
    private int inventoryWidth = 4;
    private int inventoryHeight = 3;
    private int slotCol = 0;
    private int slotRow = 0;
    private static final int SLOT_SPEED = 2;//TODO: FIXA
    private int slotCounter = 0;
    private int marginal;

    private final static int ROUND_CORNERS = 25;


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
	this.marginal = zinkPanel.getTileSize()/2;
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

    private void updateCurrentScreen() {
	currentScreen.x = zinkPanel.getScreenStartPoint().x * zinkPanel.getTileSize() * zinkPanel.getColumns();
	currentScreen.y = zinkPanel.getScreenStartPoint().y * zinkPanel.getTileSize() * zinkPanel.getRows();
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
	updateCurrentScreen();
	if (zinkPanel.isShowingTitleScreen()) {
	    showTitleScreen(g2);
	    return;
	}

	if (showingInventory) {
	    showInventory(g2);
	}

	updateHearts();
	g2.setFont(font);
	g2.setColor(Color.BLACK);
	int imageSize = zinkPanel.getTileSize()*2 /3;
	for (int i = 0; i < hearts.length; i++) {
	    g2.drawImage(hearts[i].image, zinkPanel.getTileSize()*(i+1) + currentScreen.x,
			 imagePoint.y + currentScreen.y, imageSize, imageSize, null);
	}
	if (showingMessage) {
	    //TODO: Fixa bättre plats för message
	    g2.drawString(message, currentScreen.x + screensizeX/3 + font.getSize(),
			  currentScreen.y + screensizeY/2);
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
	if (arrowCounter <= 5) {
	    return;
	}
	updatePlayerChoice(EntityInput.UP, -1, amountOptions);
	updatePlayerChoice(EntityInput.DOWN, 1, amountOptions);
	arrowCounter = 0;
    }

    public void playerConfirm() {
	if (zinkPanel.isShowingTitleScreen()) {
	    titleScreenConfirm();
	    return;
	}
	resetSlotPos();
	zinkPanel.setGameRunning(showingInventory);
	showingInventory = !showingInventory;
	if(showingInventory){
	    zinkPanel.music.stopMusic();
	    zinkPanel.sound.playSoundEffect(1);
	}else {
	    zinkPanel.sound.playSoundEffect(1);
	    zinkPanel.music.playMusic();
	}
    }

    private void titleScreenConfirm() {
	if (howToPlay) {
	    howToPlay = false;
	    return;
	}
	if (playerChoice == 0) {
	    startGameChoice();
	}
	else if (playerChoice == 1) {
	    startHowToPlay();
	}
    }

    private void startHowToPlay() {
	howToPlay = true;
    }

    private void startGameChoice() {
	zinkPanel.stopShowingTitleScreen();
    }

    private void updatePlayerChoice(EntityInput entityInput, int step, int amountOptions) {
	if (zinkPanel.getKeyHandler().getKey(entityInput)) {
	    playerChoice += step;
	    if (playerChoice < 0 || playerChoice == amountOptions) playerChoice = 0;
	}
    }

    private void showInventory(Graphics2D g2) {
	//frame
	int posX = (player.pos.x  + zinkPanel.getTileSize() / 2)/ zinkPanel.getTileSize();
	int posY = (player.pos.y  + zinkPanel.getTileSize() / 2)/ zinkPanel.getTileSize();

	int frameX = (posX / zinkPanel.getColumns() *zinkPanel.getColumns()*zinkPanel.getTileSize()) + zinkPanel.getTileSize()*9;
	int frameY = (posY / zinkPanel.getRows() * zinkPanel.getRows()*zinkPanel.getTileSize()) + zinkPanel.getTileSize()*2;

	int framewidth = zinkPanel.getTileSize() * 6;
	int frameHeigth = zinkPanel.getTileSize() * 5;
	drawWindow(g2, frameX, frameY, framewidth, frameHeigth);

	//Slot
	int slotXstart = frameX + 20;
	int slotYstart = frameY + 20;
	int slotX = slotXstart;
	int slotY = slotYstart;
	int slotSize = zinkPanel.getTileSize()+3;

	//draw items
	for (int i = 0; i < player.getInventory().size(); i++) {
	    if(player.getInventory().get(i).getGameObject().equals(player.getCurrentWeapoon())){
		g2.setColor(Color.ORANGE);
		g2.fillRoundRect(slotX,slotY,zinkPanel.getTileSize(),zinkPanel.getTileSize(),10,10);
	    }

	    g2.drawImage(player.getInventory().get(i).image, slotX, slotY, zinkPanel.getTileSize(), zinkPanel.getTileSize(), null);
	    slotX += slotSize;
	    if (i == 4 || i == 9 || i == 14) {
		slotX = slotXstart;
		slotY += slotSize;
	    }
	}

	//Cursor
	int cursorX = slotXstart + (slotSize * slotCol);
	int cursorY = slotYstart + (slotSize * slotRow);
	int cursorWidth = zinkPanel.getTileSize();
	int cursorHeight = zinkPanel.getTileSize();
	//draw
	g2.setColor(Color.white);
	g2.drawRect(cursorX, cursorY, cursorWidth, cursorHeight);

	moveSlot();

    }

    private Point middleOfScreen(int width, int height) {
	return new Point(currentScreen.x + (screensizeX - width)/2,currentScreen.y + (screensizeY - height)/2);
    }

    private void drawSlots(final Graphics2D g2, final int slotWidth, final int slotHeight, final int size,
			   final Point start) {
	setSlotBorder(g2);
	moveSlot();
	g2.drawRect(start.x + scale(slotPos.x) +marginal*slotPos.x, start.y + scale(slotPos.y) +marginal*slotPos.y, size, size);
    }

    private void moveSlot() {
	slotCounter++;
	if (slotCounter < SLOT_SPEED) {
	    return;
	}
	slotCounter = 0;
	KeyHandler keyHandler = zinkPanel.getKeyHandler();
	if (keyHandler.getKey(EntityInput.UP)) {
	    if (slotRow != 0) {
		slotRow--;
	    }
	}
	else if (keyHandler.getKey(EntityInput.DOWN)) {
	    if (slotRow != inventoryHeight) {
		slotRow++;
	    }
	}
	else if (keyHandler.getKey(EntityInput.LEFT)) {
	    if (slotCol != 0) {
		slotCol--;
	    }
	}
	else if (keyHandler.getKey(EntityInput.RIGHT)) {
	    if (slotCol != inventoryWidth) {
		slotCol++;
	    }
	}
	else if (keyHandler.getKey(EntityInput.ATTACK)){
	    int index = (slotRow*inventoryWidth+slotCol) + slotRow%inventoryWidth;
	    if (index < player.getInventory().size()){
		zinkPanel.getPlayer().selectCurrentWeapon(index);
	    }

	}
    }

    private void moveSlotInDirection(final EntityInput entityInput) {
	switch (entityInput) {
	    case UP -> checkCollision(PointXY.Y, -1);
	    case DOWN -> checkCollision(PointXY.Y, 1);
	    case LEFT -> checkCollision(PointXY.X, -1);
	    case RIGHT -> checkCollision(PointXY.X, 1);
	}
    }

    private void checkCollision(PointXY pointXY, final int i) {
	if (pointXY == PointXY.X) {
	    slotPos.x += i;
	    if (slotOutside()) {
		slotPos.x -= i;
	    }
	}
	else if (pointXY == PointXY.Y) {
	    slotPos.y += i;
	    if (slotOutside()) {
		slotPos.y -= i;
	    }
	}
    }

    private boolean slotOutside() {
	return (0 > slotPos.x || slotPos.x > inventoryWidth ||
	    0 > slotPos.y || slotPos.y > inventoryHeight);
    }


    private int scale(final int i) {
	return i * zinkPanel.getTileSize();
    }

    private void resetSlotPos() {
	slotPos.x = 0;
	slotPos.y = 0;
    }

    private void setSlotBorder(final Graphics2D g2) {
	changeBackstroke(g2,10.0f);
	g2.setColor(Color.gray);
    }

    private Point getCoordinateSlot(Point start, int slotSize, int marginal, int x, int y) {
	int pointX = start.x + marginal+(slotSize+marginal) * x;
	int pointY = start.y + marginal+(slotSize+marginal) * y;
	return new Point(pointX, pointY);
    }

    private void changeBackstroke(final Graphics2D g2, final float f) {
	float backStroke = zinkPanel.getTileSize() / f;
	g2.setStroke(new BasicStroke(backStroke));
    }

    private int getStringLength(Graphics2D g2, String text) {
	return (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    }

    public void showGameOverMessage() {
	message = "Game Over";
	showingMessage = true;
    }

    private void drawWindow(Graphics2D g2, int x, int y, int width, int height) {

	float subScreenAlpha = 0.6f;
	setAlphaComposite(g2, subScreenAlpha);
	g2.setColor(Color.black);
	g2.fillRoundRect(x, y, width, height, ROUND_CORNERS, ROUND_CORNERS);
	resetAlpha(g2);
	g2.setColor(Color.darkGray);
	float backStroke = zinkPanel.getTileSize() / 8.0f;
	g2.setStroke(new BasicStroke(backStroke));
	g2.drawRoundRect(x, y, width, height, ROUND_CORNERS, ROUND_CORNERS);
    }

    private void resetAlpha(Graphics2D g2) {
	setAlphaComposite(g2, 1.0f);
    }

    private void setAlphaComposite(final Graphics2D g2, final float alpha) {
	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }
}
