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
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * FACTOR;

    private static final int ROWS = 12;
    private static final int COLUMNS = 16;

    private static final int FPS = 60;

    private KeyHandler keyHandler = new KeyHandler();

    private Player player = new Player(keyHandler);
    private Room room = new Room();

    public ZinkPanel() {
        this.setPreferredSize(new Dimension(COLUMNS * TILE_SIZE, ROWS * TILE_SIZE));
        this.setBackground(new Color(169, 69, 69));
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    /**
     * starts timer
     */
    public void startTimer() {
        Timer gameTimer = new Timer(1000 / FPS, doOneStep);
        gameTimer.start();
    }

    private final Action doOneStep = new AbstractAction()
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

    @Override public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        room.draw(g2);
        player.draw(g2);

    }

}
