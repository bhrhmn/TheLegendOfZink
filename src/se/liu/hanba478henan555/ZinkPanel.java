package se.liu.hanba478henan555;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Updates the frame
 */
public class ZinkPanel extends JPanel
{
    private static int originalTileSize = 16;
    private static int factor = 3;
    public static int tileSize = originalTileSize * factor;
    public static final int ROWS = 12;
    public static final int COLUMNS = 16;

    private static final int FPS = 60;

    private KeyHandler keyHandler = new KeyHandler();

    private Player player = new Player(keyHandler);

    public ZinkPanel() {
        this.setPreferredSize(new Dimension(COLUMNS * tileSize, ROWS * tileSize));
        this.setBackground(new Color(169, 69, 69));
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        startTimer();
    }

    public void startTimer() {
        Timer timer = new Timer(1000 / FPS, doOneStep);
        timer.start();
    }

    public final Action doOneStep = new AbstractAction()
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
     * Changes the players position depending on which keys are currently pressed
     */
    private void update() {
        player.update();
    }

    @Override public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);

    }

}
