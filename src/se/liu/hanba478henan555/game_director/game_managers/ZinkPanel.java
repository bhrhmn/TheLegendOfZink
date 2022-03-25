package se.liu.hanba478henan555.game_director.game_managers;

import se.liu.hanba478henan555.entity.entity_abstract.AbstractEntity;
import se.liu.hanba478henan555.entity.entity_player.Player;
import se.liu.hanba478henan555.game_director.input_manager.KeyHandler;
import se.liu.hanba478henan555.game_director.ui.WindowManager;
import se.liu.hanba478henan555.game_director.map_manager.RoomManager;
import se.liu.hanba478henan555.game_director.sound_manager.PlaySound;
import se.liu.hanba478henan555.objects.abstract_game_object.AbstractObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the appearence of the frame
 */
public class ZinkPanel extends JPanel
{
    private static final int ORIGINAL_TILE_SIZE = 16;
    private static final int FACTOR = 3;

    private static final int TILE_SIZE = ORIGINAL_TILE_SIZE * FACTOR;

    private static final int COLUMNS = 16;
    private static final int ROWS = 12;

    private static final int WORLD_COLUMNS = 48;
    private static final int WORLD_ROWS = 24;

    private static final int FPS = 60;

    private boolean isGameOver = false;
    private boolean gameRunning;

    private Point screenStartPoint = new Point(0, 0);

    private KeyHandler keyHandler = new KeyHandler();
    private CollisionHandler collisionHandler = new CollisionHandler(this);
    private RoomManager roomManager = new RoomManager(this);

    /**
     * Used when playing soundeffects
     */
    public PlaySound sound = new PlaySound();
    /**
     * Plays backgroundmusic
     */
    public PlaySound music = new PlaySound();

    private Player player = new Player(this, new Point(7, 8), keyHandler);

    private List<AbstractEntity> enemyList = new ArrayList<>();
    private List<AbstractObject> gameObjects = new ArrayList<>();

    private PlaceSuperObjectsSpawnEnemies placeSuperObjectsSpawnEnemies = new PlaceSuperObjectsSpawnEnemies(this);
    private WindowManager windowManager = new WindowManager(this);
    private boolean showingTitleScreen = true;

    public ZinkPanel() {
        this.setPreferredSize(new Dimension(COLUMNS * TILE_SIZE, ROWS * TILE_SIZE));
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        keyHandler.setZinkPanel(this);
    }

    public int getRows(){return ROWS;}

    public int getColumns(){return COLUMNS;}

    public int getTileSize(){return TILE_SIZE;}

    public int getOriginalTileSize(){return ORIGINAL_TILE_SIZE;}

    public int getWorldRows(){return WORLD_ROWS;}

    public int getWorldColumns(){return WORLD_COLUMNS;}

    public Point getScreenStartPoint() {return screenStartPoint;}

    public WindowManager getWindowManager() {return windowManager;}

    public List<AbstractEntity> getEnemyList() {return enemyList;}

    public List<AbstractObject> getGameObjects() {return gameObjects;}

    public int getFPS() {return FPS;}

    public CollisionHandler getCollisionHandler() {return collisionHandler;}

    public RoomManager getRoomManager() {return roomManager;}

    public PlaySound getSound() {return sound;}

    public Player getPlayer() {return player;}

    public boolean isShowingTitleScreen() {return showingTitleScreen;}

    public KeyHandler getKeyHandler() {return keyHandler;}

    public void setGameRunning(boolean b) {
        gameRunning = b;
    }

    /**
     * starts timer
     */
    public void startTimer() {
        Timer gameTimer = new Timer(1000 / FPS, doOneStep);
        gameTimer.start();
        gameRunning = true;
    }

    public void setUpGame(){
        music.playMusic();
        placeSuperObjectsSpawnEnemies.placeObjects();
        placeSuperObjectsSpawnEnemies.spawnEnemies();
    }

    @SuppressWarnings("CloneableClassWithoutClone") private final Action doOneStep = new AbstractAction()
    {
        @Override public void actionPerformed(final ActionEvent e) {
            tick();
        }
    };


    /**
     * Updates and animates game
     */
    public void tick() {
        if (isGameOver) {
            windowManager.showGameOverMessage();
            repaint();
            return;
        }
        if (!isShowingTitleScreen() && gameRunning) update();
        repaint();
    }

    /**
     * calls on player function update
     */
    private void update() {
        player.update();

        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).update();
        }
        for (int i = 0; i<gameObjects.size(); i++) {
            gameObjects.get(i).update();
        }

    }

    private void moveScreen(Graphics g){
        int posX = (player.getPos().x  + getTileSize() / 2)/ getTileSize();
        int posY = (player.getPos().y  + getTileSize() / 2)/ getTileSize();
        g.translate((posX / getColumns()) *-getColumns()*getTileSize(),
                    (posY / getRows()) *-getRows()*getTileSize());
        screenStartPoint.x = posX / getColumns();
        screenStartPoint.y = posY / getRows();

    }


    @Override public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (showingTitleScreen) {
            windowManager.showTitleScreen(g2);
            return;
        }
        moveScreen(g2);
        roomManager.draw(g2);

        for (int i = 0; i<gameObjects.size(); i++) {
            gameObjects.get(i).draw(g2);
        }
        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).draw(g2);
        }

        player.draw(g2);
        windowManager.draw(g2);

    }

    public void setIsGameOver(final boolean b) {
        isGameOver = b;
    }

    public void stopShowingTitleScreen() {
        showingTitleScreen = false;
    }

    public void showWinScreen(){
        gameRunning = false;
        windowManager.showWinScreen();
    }
}
