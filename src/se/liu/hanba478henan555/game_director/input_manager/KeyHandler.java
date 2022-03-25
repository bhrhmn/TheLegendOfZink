package se.liu.hanba478henan555.game_director.input_manager;

import se.liu.hanba478henan555.entity.entity_abstract.EntityInput;
import se.liu.hanba478henan555.game_director.game_managers.ZinkPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles input from keyboard
 * Saves the pressed state of relevant keys to "keyMap"
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public class KeyHandler extends KeyAdapter
{
    private ZinkPanel zinkPanel = null;

    private Map<Integer, Boolean> keyMap = new HashMap<>(Map.ofEntries(
	    Map.entry(KeyEvent.VK_W, false),
	    Map.entry(KeyEvent.VK_A, false),
	    Map.entry(KeyEvent.VK_S, false),
	    Map.entry(KeyEvent.VK_D, false),
	    Map.entry(KeyEvent.VK_ENTER, false),
	    Map.entry(KeyEvent.VK_SPACE, false)
    ));

    /**
     * Returns value in "keyMap" depending on input
     * @param key
     * @return
     */
    public boolean getKey(EntityInput key){
	switch (key){
	    case UP -> {return keyMap.get(KeyEvent.VK_W);}
	    case DOWN -> {return keyMap.get(KeyEvent.VK_S);}
	    case LEFT -> {return keyMap.get(KeyEvent.VK_A);}
	    case RIGHT -> {return keyMap.get(KeyEvent.VK_D);}
	    case CONFIRM -> {return keyMap.get(KeyEvent.VK_ENTER);}
	    case ATTACK -> {return  keyMap.get(KeyEvent.VK_SPACE);}
	    default -> {return false;}
	}
    }

    /**
     * Changes value in "keyMap"
     * @param e
     * @param b Value
     */
    private void changeMapValue(KeyEvent e, boolean b){
	int key = e.getKeyCode();
	if (keyMap.containsKey(key)) {
	    keyMap.put(key, b);
	}
    }

    @Override public void keyPressed(final KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	    zinkPanel.getWindowManager().playerConfirm();
	}
	changeMapValue(e,true);
    }

    @Override public void keyReleased(final KeyEvent e) {
	changeMapValue(e,false);
    }

    public void setZinkPanel(ZinkPanel zinkPanel) {
	this.zinkPanel = zinkPanel;
    }

}
