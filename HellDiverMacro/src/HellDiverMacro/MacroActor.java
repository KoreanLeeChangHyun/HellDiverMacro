package HellDiverMacro;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.HashMap;
import java.util.StringTokenizer;

class Macro extends Robot{

	  public Macro() throws AWTException {
		 super();
	  }

	  public void keyPut(int i)
	  {
	    super.keyPress(i);
	    super.keyRelease(i);
	    super.delay(40);
	  }
	  
	  public void keyPut(String str) {
		str = str.toUpperCase();
		
		for(int i=0; i<str.length(); i++){
			int keyCode = (int)str.charAt(i);
			super.keyPress(keyCode);
			super.keyRelease(keyCode);
			super.delay(40);
		}
	  }
	  
	}

	public class MacroActor {
		int keyCode;
		Macro macro;
		int StratagemKey;
		HashMap<String, Integer> keyCodeMap;
		StringTokenizer stk;
		
		public MacroActor(){
			initKeyCodeMap();
			
			try {
				macro = new Macro();
				StratagemKey = 17;
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}
		
		private void initKeyCodeMap() {
			keyCodeMap = new HashMap<String, Integer>();
			
			for(String[] strline: RobotkeyCode.code) {
				String keyName = strline[1];
				int keyValue = Integer.parseInt(strline[0]);
				keyCodeMap.put(keyName, keyValue);
			}
		}
		
		private void keyAct(String str) {
			macro.keyPress(this.StratagemKey);
			macro.delay(40);
			macro.keyPut(str);
			macro.keyRelease(this.StratagemKey);
		}
		
		private void setStratagemKey(String stratagemCode) {
			
			int keyValue = keyCodeMap.get(stratagemCode);	
			this.StratagemKey = keyValue;
			
		}
		
		public void actMacro(String hellDiverCode) {
			String[] slice = hellDiverCode.split("\\+");
			
			String stratagemCode = slice[0].trim();
			String inputCode = slice[1].trim();
			
			setStratagemKey(stratagemCode);
			keyAct(inputCode);
		}
	}