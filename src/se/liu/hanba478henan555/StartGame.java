package se.liu.hanba478henan555;

import javax.swing.*;

/**
 * Creates frame and starts game
 */
public class StartGame
{
    public static void main(String[] args) {
	JFrame frame = new JFrame("Legend of zink");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);

	TitleScreen titleScreen = new TitleScreen();
	frame.add(titleScreen);
	frame.pack();
	
	ZinkPanel zinkPanel = new ZinkPanel();
	frame.add(zinkPanel);
	frame.pack();

	frame.setLocationRelativeTo(null);
	frame.setVisible(true);

	zinkPanel.setUpGame();
	zinkPanel.startTimer();
    }
}
