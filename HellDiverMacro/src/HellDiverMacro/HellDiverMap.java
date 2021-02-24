package HellDiverMacro;

import java.util.HashMap;
import java.util.LinkedList;

public class HellDiverMap {
	private HashMap<String, String> hotKeyMap = new HashMap<String, String>();
	private LinkedList<Stratagem> stratagemMap = new LinkedList<Stratagem>();
	
	private Handdler handdler;
	
	/*
	 * Constructor
	 */
	public HellDiverMap(String hellDiverTag, Handdler handdler) {
		this.handdler = handdler;
		initHellDiverMap(hellDiverTag);
	}
	
	private void initHellDiverMap(String hellDiverTag) {
		if(hellDiverTag.equals("")) {
			initDefalutMap();
		}
		else {
			initMapbyTag(hellDiverTag);
		}
	}
	
	private void initDefalutMap() {
		for(int id=0; id<=55; id++) {
			
			String name = StratagemList.name[id][0];
			String hotkey = "";
			String inputCode = StratagemList.name[id][1];
			
			Stratagem stratagem = new Stratagem(id, name, hotkey, inputCode);
			
			stratagemMap.add(stratagem);
			
			if(!hotkey.equals("")) {
				hotKeyMap.put(hotkey, inputCode);
			}
		}
		
		stratagemMap.get(51).setHotKey("Ctrl");
		
		saveHotKey();
	}
	
	private void initMapbyTag(String hellDiverTag) {
		for(int id=0; id<=55; id++) {
			String stratagemTag = TagParser.getValue(hellDiverTag, String.valueOf(id));
			
			String name = TagParser.getValue(stratagemTag, "name").trim();
			String hotkey = TagParser.getValue(stratagemTag, "hotkey").trim();
			String inputCode = TagParser.getValue(stratagemTag, "inputCode").trim();
			
			Stratagem stratagem = new Stratagem(id, name, hotkey, inputCode);
			
			stratagemMap.add(stratagem);
			
			if(!hotkey.equals("")) {
				hotKeyMap.put(hotkey, inputCode);
			}
		}
	}
	
	String getInputCode(String keyCode) {
		String stratagemCode = stratagemMap.get(51).getHotkey();
		String inputCode;

	
		
		// key1 + key2 = keyCode
		if(stratagemCode.equals(keyCode)) {
			return null;
		}
		inputCode = hotKeyMap.get(keyCode);
		if(inputCode != null) {
			return stratagemCode + " + " + inputCode;
		}
		
		// key2
		String key2 = keyCode.split("\\+")[1].trim();
		if(stratagemCode.equals(key2)) {
			return null;
		}
		inputCode = hotKeyMap.get(key2);
		if(inputCode != null) {
			return stratagemCode + " + " + inputCode;
		}
		
		return null;
	}
	
	private boolean isAWSD(String hotkey) {
		switch(hotkey) {
		case "W": case "A": case "S": case "D":
			return true;
		default:
			return false;
		}
	}
	
	private int checkHasHotKey(String newHotkey) {
		int removedID = -4;
		for(Stratagem stratagem: stratagemMap) {
			String hotkey = stratagem.getHotkey();
			if(hotkey.equals(newHotkey)) {
				removedID = stratagem.getID();
				break;
			}
		}
		
		return removedID;
	}
	
	private void saveHotKey() {
		String hellDiverTag = "";
		
		for(Stratagem stratagem: stratagemMap) {
			String lineStr = "";
			
			int id = stratagem.getID();
			String name = stratagem.getName();
			String hotkey = stratagem.getHotkey();
			String inputCode = stratagem.getInputCode();
			
			lineStr += "<" + id + ">\n";
			lineStr += "\t<name> " + name + " </name>\n";
			lineStr += "\t<hotkey> " + hotkey + " </hotkey>\n";
			lineStr += "\t<inputCode> " + inputCode + " </inputCode>\n";
			lineStr += "</" + id + ">\n";
			lineStr += "\n";
			
			hellDiverTag += lineStr;
		}
		
		handdler.writeConfig(hellDiverTag, "hellDiverTag.txt");
		
	}
	
	private void removeHotKey(int stratagemID) {
		Stratagem stratagem = stratagemMap.get(stratagemID);
		hotKeyMap.remove(stratagem.getHotkey());
		stratagem.setHotKey("");
		saveHotKey();
	}
	
	private void setHotKey(int stratagemID, String hotkey) {
		Stratagem stratagem = stratagemMap.get(stratagemID);
		hotKeyMap.put(hotkey, stratagem.getInputCode());
		stratagem.setHotKey(hotkey);
		saveHotKey();
	}
	
	public int resetHotkey(int stratagemID, String newHotkey) {
		if(newHotkey.equals("Escape")) {
			// not working
			return -1;
		}
		else if(newHotkey.equals("Backspace")) {
			removeHotKey(stratagemID);
			return -2;
		} 
		else if(isAWSD(newHotkey)) {
			return -3;
		}
		else {
			int removedID = checkHasHotKey(newHotkey);
			if(removedID != -4) {
				removeHotKey(removedID);
			}
			setHotKey(stratagemID, newHotkey);
			return removedID;
		}
	}
	
	@SuppressWarnings("unused")
	private void printStratagemMap() {
		System.out.println("StratagemMap--------------------------------------");
		for(Stratagem stratagem: stratagemMap) {
			int id = stratagem.getID();
			String name = stratagem.getName();
			String hotkey = stratagem.getHotkey();
			String inputCode = stratagem.getInputCode();
			
			System.out.println(id + " : " + name + " : " + hotkey + " : " + inputCode);
		}
		System.out.println();
		System.out.println();
	}
	
	@SuppressWarnings("unused")
	private void printHotKeyMap() {
		System.out.println("HoykeyMap-----------------------------");
		for(String key: hotKeyMap.keySet()) {
			String value = hotKeyMap.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println();
		System.out.println();
	}
	
}
