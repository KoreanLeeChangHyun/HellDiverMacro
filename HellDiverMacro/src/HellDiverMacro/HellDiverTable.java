package HellDiverMacro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

public class HellDiverTable extends JPanel{
	private static final long serialVersionUID = 1L;
	Handdler handdler;
	
	JScrollPane[] tableList;
	CustomTable selTable;
	int selIndex = 0; 
	int stratagemCount = 55 + 1;
	
	
	/*
	 * Constructor
	 * In:  (String: hellDiver tag value)
	 */
    public HellDiverTable(String hellDiverTag, Handdler handdler) {
    	this.handdler = handdler;
    	
    	makeTableList(hellDiverTag);
         	
    	this.setLayout(new BorderLayout());
    	selectTable(0);
    	this.revalidate();
    	this.repaint();
	}
    
    private void makeTableList(String hellDiverTag) {
    	tableList = new JScrollPane[4];
    	
    	if(hellDiverTag.equals("")) {
        	tableList[0] = defalutTable(0, 26);
        	tableList[1] = defalutTable(27, 37);
        	tableList[2] = defalutTable(38, 50);
        	tableList[3] = defalutTable(51, 55);
        	((CustomTable)tableList[3].getViewport().getComponent(0)).setCellText("Ctrl", 0, 2);
    	}
    	else {
        	tableList[0] = newTable(hellDiverTag, 0, 26);
        	tableList[1] = newTable(hellDiverTag, 27, 37);
        	tableList[2] = newTable(hellDiverTag, 38, 50);
        	tableList[3] = newTable(hellDiverTag, 51, 55);
    	}
    	
    }
    
    private JScrollPane defalutTable(int startID, int endID) {
    	String header[] = {"Img", "Name", "Hotkey", "InputCode"};
    	JLabel contents[][] = new JLabel[endID - startID + 1][header.length];
		
    	int tableIndex = 0;
		for(int i=startID; i<=endID; i++) {
				
			String name = StratagemList.name[i][0];
			String hotkey = "";
			String inputCode = StratagemList.name[i][1];
		
			JLabel imgLabel = new JLabel();
			try {
				URL imageURL = getClass().getClassLoader().getResource(name.replaceAll("/", "_") + ".png");
				ImageIcon icon = new ImageIcon(imageURL);
				imgLabel.setIcon(icon);
			} catch (Exception e) {
				System.out.println(name.replaceAll("/", "_") + ".png");
			}

			
			// Make Table Label
			JLabel nameLable = new JLabel("  " + name);
			JLabel hotkeyLable = new JLabel("  " + hotkey);
			hotkeyLable.setHorizontalAlignment(JLabel.CENTER);
			JLabel inputCodeLabel = new JLabel("  " + this.getInputCode(inputCode));
			
			contents[tableIndex][0] = imgLabel;
			contents[tableIndex][1] = nameLable;
			contents[tableIndex][2] = hotkeyLable;
			contents[tableIndex][3] = inputCodeLabel;
		
			tableIndex++;
		}
		
		
		CustomTable table = new CustomTable(contents, header);
		this.initTable(table);
		this.initTableEvent(table);
		
		tableReSize(table);
		
		JScrollPane tableScrollPan = new JScrollPane();
		
    	tableScrollPan.setBackground(Color.gray);
    	tableScrollPan.getViewport().setBackground(Color.gray);
    	tableScrollPan.setBorder(new LineBorder(Color.gray));
    	tableScrollPan.getViewport().add(table);
		
		return tableScrollPan;
    }
    
    /*
     * Make HellDiver table with the hellDiver tag
     * In:  (String: hellDiver tag value)
     */
    private JScrollPane newTable(String hellDiverTag, int startID, int endID) {
    	
    	String header[] = {"Img", "Name", "Hotkey", "InputCode"};
    	JLabel contents[][] = new JLabel[endID - startID + 1][header.length];
		
    	int tableIndex = 0;
		for(int i=startID; i<=endID; i++) {
			
			String stratagem = TagParser.getValue(hellDiverTag, String.valueOf(i));
			
			String name = TagParser.getValue(stratagem, "name");
			String hotkey = TagParser.getValue(stratagem, "hotkey");
			String inputCode = TagParser.getValue(stratagem, "inputCode");
			JLabel imgLabel = new JLabel();
			try {
				URL imageURL = getClass().getClassLoader().getResource(name.replaceAll("/", "_") + ".png");
				ImageIcon icon = new ImageIcon(imageURL);
				imgLabel.setIcon(icon);
			} catch (Exception e) {
				System.out.println(name.replaceAll("/", "_") + ".png");
			}

			
			// Make Table Label
			JLabel nameLable = new JLabel("  " + name);
			JLabel hotkeyLable = new JLabel("  " + hotkey);
			hotkeyLable.setHorizontalAlignment(JLabel.CENTER);
			JLabel inputCodeLabel = new JLabel("  " + this.getInputCode(inputCode));
			
			contents[tableIndex][0] = imgLabel;
			contents[tableIndex][1] = nameLable;
			contents[tableIndex][2] = hotkeyLable;
			contents[tableIndex][3] = inputCodeLabel;
		
			tableIndex++;
		}
		
		
		CustomTable table = new CustomTable(contents, header);
		this.initTable(table);
		this.initTableEvent(table);
		
		tableReSize(table);
		
		JScrollPane tableScrollPan = new JScrollPane();
		
    	tableScrollPan.setBackground(Color.gray);
    	tableScrollPan.getViewport().setBackground(Color.gray);
    	tableScrollPan.setBorder(new LineBorder(Color.gray));
    	
    	tableScrollPan.getViewport().add(table);
		
		return tableScrollPan;
    }
    
