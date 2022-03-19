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

    public void placeObjects(){
	zinkPanel.objects[0] = new Key(zinkPanel);
	zinkPanel.objects[0].setValues("key", 6, 4);
    }
}
