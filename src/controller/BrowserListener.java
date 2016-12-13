package controller;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import view.MutantVizWindow;

public class BrowserListener extends WindowListener implements TreeSelectionListener {
	
	public BrowserListener(MutantVizWindow window) {
		attachWindow(window);
	}
	
	@Override
	/**
	 * React to a selection being made in the browser;
	 * Updates the view to something relevant to that node, for example
	 * a view of the code, or a summary of the folder.
	 */
	public void valueChanged(TreeSelectionEvent e)
	{
		window.setNode((DefaultMutableTreeNode) e.getPath().getLastPathComponent());
	}
}
