package HellDiverMacro;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class DraggeListener extends MouseAdapter implements MouseMotionListener {
	boolean isFrame;
	
	JFrame frame;
	
	JComponent ParentPanel;
	JComponent movePanel;
	
	int offx = 0;
	int offy = 0;
	
	public DraggeListener(JComponent ParentPanel, JComponent movePanel) {
		this.ParentPanel = ParentPanel;
		this.movePanel = movePanel;
		isFrame = false;
	}
	
	public DraggeListener(JFrame frame) {
		this.frame = frame;
		isFrame = true;
	}
	
	public void mousePressed(MouseEvent e){
		if(isFrame) {
			framePressed(e);
		}
		else {
			panelPressed();
		}
	}
	
	public void mouseDragged(MouseEvent e){
		if(isFrame) {
			frameDragged(e);
		}
		else {
			panelDragged();
		}
	}
	
	private void framePressed(MouseEvent e) {
		int realX = frame.getX();
		int realY = frame.getY();
		
		int pointX = e.getXOnScreen();
		int pointY = e.getYOnScreen();
		
		offx = pointX - realX;
		offy = pointY - realY;	
	}
	
	private void frameDragged(MouseEvent e) {
		try {
			int pointX = e.getXOnScreen();
			int pointY = e.getYOnScreen();
			
			frame.setLocation(pointX - offx, pointY - offy);
		} catch (Exception err) {
			System.out.println("�Ÿ��� ���");
		}
	}
	
	
	/* Parent Panel, Move Panel */
	private void panelPressed() {
		int realX = movePanel.getX(); 
		int realY = movePanel.getY();
		
		int pointX = ParentPanel.getMousePosition().x;
		int pointY = ParentPanel.getMousePosition().y;
		
		offx = pointX - realX;
		offy = pointY - realY;
	}
	
	private void panelDragged() {
		try {
			int pointX = ParentPanel.getMousePosition().x;
			int pointY = ParentPanel.getMousePosition().y;
			
			movePanel.setLocation(pointX - offx, pointY - offy);
		} catch (Exception err) {
			System.out.println("�Ÿ��� ���");
		}
	}
	
}


