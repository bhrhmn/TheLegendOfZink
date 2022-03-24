package se.liu.hanba478henan555.game_director.game_managers;

import se.liu.hanba478henan555.entity.enemy.EnemyBlob;
import se.liu.hanba478henan555.entity.enemy.EnemyDragon;
import se.liu.hanba478henan555.entity.enemy.EnemyRed;
import se.liu.hanba478henan555.objects.life.BloodPile;
import se.liu.hanba478henan555.objects.weapon.Bow;
import se.liu.hanba478henan555.objects.open.Door;
import se.liu.hanba478henan555.objects.open.Key;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;
import se.liu.hanba478henan555.objects.weapon.PlayerSword;

import java.awt.*;

/**
 * Places objects and enemies on frame
 */
public class PlaceSuperObjectsSpawnEnemies
{
    private ZinkPanel zinkPanel;

    public PlaceSuperObjectsSpawnEnemies(ZinkPanel zp){
	this.zinkPanel = zp;
    }

    public void placeDoor(int x, int y){
	Door door = new Door(zinkPanel);
	door.setValues(x,y);
	zinkPanel.getGameObjects().add(door);
    }

    private void placeKey(int x, int y){
	Key key = new Key(zinkPanel);
	key.setValues(x,y);
	zinkPanel.getGameObjects().add(key);
    }

    private void spawnRedEnemy(int x, int y){
	EnemyRed en = new EnemyRed(zinkPanel, new Point(x, y));
	zinkPanel.getEnemyList().add(en);
    }

    private void spawnBlob(int x, int y) {
	EnemyBlob blob = new EnemyBlob(zinkPanel, new Point(x, y));
	zinkPanel.getEnemyList().add(blob);
    }

    private void spawnDragon(int x, int y) {
	EnemyDragon dragon = new EnemyDragon(zinkPanel, new Point(x, y));
	zinkPanel.getEnemyList().add(dragon);
    }

    public void spawnBloodPile(int x, int y) {
	BloodPile bloodPile = new BloodPile(zinkPanel);
	bloodPile.setValues(x,y);
	zinkPanel.getGameObjects().add(bloodPile);
    }

    private void placeSword(int x, int y, ObjectType sword) {
	PlayerSword playerSword = new PlayerSword(zinkPanel, sword, true);
	playerSword.setValues(x,y);
	zinkPanel.getGameObjects().add(playerSword);
    }

    private void placeBow(int x, int y){
	Bow bow = new Bow(zinkPanel);
	bow.setValues(x,y);
	zinkPanel.getGameObjects().add(bow);
    }


    public void placeObjects(){

	placeDoor(6,4);
	placeKey(1,1);
	placeKey(10,10);
	placeKey(10,21);
	placeKey(46,1);

	placeDoor(42,1);

	placeKey(6,14);
	placeKey(7,14);
	placeKey(8,14);

	placeSword(7, 2, ObjectType.PLAYER_SWORD_GOOD);
	placeSword(2, 2,  ObjectType.PLAYER_SWORD_BAD);

	placeBow(11,10);
    }

    public void spawnEnemies(){

	spawnRedEnemy(9, 20);

	spawnBlob(6, 22);

	spawnDragon(2, 8);

    }


}
