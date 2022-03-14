package se.liu.hanba478henan555;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyHandler extends KeyAdapter
{

    private HashMap<Integer, Boolean> keyMap = new HashMap<>(Map.ofEntries(
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
    public boolean getKey(String key){
	switch (key){
	    case "up" -> {return keyMap.get(KeyEvent.VK_W);}
	    case "down" -> {return keyMap.get(KeyEvent.VK_S);}
	    case "left" -> {return keyMap.get(KeyEvent.VK_A);}
	    case "right" -> {return keyMap.get(KeyEvent.VK_D);}
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
