package se.liu.hanba478henan555.game_director.ui;

import se.liu.hanba478henan555.entity.entity_abstract.EntityInput;
import se.liu.hanba478henan555.entity.entity_player.Player;
import se.liu.hanba478henan555.game_director.input_manager.KeyHandler;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;
import se.liu.hanba478henan555.objects.life.Heart;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;


/**
 * Inventory-class handles showing the Player's inventory on screen
 */
public class WindowManager
{
    private boolean showingMessage = false;
    private String message = null;
    private Font font;
    private Timer messageTimer = null;

    private ZinkPanel zinkPanel;
    private Player player;
    private Heart[] hearts;
    private Point imagePoint;

    private int screensizeX, screensizeY;
    private Point currentScreen = new Point(0, 0);
    private int playerChoice = 0;
    private int arrowCounter = 0;

    private boolean howToPlay = false;
    private boolean showingInventory = false;

    private Point slotPos = new Point(0,0);
    private final static int INVENTORY_WIDTH = 4;
    private final static int INVENTORY_HEIGHT = 3;
    private int slotCol = 0;
    private int slotRow = 0;
    private static final int SLOT_SPEED = 5;
    private int slotCounter = 0;

    private final static int ROUND_CORNERS = 25;

    private final Map<ObjectType, String> objectMessageMap = Map.ofEntries(
	    Map.entry(ObjectType.KEY, "You got a Key!"),
	    Map.entry(ObjectType.DOOR, "You opened a Door!"),
	    Map.entry(ObjectType.HEART, "You got one life!")
    );


    public WindowManager(ZinkPanel zp) {
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
	int marginal = zinkPanel.getTileSize() / 2;
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

    public void showObjectMessage(AbstractObject abstractObject) {
	message = objectMessageMap.get(abstractObject.getGameObject());
	showingMessage = true;
	messageTimer.stop();
	messageTimer.start();
    }

    public void showWinScreen(){
	messageTimer.stop();
	message = "You saved the princess!";
	showingMessage = true;
    }

    public void draw(Graphics2D g2) {
	updateCurrentScreen();
	if (zinkPanel.isShowingTitleScreen()) {
	    showTitleScreen(g2);
	    return;
	}
	showInventory(g2);
	updateHearts();
	g2.setFont(font);
	g2.setColor(Color.BLACK);
	drawHearts(g2);
	showMessage(g2);
    }

    private void showMessage(Graphics2D g2){
	if (showingMessage) {
	    g2.drawString(message, currentScreen.x + screensizeX/2 - getStringLength(g2, message)/2,
			  currentScreen.y + screensizeY/2);
	}
    }

    private void drawHearts(Graphics2D g2){
	int imageSize = zinkPanel.getTileSize()*2 /3;
	for (int i = 0; i < hearts.length; i++) {
	    g2.drawImage(hearts[i].getImage(), zinkPanel.getTileSize()*(i+1) + currentScreen.x,
			 imagePoint.y + currentScreen.y, imageSize, imageSize, null);
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
	    startGame();
	}
	else if (playerChoice == 1) {
	    startHowToPlay();
	}
    }

    private void startHowToPlay() {
	howToPlay = true;
    }

    private void startGame() {
	zinkPanel.stopShowingTitleScreen();
    }

    private void updatePlayerChoice(EntityInput entityInput, int step, int amountOptions) {
	if (zinkPanel.getKeyHandler().getKey(entityInput)) {
	    playerChoice += step;
	    if (playerChoice < 0 || playerChoice == amountOptions) playerChoice = 0;
	}
    }

    private void showInventory(Graphics2D g2) {
	if (!showingInventory) {
	    return;
	}
	//inventory window
	int posX = (player.getPos().x  + zinkPanel.getTileSize() / 2)/ zinkPanel.getTileSize();
	int posY = (player.getPos().y  + zinkPanel.getTileSize() / 2)/ zinkPanel.getTileSize();

	final int frameX = (posX / zinkPanel.getColumns() *zinkPanel.getColumns()*zinkPanel.getTileSize()) + zinkPanel.getTileSize()*9;
	final int frameY = (posY / zinkPanel.getRows() * zinkPanel.getRows()*zinkPanel.getTileSize()) + zinkPanel.getTileSize()*2;

	int framewidth = zinkPanel.getTileSize() * 6;
	int frameHeigth = zinkPanel.getTileSize() * 5;
	drawWindow(g2, frameX, frameY, framewidth, frameHeigth);

	//slot
	final int slotXstart = frameX + 20;
	final int slotYstart = frameY + 20;
	int slotX = slotXstart;
	int slotY = slotYstart;
	final int slotSize = zinkPanel.getTileSize()+3;

	drawItems(g2, slotXstart, slotX, slotY, slotSize);
	drawCursor(g2, slotSize, slotXstart, slotYstart);

	moveSlot();

    }

    private void drawItems(Graphics2D g2, int startX, int currentX, int currentY, int slotSize){

	for (int i = 0; i < player.getInventory().size(); i++) {
	    if(player.getInventory().get(i).getGameObject().equals(player.getWeapon())){
		g2.setColor(Color.ORANGE);
		g2.fillRoundRect(currentX,currentY,zinkPanel.getTileSize(),zinkPanel.getTileSize(),10,10);
	    }

	    g2.drawImage(player.getInventory().get(i).getImage(), currentX, currentY, zinkPanel.getTileSize(), zinkPanel.getTileSize(), null);
	    currentX += slotSize;
	    if (i == 4 || i == 9 || i == 14) {
		currentX = startX;
		currentY += slotSize;
	    }
	}
    }

    private void drawCursor(Graphics2D g2, int slotSize, int x, int y){

	int cursorX = x + (slotSize * slotCol);
	int cursorY = y + (slotSize * slotRow);
	int cursorWidth = zinkPanel.getTileSize();
	int cursorHeight = zinkPanel.getTileSize();

	g2.setColor(Color.white);
	g2.drawRect(cursorX, cursorY, cursorWidth, cursorHeight);

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
	    if (slotRow != INVENTORY_HEIGHT) {
		slotRow++;
	    }
	}
	else if (keyHandler.getKey(EntityInput.LEFT)) {
	    if (slotCol != 0) {
		slotCol--;
	    }
	}
	else if (keyHandler.getKey(EntityInput.RIGHT)) {
	    if (slotCol != INVENTORY_WIDTH) {
		slotCol++;
	    }
	}
	else if (keyHandler.getKey(EntityInput.ATTACK)){
	    int index = (slotRow * INVENTORY_WIDTH + slotCol) + slotRow % INVENTORY_WIDTH;
	    if (index < player.getInventory().size()){
		zinkPanel.getPlayer().selectCurrentWeapon(index);
	    }

	}
    }

    private void resetSlotPos() {
	slotPos.x = 0;
	slotPos.y = 0;
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
