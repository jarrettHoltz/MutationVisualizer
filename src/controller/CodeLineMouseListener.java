package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.CodeLine;
import view.MutantVizWindow;

public class CodeLineMouseListener extends WindowListener implements MouseListener {
	public CodeLineMouseListener(MutantVizWindow window) {
		attachWindow(window);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source instanceof CodeLine) {
			window.setComparison((CodeLine)source);			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
