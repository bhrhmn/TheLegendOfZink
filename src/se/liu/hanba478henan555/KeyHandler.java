package se.liu.hanba478henan555;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles input from keyboard
 */
public class KeyHandler extends KeyAdapter
{

    private Map<Integer, Boolean> keyMap = new HashMap<>(Map.ofEntries(
	    Map.entry(KeyEvent.VK_W, false),
	    Map.entry(KeyEvent.VK_A, false),
	    Map.entry(KeyEvent.VK_S, false),
	    Map.entry(KeyEvent.VK_D, false)
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
	changeMapValue(e,true);
    }

    @Override public void keyReleased(final KeyEvent e) {
	changeMapValue(e,false);
    }



}
