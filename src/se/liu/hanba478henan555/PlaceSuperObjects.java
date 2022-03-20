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

    public void placeKey(int x, int y, int i){
	Key key = new Key(zinkPanel,i);
	key.setValues(ObjectType.KEY, x,y);
	zinkPanel.gameObjects[i] = key;
    }

    public void placeObjects(){
	placeKey(6,4,  0);
	placeKey(1,1,  1);
	placeKey(10,10,2);

	placeKey(10,21,3);
	placeKey(46,1,4);
    }


}
