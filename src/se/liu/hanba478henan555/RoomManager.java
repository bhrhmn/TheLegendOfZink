package se.liu.hanba478henan555;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RoomManager
{
    private Tile[] tileTypes;
    private int[][] roomTileData;

    public RoomManager() {
	this.tileTypes = new Tile[10];
	this.roomTileData = new int[ZinkPanel.ROWS][ZinkPanel.COLUMNS];
	loadMap("src/se/liu/hanba478henan555/rooms/room_1.txt");
	getTileTypes();
    }

    private void getTileTypes() {
	try {
	    tileTypes[0] = new Tile();
	    tileTypes[0].image = ImageIO.read(new File("src/se/liu/hanba478henan555/tiles/wall.png"));

	    tileTypes[1] = new Tile();
	    tileTypes[1].image = ImageIO.read(new File("src/se/liu/hanba478henan555/tiles/earth.png"));
	}catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void loadMap(String roomFile) {
	try{
	    Scanner scanner = new Scanner(new BufferedReader(new FileReader(roomFile)));
	    while (scanner.hasNextLine()){
		for(int y = 0; y < ZinkPanel.COLUMNS; y++){
		    String[] line = scanner.nextLine().trim().split(" ");
		    for (int x = 0; x < ZinkPanel.ROWS; x++){
			roomTileData[x][y] = Integer.parseInt(line[x]);
		    }
		}
	    }

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

    }

    public void draw(Graphics2D g2) {
	for (int y =0 ; y < ZinkPanel.COLUMNS; y++) {
	    for(int x = 0; x < ZinkPanel.ROWS; x++){
		int tileType = roomTileData[x][y];
		g2.drawImage(tileTypes[tileType].image,x*ZinkPanel.TILE_SIZE,y*ZinkPanel.TILE_SIZE,ZinkPanel.TILE_SIZE, ZinkPanel.TILE_SIZE, null );
	    }
	}

    }


}