package HellDiverMacro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HellDiverFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	JFrame frame;
	
	// head
	JPanel head;
	JLabel minimizeBtn;
	JLabel closeBtn;
	
	// body
	JPanel body;
	HellDiverTitle title;
	HellDiverMenu menu;
	HellDiverTable table;
	
	// foot
	JPanel foot;

	/*
	 * Constructor
	 */
	public HellDiverFrame(HellDiverTitle title, HellDiverMenu menu, HellDiverTable table) {
		initFrame();
		
		inithead();
		initheadEvent();
		
		initBody(title, menu, table);
		
		initFooter();
		
		addComponent();
	}
	
	/*
	 * init frame setting
	 */
	private void initFrame() {
		frame = this;
		setBackground(Color.black);
		setSize(500, 500);
		setAlwaysOnTop(true);
		setResizable(false);
		setUndecorated(true);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/*
	 * init head setting & Object
	 */
	private void inithead() {
		head = new JPanel();
		head.setLayout(null);
		head.setLayout(new BorderLayout());
		head.setBackground(Color.black);
		
		// Top bar panel
		JPanel pan = new JPanel();
		pan.setBackground(Color.black);
		pan.setLayout(new GridLayout(1, 2));
		head.add(pan, BorderLayout.EAST);
		head.revalidate();
		head.repaint();
		
		// MinimizeBtn
		minimizeBtn = new JLabel(" ¤Ñ ");
		minimizeBtn.setBackground(Color.black);
		minimizeBtn.setOpaque(true);
		minimizeBtn.setHorizontalAlignment(JLabel.CENTER);
		minimizeBtn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		minimizeBtn.setForeground(Color.WHITE);
		pan.add(minimizeBtn);
		pan.revalidate();
		pan.repaint();
		
		// CloseBtn
		closeBtn = new JLabel(" x ");
		closeBtn.setBackground(Color.black);
		closeBtn.setOpaque(true);
		closeBtn.setHorizontalAlignment(JLabel.CENTER);
		closeBtn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		closeBtn.setForeground(Color.WHITE);
		pan.add(closeBtn);
		pan.revalidate();
		pan.repaint();
	}
	
	/*
	 * init Top bar buttom event
	 */
	private void initheadEvent() {
		
		DraggeListener draggeListener = new DraggeListener(frame);
		head.addMouseListener(draggeListener);
		head.addMouseMotionListener(draggeListener);
		
		minimizeBtn.addMouseListener(new MouseAdapter() {
			@Override // Hover In
			public void mouseEntered(MouseEvent e) {
				minimizeBtn.setBackground(Color.RED);
			}
			@Override // Hover Out
			public void mouseExited(MouseEvent e) {
				minimizeBtn.setBackground(Color.black);
			}	
			@Override
			public void mousePressed(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
		});
		
		closeBtn.addMouseListener(new MouseAdapter() {
			@Override // Hover In
			public void mouseEntered(MouseEvent e) {
				closeBtn.setBackground(Color.RED);
			}
			@Override //  Hover Out
			public void mouseExited(MouseEvent e) {
				closeBtn.setBackground(Color.black);
			}	
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
	}
	
	/*
	 * init body setting & object
	 */
	private void initBody(HellDiverTitle title, HellDiverMenu menu, HellDiverTable table) {
		body = new JPanel();
		body.setLayout(new BorderLayout());
		
		JPanel bodyheader = new JPanel();
		bodyheader.setLayout(new BorderLayout());
		JPanel bodyContainer = new JPanel();
		bodyContainer.setLayout(new BorderLayout());
		
		bodyheader.add(title, BorderLayout.NORTH);
		bodyheader.add(menu, BorderLayout.CENTER);
		bodyContainer.add(table);
		
		body.add(bodyheader, BorderLayout.NORTH);
		body.add(bodyContainer, BorderLayout.CENTER);
		
		body.revalidate();
		body.repaint();
	}
	
	/*
	 * init Foot setting
	 */
	private void initFooter() {
		JLabel copyright = new JLabel("Copyright (c) By qoxmf135(streamID) (No redistribution)");
		copyright.setForeground(Color.GRAY);
		
		foot = new JPanel();
		foot.setBackground(Color.black);
		foot.add(copyright);
		foot.revalidate();
		foot.repaint();
	}
	
	/*
	 * Add head, body, foot
	 */
	private void addComponent() {
		
		this.add(head, BorderLayout.NORTH);
		this.add(body, BorderLayout.CENTER);
		this.add(foot, BorderLayout.SOUTH);
		this.revalidate();
		this.repaint();
	}
}
