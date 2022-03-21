package se.liu.hanba478henan555;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Manages background
 */
public class RoomManager
{
    private Tile[] tileTypes;
    public int[][] roomTileData;
    private int tileSize,worldRows,worldColumns;

    public RoomManager(ZinkPanel zp) {
	this.tileSize = zp.getTileSize();
	this.worldColumns = zp.getWorldColumns();
	this.worldRows = zp.getWorldRows();

	this.tileTypes = new Tile[10];
	this.roomTileData = new int[worldColumns][worldRows];
	defineTileTypes();
	loadMap("src/se/liu/hanba478henan555/world/world.txt");
    }

    private void defineTileTypes() {
	try {
	    tileTypes[0] = new Tile();
	    tileTypes[0].image = ImageIO.read(new File("src/se/liu/hanba478henan555/tiles/wall.png"));
	    tileTypes[0].collision = true;


	    tileTypes[1] = new Tile();
	    tileTypes[1].image = ImageIO.read(new File("src/se/liu/hanba478henan555/tiles/earth.png"));

	}catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void loadMap(String roomFile) {
	Scanner scanner = null;

	try{scanner = new Scanner(new BufferedReader(new FileReader(roomFile)));
	} catch (FileNotFoundException e){ e.printStackTrace();}

	for(int y = 0; y < worldRows; y++){
	    String[] line = scanner.nextLine().trim().split(" ");
	    for (int x = 0; x < worldColumns; x++){
		roomTileData[x][y] = Integer.parseInt(line[x]);
	    }
	}

    }

    public void draw(Graphics2D g2) {
	for (int y =0; y < worldRows; y++) {
	    for(int x = 0; x < worldColumns; x++){
		int tileType = roomTileData[x][y];

		g2.drawImage(tileTypes[tileType].image,
			     x*tileSize ,y*tileSize,
			     	tileSize, tileSize, null );
	    }
	}
    }

    public boolean tileHasCollision(Point pos){
	return tileTypes[roomTileData[pos.x][pos.y]].collision;
    }
}
