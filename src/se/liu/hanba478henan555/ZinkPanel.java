package se.liu.hanba478henan555;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Handles the appearence of the frame
 */
public class ZinkPanel extends JPanel
{
    private static final int ORIGINAL_TILE_SIZE = 16;
    private static final int FACTOR = 3;
    /**
     Size of a tile
     */
    private static final int TILE_SIZE = ORIGINAL_TILE_SIZE * FACTOR;

    private static final int ROWS = 16;
    private static final int COLUMNS = 12;

    private static final int WORLD_ROWS = 48;
    private static final int WORLD_COLUMNS = 24;

    private static final int FPS = 60;

    private KeyHandler keyHandler = new KeyHandler();
    public  CollisionHandler collisionHandler = new CollisionHandler(this);
    public  Player player = new Player(this,keyHandler);
    public  RoomManager roomManager = new RoomManager(this);
    public AbstractObject[] gameObjects = new AbstractObject[10];
    private PlaceSuperObjects placeSuperObjects = new PlaceSuperObjects(this);
    public ZinkPanel() {
        this.setPreferredSize(new Dimension(ROWS * TILE_SIZE, COLUMNS * TILE_SIZE));
        this.setBackground(new Color(255, 53, 184));
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public int getRows(){
        return ROWS;
    }

    public int getColumns(){
        return COLUMNS;
    }

    public int getTileSize(){
        return TILE_SIZE;
    }

    public int getOriginalTileSize(){return ORIGINAL_TILE_SIZE;}

    public int getWorldRows(){return WORLD_ROWS;}

    public int getWorldColumns(){return  WORLD_COLUMNS;}
    /**
     * starts timer
     */
    public void startTimer() {
        Timer gameTimer = new Timer(1000 / FPS, doOneStep);
        gameTimer.start();
    }

    public void setUpGame(){
        placeSuperObjects.placeObjects();
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
        update();
        repaint();
    }

    /**
     * calls on player function update
     */
    private void update() {
        player.update();
    }

    private void moveScreen(Graphics g){
        int posX = (player.pos.x  + getTileSize() / 2)/ getTileSize();
        int posY = (player.pos.y  + getTileSize() / 2)/ getTileSize();
        g.translate((posX / getRows()) *-getRows()*getTileSize(),
                    (posY / getColumns()) *-getColumns()*getTileSize());
    }


    @Override public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        moveScreen(g2);
        roomManager.draw(g2);
        player.draw(g2);


        for (final AbstractObject object : gameObjects) {
            if(object != null){
                object.draw(g2);
            }
        }

    }

}
