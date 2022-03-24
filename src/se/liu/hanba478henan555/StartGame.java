package se.liu.hanba478henan555;

import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;

import javax.swing.*;

/**
 * Creates frame and starts game
 * Creates frame and starts game
 * Creates frame and starts game
 * Creates frame and starts game
 * Creates frame and starts game
 * Creates frame and starts game
 * Creates frame and starts game
 * Creates frame and starts game
 */
public class StartGame
{
    public static void main(String[] args) {
	JFrame frame = new JFrame("Legend of zink");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	
	ZinkPanel zinkPanel = new ZinkPanel();
	frame.add(zinkPanel);
	frame.pack();

	frame.setLocationRelativeTo(null);
	frame.setVisible(true);

	LoggingManager.setUpLogger();

	zinkPanel.setUpGame();
	zinkPanel.startTimer();
    }
}
