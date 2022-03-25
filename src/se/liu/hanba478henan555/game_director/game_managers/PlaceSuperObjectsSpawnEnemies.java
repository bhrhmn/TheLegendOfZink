package se.liu.hanba478henan555.game_director.game_managers;

import se.liu.hanba478henan555.entity.entity_enemy.EnemyBlob;
import se.liu.hanba478henan555.entity.entity_enemy.EnemyDragon;
import se.liu.hanba478henan555.entity.entity_enemy.EnemyRed;
import se.liu.hanba478henan555.objects.life.BloodPile;
import se.liu.hanba478henan555.objects.life.Heart;
import se.liu.hanba478henan555.objects.weapon.Bow;
import se.liu.hanba478henan555.objects.open.Door;
import se.liu.hanba478henan555.objects.open.Key;
import se.liu.hanba478henan555.objects.abstract_game_object.ObjectType;
import se.liu.hanba478henan555.objects.weapon.PlayerSword;
import se.liu.hanba478henan555.objects.win_objects.Princess;

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

    private void placeHeart(int x, int y) {
	Heart heart = new Heart(zinkPanel);
	heart.setValues(x, y);
	heart.setFullHeart();
	zinkPanel.getGameObjects().add(heart);
    }

    private void placePrincess(int x, int y) {
	Princess princess = new Princess(zinkPanel);
	princess.setValues(x, y);
	zinkPanel.getGameObjects().add(princess);
    }


    public void placeObjects(){

	placeDoor(6,4);
	placeKey(1,1);
	placeSword(7, 2,  ObjectType.PLAYER_SWORD_BAD);

	placeDoor(43,2);
	placeKey(26,6);

	placeBow(45,22);
	placeDoor(45,20);
	placeKey(19,22);

	placeKey(1,14);
	placeKey(2,34);
	placeDoor(20,31);

	placeSword(33,33, ObjectType.PLAYER_SWORD_GOOD);
	placeDoor(33,31);

	placeHeart(41,42);
	placeHeart(41,43);
	placeHeart(41,44);

	placePrincess(4,43);
    }

    public void spawnEnemies(){
	spawnRedEnemy(20, 6);
	spawnRedEnemy(24, 7);
	spawnBlob(20, 6);

	spawnBlob(35, 8);
	spawnBlob(34, 7);

	spawnRedEnemy(35, 17);
	spawnRedEnemy(32, 17);
	spawnBlob(34, 18);


	spawnBlob(3, 15);

	spawnRedEnemy(3, 15);
	spawnRedEnemy(3, 15);
	spawnRedEnemy(3, 15);
	spawnRedEnemy(3, 15);

	spawnRedEnemy(4, 34);
	spawnRedEnemy(8, 33);
	spawnRedEnemy(12, 30);
	spawnRedEnemy(14, 31);

	spawnDragon(16, 41);
	spawnDragon(19, 45);

    }


}
