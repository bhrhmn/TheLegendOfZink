package se.liu.hanba478henan555;

import javax.swing.*;
import java.awt.*;

/**
 * Titlescreen
 * shows a pink screen with a word
 */
public class TitleScreen extends JPanel
{
    public TitleScreen() {
	ZinkPanel zinkPanel = new ZinkPanel();
	this.setPreferredSize(new Dimension(zinkPanel.getColumns() * zinkPanel.getTileSize(), zinkPanel.getRows() * zinkPanel.getTileSize()));
	this.setBackground(new Color(255, 53, 184));
	repaint();
    }

    @Override public void paintComponent(final Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
	g2.drawString("Tjena", 100, 100);
    }
}
