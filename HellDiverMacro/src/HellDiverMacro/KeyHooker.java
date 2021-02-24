package HellDiverMacro;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyHooker implements NativeKeyListener {
	Handdler handdler;
	
	public KeyHooker(Handdler handdler) {
		this.handdler = handdler;
	}

	public void nativeKeyPressed(NativeKeyEvent e) { 
		
		if(handdler.isModefy() && handdler.getOneKey() == -1) {
			handdler.setOneKey(e.getKeyCode());
		}
		
		if(handdler.isRun()) {
			String key1 = "";
			
			if(handdler.getOneKey() != -1) {
				key1 = NativeKeyEvent.getKeyText(handdler.getOneKey());
			}
			String key2 = NativeKeyEvent.getKeyText(e.getKeyCode());
			
			String keyCode = key1 + " + " + key2;
			handdler.actMacro(keyCode);
			handdler.setOneKey(e.getKeyCode());
		}
	}
	
	public void nativeKeyReleased(NativeKeyEvent e) { 
		String hotkey = "";
		
		if(handdler.isModefy()) {
			if(handdler.getOneKey() == e.getKeyCode()) {
				String key = NativeKeyEvent.getKeyText(e.getKeyCode());
				hotkey = key;
			} 
			else {
				String key1 = NativeKeyEvent.getKeyText(handdler.getOneKey());
				String key2 = NativeKeyEvent.getKeyText(e.getKeyCode());
				hotkey = key1 + " + " + key2;
			}
			
			handdler.modefiyHotKey(hotkey);
		}
		
		
	} 
		
	public void nativeKeyTyped(NativeKeyEvent e) {} 
	
}
