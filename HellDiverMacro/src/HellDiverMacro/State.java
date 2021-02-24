package HellDiverMacro;

abstract class State{

	protected boolean run = true;
	private boolean modify = false;
	protected TablePoint modifyCell;
	
	private int onekey = -1;
	
	public int getOneKey() {
		return onekey;
	}
	
	public void setOneKey(int onekey) {
		this.onekey = onekey;
	}
	
	public void resetOneKey() {
		onekey = -1;
	}
	
	public boolean isRun() {
		return run;
	}
	
	public void setModfy(boolean modify) {
		this.modify = modify;
	}
	
	public boolean isModefy() {
		return modify;
	}
}
