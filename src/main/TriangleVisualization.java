package main;

import javax.swing.ToolTipManager;
import javax.swing.UIManager;

import controller.BrowserListener;
import controller.CodeLineMouseListener;
import controller.CollapsiblePanelListener;
import controller.TriangleParser;
import model.TriangleModel;
import view.MutantVizWindow;

public class TriangleVisualization {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	    } 
	    catch (Exception e) {
			//This should really never happen
			e.printStackTrace();
	    }

		TriangleModel model = new TriangleModel();
		TriangleParser parser = new TriangleParser();
		parser.buildModel(model,"triangle/mutation_results", "triangle/src", "triangle/test" );
		MutantVizWindow view = new MutantVizWindow(model);
		addListeners(view);
		//Make hovering over summary bar segments more responsive
		ToolTipManager.sharedInstance().setInitialDelay(100); //Default is 750ms
	}
	
	private static void addListeners(MutantVizWindow view) {
		view.setTreeSelectionListener(new BrowserListener(view));
		view.setActionListener(new CollapsiblePanelListener(view));
		view.setMouseListener(new CodeLineMouseListener(view));
	}
}
