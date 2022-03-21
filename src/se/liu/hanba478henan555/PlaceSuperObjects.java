package se.liu.hanba478henan555;

/**
 * Places objects on frame
 */
public class PlaceSuperObjects
{
    private ZinkPanel zinkPanel;

    public PlaceSuperObjects(ZinkPanel zp){
	this.zinkPanel = zp;
    }

    public void placeDoor(int x, int y){
	Door door = new Door(zinkPanel);
	door.setValues(x,y);
	zinkPanel.gameObjects.add(door);
    }

    private void placeKey(int x, int y){
	Key key = new Key(zinkPanel);
	key.setValues(x,y);
	zinkPanel.gameObjects.add(key);
    }

    public void placeObjects(){
	placeDoor(6,4);
	placeKey(1,1);
	placeKey(10,10);
	placeKey(10,21);
	placeKey(46,1);

	placeDoor(42,1);

    }


}
