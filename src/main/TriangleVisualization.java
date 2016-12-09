package main;

import javax.swing.ToolTipManager;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.TriangleParser;
import model.TriangleModel;
import view.TriangleWindow;

public class TriangleVisualization {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TriangleModel model = new TriangleModel();
		TriangleParser parser = new TriangleParser();
		parser.BuildModel(model,"triangle/mutation_results", "triangle/src", "triangle/test" );
		TriangleWindow view = new TriangleWindow(model);
		//Make hovering over summary bar segments more responsive
		ToolTipManager.sharedInstance().setInitialDelay(100); //Default is 750ms
	}

}
