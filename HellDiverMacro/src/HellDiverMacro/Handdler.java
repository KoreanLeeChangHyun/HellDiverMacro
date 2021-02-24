package HellDiverMacro;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;


public class Handdler extends State{
	
	// view
	HellDiverFrame hellDiverFrame;
	HellDiverTitle title;
	HellDiverMenu menu;
	HellDiverTable table;
	
	// data set
	HellDiverMap map;
	
	// tool
	HellDiverDAO hellDiverDAO;
	MacroActor macroActor;
	
	public Handdler(){
		initObject();
		initHooker();
	}
	
	/*
	 * init object for helldiver app
	 */
	private void initObject() {	
		// strFileRW = new StrFileRW();
		modifyCell = new TablePoint();
		hellDiverDAO = new HellDiverDAO();
		
		String hellDiverTag = hellDiverDAO.getHellDiverTag();
		
		macroActor = new MacroActor();
		map = new HellDiverMap(hellDiverTag, this);
				 
		
		title = new HellDiverTitle();
		menu = new HellDiverMenu(this);
		table = new HellDiverTable(hellDiverTag, this);
		
		hellDiverFrame = new HellDiverFrame(title, menu, table);
	}
	
	/*
	 * 
	 */
	private void initHooker() {
		// Clear previous logging configurations.
		LogManager.getLogManager().reset();

		// Get the logger for "org.jnativehook" and set the level to off.
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		
		try { 
			GlobalScreen.registerNativeHook(); 
		} 
		catch (NativeHookException ex) { 
			System.err.println("There was a problem registering the native hook."); 
			System.err.println(ex.getMessage()); System.exit(1); 
		} 
		
		GlobalScreen.addNativeMouseListener(new MouseHooker()); 
		GlobalScreen.addNativeKeyListener(new KeyHooker(this));
	}
	
	
	
	public void writeConfig(String tag, String path) {
		hellDiverDAO.saveHellDiverTag(tag);
	}
	
	/*
	 * Set the cell which modify the hotkey
	 * In (int: stratagem ID) (int: table row) (int: table column) 
	 */
	public void setModifyCell(int id, int row, int col) {
		modifyCell.setPoint(id, row, col);
	}
	
	/*
	 * Modify the hotkey 
	 */
	public void modefiyHotKey(String hotkey) {
		int id = modifyCell.getID();
		int row = modifyCell.getRow();
		int col = modifyCell.getCol();
		
		// If new hotkey is already used key, pash hotkey have to removed in hotkeyMap
		int removedIndex = map.resetHotkey(id, hotkey);
		
		table.endModifyHotkey(row, col, hotkey, removedIndex);
	}
	
	/*
	 * 
	 */
	public void changeMacroState() {
		
		if(this.run == true) {
			this.run = false;
		} else {
			this.run = true;
		}
		
		menu.showMacroState(this.run);
	}
	
	public void actMacro(String keyCode) {

		String inputCode = map.getInputCode(keyCode);
		System.out.println("ActCode: " + inputCode);
		if(inputCode != null) {
			macroActor.actMacro(inputCode);
		}
	}
	
	public void selectTable(int tableIndex) {
		table.selectTable(tableIndex);
	}
	
}
