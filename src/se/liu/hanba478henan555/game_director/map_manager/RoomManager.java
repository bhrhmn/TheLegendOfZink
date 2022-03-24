package se.liu.hanba478henan555.game_director.map_manager;

import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;

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
    private int[][] roomTileData;
    private int tileSize,worldRows,worldColumns;
    private String fs = File.separator;

    public RoomManager(ZinkPanel zp) {
	this.tileSize = zp.getTileSize();
	this.worldColumns = zp.getWorldColumns();
	this.worldRows = zp.getWorldRows();

	this.tileTypes = new Tile[10];
	this.roomTileData = new int[worldColumns][worldRows];
	defineTileTypes();
	loadMap("resources" + fs + "world" + fs + "world.txt");
    }

    private void defineTileTypes() {
	//TODO ta bort "/"
	loadTile(0,  "images" + fs + "tiles" + fs + "wall.png",true);
	loadTile(1,  "images" + fs + "tiles" + fs + "earth.png",false);
	loadTile(2,  "images" + fs + "tiles" + fs + "pedestal.png",false);
    }

    private void loadTile(int index, String filePath, boolean collision){
	try {
	    tileTypes[index] = new Tile();
	    tileTypes[index].setImage(ImageIO.read(getClass().getResourceAsStream("/" + filePath)));
	    tileTypes[index].setCollision(collision);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void loadMap(String roomFile) {
	try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(roomFile)))) {
	    for (int y = 0; y < worldRows; y++) {
		String[] line = scanner.nextLine().trim().split(" ");
		for (int x = 0; x < worldColumns; x++) {
		    roomTileData[x][y] = Integer.parseInt(line[x]);
		}
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

    }

    public void draw(Graphics2D g2) {
	for (int y =0; y < worldRows; y++) {
	    for(int x = 0; x < worldColumns; x++){
		int tileType = roomTileData[x][y];

		g2.drawImage(tileTypes[tileType].getImage(),
			     x*tileSize ,y*tileSize,
			     	tileSize, tileSize, null );
	    }
	}
    }

    public boolean tileHasCollision(Point pos){
	return tileTypes[roomTileData[pos.x][pos.y]].isCollision();
    }
}
