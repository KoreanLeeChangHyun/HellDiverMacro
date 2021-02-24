package HellDiverMacro;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

//Mouse 입력 리스너 구현 
public class MouseHooker implements NativeMouseListener { 
	public void nativeMouseClicked(NativeMouseEvent e) { 
		// System.out.println("Mouse Clicked: " + e.getPoint()); 
	} 
	
	public void nativeMousePressed(NativeMouseEvent e) { 
		// System.out.println("Mouse Pressed: " + e.getPoint()); 
	} 
	
	public void nativeMouseReleased(NativeMouseEvent e) { 
		// System.out.println("Mouse Released: " + e.getPoint()); 
	} 
}
