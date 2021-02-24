package HellDiverMacro;

class Stratagem{
	private int id;
	private String name;
	private String inputCode;
	private String hotkey;
	
	public Stratagem(int id, String name, String hotKey, String inputCode) {
		this.id = id;
		this.name = name;
		this.hotkey = hotKey;
		this.inputCode = inputCode;
	}
	
	public void setHotKey(String hotkey) {
		this.hotkey = hotkey;
	}
	
	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getInputCode() {
		return inputCode;
	}

	public String getHotkey() {
		return hotkey;
	}

}