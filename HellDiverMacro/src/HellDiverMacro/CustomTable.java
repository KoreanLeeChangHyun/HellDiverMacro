package HellDiverMacro;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

class TablePoint{
	int id = -1;
	int row = -1;
	int col = -1;
	
	public void setPoint(int id, int row, int col) {
		this.id = id;
		this.row = row;
		this.col = col;
	}
	
	public int getID() {
		return id;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
}

class CustomDefaultTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	
	CustomDefaultTableModel(JLabel[][] contents, String[] header){
		super(contents, header);
	}
	
	@Override
	public boolean isCellEditable(int i, int c) { 
		return false; 
	}
	
}

class CustomTableCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean 
	isSelected, boolean hasFocus, int row, int column) {
		// Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(isSelected) {
        }
		
		if(value instanceof JLabel){
            //This time return only the JLabel without icon
             return (JLabel)value;
        }
        else {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
	}
}

 

public class CustomTable extends JTable{
	private static final long serialVersionUID = 1L;
	
	CustomTable(JLabel[][] contents, String[] header) {
		
		CustomTableCellRenderer renderer = new CustomTableCellRenderer();
		CustomDefaultTableModel model = new CustomDefaultTableModel(contents, header);
		
		this.setModel(model);
		this.setDefaultRenderer(JLabel.class, renderer);
		this.setFocusable(false);
		this.setCellSelectionEnabled(false);
	}
	
	@Override
	public Class<JLabel> getColumnClass(int column){
        return JLabel.class;
    }
	
	public JLabel getCell(int row, int col) {
		return (JLabel)this.getValueAt(row, col);
	}
	
	public String getCellText(int row, int col) {
		JLabel label = (JLabel)this.getValueAt(row, col);
		return label.getText();
	}
	
	public void setCellText(String text, int row, int col) {
		JLabel label = (JLabel)this.getValueAt(row, col);
		
		label.setText(text);
		reView();
	}
	
	public void setCellBackground(Color color, int row, int col) {
		JLabel label = (JLabel)this.getValueAt(row, col);
		
		label.setOpaque(true);
		label.setBackground(color);
		reView();
	}
	
	public void reView() {
		this.setBackground(Color.BLUE);
		this.setBackground(Color.WHITE);
	}
}
