package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MutantVizWindow;

public class CollapsiblePanelListener extends WindowListener implements ActionListener {
	public CollapsiblePanelListener(MutantVizWindow window) {
		attachWindow(window);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		System.out.println("Button pressed: " + command);
		CollapsiblePanelAction action = CollapsiblePanelAction.valueOf(command);
		switch(action) {
			case EXPAND_CODE:
				window.setCodeView();
				break;
			case EXPAND_SUMMARY:
				window.setSummaryView(true);
		case EXPAND_COMPARISON:
			//TODO
			break;
		}
	}
}
