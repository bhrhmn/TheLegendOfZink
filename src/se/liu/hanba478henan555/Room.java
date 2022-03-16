package se.liu.hanba478henan555;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Room
{
    public Tile[] tileTypes;

    public Room() {
	tileTypes = new Tile[10];
	getTileTypes();
    }

    private void getTileTypes() {
	try {
	    tileTypes[0] = new Tile();
	    tileTypes[0].image = ImageIO.read(getClass().getResourceAsStream("./tiles/wall.png"));

	    tileTypes[1] = new Tile();
	    tileTypes[1].image = ImageIO.read(getClass().getResourceAsStream("./tiles/earth.png"));
	}catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void draw(Graphics2D g2) {
	g2.drawImage(tileTypes[0].image, 0, 0,  ZinkPanel.TILE_SIZE,  ZinkPanel.TILE_SIZE, null);
	g2.drawImage(tileTypes[1].image, ZinkPanel.TILE_SIZE, 0,  ZinkPanel.TILE_SIZE,  ZinkPanel.TILE_SIZE, null);
    }

    public void loadMap() {

    }
}
