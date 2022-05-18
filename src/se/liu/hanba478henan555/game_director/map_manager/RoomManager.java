package se.liu.hanba478henan555.game_director.map_manager;

import se.liu.hanba478henan555.LoggingManager;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * Manages background
 * Reads and saves images in Tiles
 * Reads a text file for the map and saves corresponding Tile for every number in textfile
 * in a two-dimensional int-array
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public class RoomManager
{
    private Tile[] tileTypes;
    private int[][] roomTileData;
    private int tileSize,worldRows,worldColumns;
    private int originalTileSize;

    public RoomManager(ZinkPanel zp) {
	this.tileSize = zp.getTileSize();
	this.worldColumns = zp.getWorldColumns();
	this.worldRows = zp.getWorldRows();
	this.originalTileSize = zp.getOriginalTileSize();

	this.tileTypes = new Tile[10];
	this.roomTileData = new int[worldColumns][worldRows];
	defineTileTypes();
	loadMap("world/world.txt");
    }

    private void defineTileTypes() {
	loadTile(0, "wall.png", true);
	loadTile(1, "earth.png", false);
	loadTile(2, "pedestal.png", false);
    }


    private void loadTile(int index, String file, boolean collision){
	BufferedImage result, readFile = null;
	try{
	    String pathToTile = "images/tiles/";
	    readFile = ImageIO.read(ClassLoader.getSystemClassLoader().getResource(pathToTile + file));
	} catch (IOException e){
	    LoggingManager.getLogr().log(Level.SEVERE, "loadTile", e);
	    readFile = new BufferedImage(originalTileSize,originalTileSize,BufferedImage.TYPE_BYTE_GRAY );
	}finally {
	    result = readFile;
	}
	tileTypes[index] = new Tile();
	tileTypes[index].setImage(result);
	tileTypes[index].setCollision(collision);

    }

    public void loadMap(String roomFile) {
	try (Scanner scanner = new Scanner(getResourceFileAsString(roomFile))) {
	    for (int y = 0; y < worldRows; y++) {
		String[] line = scanner.nextLine().trim().split(" ");
		for (int x = 0; x < worldColumns; x++) {
		    roomTileData[x][y] = Integer.parseInt(line[x]);
		}
	    }
	}
    }

    private String getResourceFileAsString(String fileName) {
	ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	try (InputStream is = classLoader.getResourceAsStream(fileName)) {
	    try (InputStreamReader inputStreamReader = new InputStreamReader(is);
		 BufferedReader reader = new BufferedReader(inputStreamReader)) {
		return reader.lines().collect(Collectors.joining(System.lineSeparator()));
	    }
	} catch (IOException e) {
	    LoggingManager.getLogr().log(Level.SEVERE, "loadMap", e);
	}
	return null;
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

    public boolean hasCollision(Point pos){
	return tileTypes[roomTileData[pos.x][pos.y]].isCollision();
    }
}
