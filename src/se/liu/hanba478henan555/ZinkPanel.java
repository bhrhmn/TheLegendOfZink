package se.liu.hanba478henan555;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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

    private static final int COLUMNS = 16;   //rows och columns ska vara tvärtom??
    private static final int ROWS = 12;

    private static final int WORLD_COLUMNS = 48;
    private static final int WORLD_ROWS = 24;

    private static final int FPS = 60;

    private Point screenStartPoint = new Point(0, 0);

    private KeyHandler keyHandler = new KeyHandler();
    public  CollisionHandler collisionHandler = new CollisionHandler(this);
    public  RoomManager roomManager = new RoomManager(this);

    public  PlaySound sound = new PlaySound();
    private Sound music = new Sound();

    public  Player player = new Player(this,keyHandler);
    public  AbstractObject[] gameObjects = new AbstractObject[10];
    private PlaceSuperObjects placeSuperObjects = new PlaceSuperObjects(this);
    private Inventory inventory = new Inventory(this);

    public ZinkPanel() {
        this.setPreferredSize(new Dimension(COLUMNS * TILE_SIZE, ROWS * TILE_SIZE));
        this.setBackground(new Color(255, 53, 184));
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public int getRows(){return ROWS;}

    public int getColumns(){return COLUMNS;}

    public int getTileSize(){return TILE_SIZE;}

    public int getOriginalTileSize(){return ORIGINAL_TILE_SIZE;}

    public int getWorldRows(){return WORLD_ROWS;}

    public int getWorldColumns(){return WORLD_COLUMNS;}

    public Point getScreenStartPoint() {return screenStartPoint;}

    /**
     * starts timer
     */
    public void startTimer() {
        Timer gameTimer = new Timer(1000 / FPS, doOneStep);
        gameTimer.start();
    }

    public void setUpGame(){
        sound.playMusic();
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
        g.translate((posX / getColumns()) *-getColumns()*getTileSize(),
                    (posY / getRows()) *-getRows()*getTileSize());
        screenStartPoint.x = posX / getColumns();
        screenStartPoint.y = posY / getRows();

    }


    @Override public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        moveScreen(g2);
        roomManager.draw(g2);

        for (final AbstractObject object : gameObjects) {
            if(object != null){
                object.draw(g2);
            }
        }
        player.draw(g2);
        inventory.draw(g2);

    }

}
