package HellDiverMacro;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HellDiverMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	private Handdler handdler;
	
	private JLabel supplyBtn;
	private JLabel offensiveBtn;
	private JLabel defensiveBtn;
	private JLabel specialBtn;
	private JLabel macroLabel;
	
	/*
	 * Constructor
	 */
	public HellDiverMenu(Handdler handdler) {
		initPanel(handdler);
		initObject();
		initEvent();
	}
	
	private void initPanel(Handdler handdler) {
		this.handdler = handdler;
		this.setLayout(new GridLayout(1, 3));
		this.setBackground(Color.black);
	}
	
	/*
	 * init Menu Object
	 */
	private void initObject() {
		
		// SupplyBtn
		supplyBtn = new JLabel();
		supplyBtn.setText("Supply");
		supplyBtn.setHorizontalAlignment(JLabel.CENTER);
		supplyBtn.setOpaque(true);
		supplyBtn.setBackground(new Color(70, 103, 109));
		supplyBtn.setForeground(Color.WHITE);
		this.add(supplyBtn);
		
		// DefensiveBtn
		defensiveBtn = new JLabel();
		defensiveBtn.setText("Defensive");
		defensiveBtn.setHorizontalAlignment(JLabel.CENTER);
		defensiveBtn.setOpaque(true);
		defensiveBtn.setBackground(new Color(124, 135, 96));
		defensiveBtn.setForeground(Color.WHITE);
		this.add(defensiveBtn);
		
		// OffensiveBtn
		offensiveBtn = new JLabel();
		offensiveBtn.setText("Offensive");
		offensiveBtn.setHorizontalAlignment(JLabel.CENTER);
		offensiveBtn.setOpaque(true);
		offensiveBtn.setBackground(new Color(106, 76, 70));
		offensiveBtn.setForeground(Color.WHITE);
		this.add(offensiveBtn);
		
		// SpecialBtn
		specialBtn = new JLabel();
		specialBtn.setText("Special");
		specialBtn.setHorizontalAlignment(JLabel.CENTER);
		specialBtn.setOpaque(true);
		specialBtn.setBackground(new Color(200, 160, 74));
		specialBtn.setForeground(Color.WHITE);
		this.add(specialBtn);
		
		// MacroLabel
		macroLabel = new JLabel(); 
		macroLabel.setText("Running");
		macroLabel.setHorizontalAlignment(JLabel.CENTER);
		macroLabel.setOpaque(true);
		macroLabel.setBackground(new Color(69, 143, 79));
		macroLabel.setForeground(Color.WHITE);
		this.add(macroLabel);
		
	}
	
	/*
	 * init menu Object event
	 */
	private void initEvent() {
		
		// SupplyBtn
		supplyBtn.addMouseListener(new MouseAdapter() {
			@Override // Hover In
			public void mouseEntered(MouseEvent e) {
				supplyBtn.setBackground(Color.GRAY);
			}
			@Override // Hover Out
			public void mouseExited(MouseEvent e) {
				supplyBtn.setBackground(new Color(70, 103, 109));
			}	
			public void mouseClicked(MouseEvent e) {
				handdler.selectTable(0);
			}
		});
		
		// DefensiveBtn
		defensiveBtn.addMouseListener(new MouseAdapter() {
			@Override // Hover In
			public void mouseEntered(MouseEvent e) {
				defensiveBtn.setBackground(Color.GRAY);
			}
			@Override // Hover Out
			public void mouseExited(MouseEvent e) {
				defensiveBtn.setBackground(new Color(124, 135, 96));
			}	
			public void mouseClicked(MouseEvent e) {
				handdler.selectTable(1);
			}
		});
		
		// OffensiveBtn
		offensiveBtn.addMouseListener(new MouseAdapter() {
			@Override // Hover In
			public void mouseEntered(MouseEvent e) {
				offensiveBtn.setBackground(Color.GRAY);
			}
			@Override // Hover Out
			public void mouseExited(MouseEvent e) {
				offensiveBtn.setBackground(new Color(106, 76, 70));
			}	
			public void mouseClicked(MouseEvent e) {
				handdler.selectTable(2);
			}
		});
		
		// SpecialBtn
		specialBtn.addMouseListener(new MouseAdapter() {
			@Override // Hover In
			public void mouseEntered(MouseEvent e) {
				specialBtn.setBackground(Color.GRAY);
			}
			@Override // Hover Out
			public void mouseExited(MouseEvent e) {
				specialBtn.setBackground(new Color(200, 160, 74));
			}	
			public void mouseClicked(MouseEvent e) {
				handdler.selectTable(3);
			}
		});
		
		// MacroLabel
		macroLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				handdler.changeMacroState();
			}
		});
		
	}
	
	public void showMacroState(boolean isRun) {
		if(isRun) {
			macroLabel.setBackground(new Color(69, 143, 79));
			macroLabel.setText("Running");
		} else {
			macroLabel.setBackground(Color.DARK_GRAY);
			macroLabel.setText("Stoped");
		}
		
	}
}
