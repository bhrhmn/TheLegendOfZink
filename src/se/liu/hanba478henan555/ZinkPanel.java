package se.liu.hanba478henan555;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 */
public class ZinkPanel extends JPanel
{
    private int originalTileSize = 16;
    private int factor = 3;
    private int tileSize = originalTileSize * factor;
    public static final int ROWS = 12;
    public static final int COLUMNS = 16;

    private static final int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();

    private Player player = new Player();

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
        if (keyHandler.getKey("up")) {
            player.pos.y -= player.speed;
        }
        if (keyHandler.getKey("down")) {
            player.pos.y += player.speed;
        }
        if (keyHandler.getKey("left")) {
            player.pos.x -= player.speed;
        }
        if (keyHandler.getKey("right")) {
            player.pos.x += player.speed;
        }
    }

    @Override public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(73, 11, 171));
        g2.fillRect(player.pos.x, player.pos.y, tileSize, tileSize);

        g2.dispose(); //?????
    }

}
