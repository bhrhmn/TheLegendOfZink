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

    public void placeDoor(int x, int y, int index){
	Door door = new Door(zinkPanel, index);
	door.setValues(x,y);
	zinkPanel.gameObjects[index] = door;
    }

    private void placeKey(int x, int y, int index){
	Key key = new Key(zinkPanel,index);
	key.setValues(x,y);
	zinkPanel.gameObjects[index] = key;
    }

    public void placeObjects(){
	placeDoor(6,4, 0);
	placeKey(1,1,  1);
	placeKey(10,10,2);
	placeKey(10,21,3);
	placeKey(46,1, 4);

	placeDoor(42,1,5);
    }


}
