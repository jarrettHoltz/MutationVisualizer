package main;

import controller.TriangleParser;
import model.TheModel;
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
		TheModel.model = model;
		TriangleWindow view = new TriangleWindow();
	}

}