    private void initTable(CustomTable table) {
    	
    	JTableHeader hearder = table.getTableHeader();
    	
    	table.setGridColor(Color.black);
    	table.setShowVerticalLines(false);
    	hearder.setBackground(Color.gray);
    	hearder.setForeground(Color.white);
    	hearder.setBorder(new LineBorder(Color.gray));
    }
    
    
    /*
     * Init Table event
     */
	private void initTableEvent(CustomTable table) {
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	
		    	if(!handdler.isModefy()) {

			        int row = table.rowAtPoint(evt.getPoint());
			        int col = table.columnAtPoint(evt.getPoint());
			        
			        
			        if(col == 2) {
			        	int id = getMenuID() + row;
			        	startModefiyHotkey(id, row, col);
			        }
		    	}
		    }
		});
	}
	
	private int getMenuID() {
		int result = 0;
		switch(selIndex) {
		case 0 : result = 0; break;
		case 1 : result = 27; break;
		case 2 : result = 38; break;
		case 3 : result = 51; break;
		}
		return result;
	}
	
	private void startModefiyHotkey(int id, int row, int col) {
		selTable.setCellBackground(new Color(39, 137, 29), row, col); 
		
		handdler.resetOneKey();
		handdler.setModifyCell(id, row, col);
		handdler.changeMacroState();		
		handdler.setModfy(true); 
	}
	
	private int getRemovedIndex(int removedIndex) {
		int result = -4;
		if(removedIndex >= 0 && removedIndex < 27) {
			result = removedIndex;
		} else if(removedIndex >= 27 && removedIndex < 38) {
			result = removedIndex - 27;
		} else if(removedIndex >= 38 && removedIndex < 51) {
			result = removedIndex - 38;
		} else if(removedIndex >= 51 && removedIndex < stratagemCount) {
			result = removedIndex - 51;
		}
		return result;
	}
	
	private int getRemovedMenuID(int removedIndex) {
		if(removedIndex >= 0 && removedIndex < 27) {
			return 0;
		} else if(removedIndex >= 27 && removedIndex < 38) {
			return 1;
		} else if(removedIndex >= 38 && removedIndex < 51) {
			return 2;
		} else if(removedIndex >= 51 && removedIndex < stratagemCount) {
			return 3;
		}
		return 0;
	}
	
	public void endModifyHotkey(int row, int col, String hotkey, int removedID) {
		
		
		if(removedID == -1){
			// not Working
		}
		else if(removedID == -2) {
			// Backspace
			selTable.setCellText("", row, col);
		}
		else if(removedID == -3) {
			// AWSD
			selTable.setCellText("Can't use 'AWSD'", row, col);
		}
		else {
			System.out.println(removedID);
			if(removedID != -4) { // already registered key
				int removedIndex = getRemovedIndex(removedID);
				int removedMenuID = getRemovedMenuID(removedID);
				
				// System.out.println(removedIndex + " : " + removedMenuID);
				
				CustomTable table = (CustomTable) tableList[removedMenuID].getViewport().getComponent(0);
				
				table.setCellText(" ", removedIndex, col);
				table.setCellBackground(new Color(154, 12, 19), removedIndex, col);
			}
			selTable.setCellText(hotkey, row, col);
		}
		
		selTable.setCellBackground(Color.WHITE, row, col);
		handdler.setModfy(false);
		handdler.resetOneKey();
		handdler.changeMacroState();
	}
	
	public void setCellBackground(Color color, int row, int col) {
		selTable.setCellBackground(color, row, col);
	}
	
	public void selectTable(int tableIndex) {
		selIndex = tableIndex;
		selTable = (CustomTable) tableList[tableIndex].getViewport().getComponent(0);
		this.removeAll();
		this.add(tableList[tableIndex], BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}
    
	/*
	 * inputCode of 'wasd' change to the arrow
	 * In  : (String: inputCode of 'wasd')
	 * Out : (String: inputCode of arrow)
	 */
    private String getInputCode(String inputCode) {
    	String result = "";
    	
    	for(int i=0; i<inputCode.length(); i++) {
    		result += getArrow(inputCode.charAt(i));
    	}
    	
    	return result;
    }
    
    /*
     * one inputCode of 'wasd' change to the arrow
     * In  : (Char: one code of 'wasd')
     * Out : (char: one code of arrow)
     */
    private char getArrow(char code) {
    	char result = '\0';
    	
    	switch(code) {
    	case 'w': result = 'ก่'; break;
    	case 's': result = 'ก้'; break;
    	case 'a': result = 'ก็'; break;
    	case 'd': result = 'กๆ'; break;
    	}
    	
    	return result;
    }
    
    /*
     * Resize the table
     */
    private void tableReSize(CustomTable table) {
    	resizeColumn(table);
    	resizeRow(table);
    }
    
    /*
     * Resize the column
     */
	private void resizeColumn(CustomTable table) { 
		table.getColumnModel().getColumn(0).setPreferredWidth(52);
		table.getColumnModel().getColumn(1).setPreferredWidth(208);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
	}
	
	/*
	 * Resize the row
	 */
	private void resizeRow(CustomTable table) {
		table.setRowHeight(50);
	}
	
}
