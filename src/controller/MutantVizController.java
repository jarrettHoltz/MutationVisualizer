package controller;

import model.MutantVizModel;

/**
 * Generic Interface for the Mutant Visualizer controller
 * @author jaholtz
 *
 */
public interface MutantVizController {
	/**
	 * 
	 * @param model, the MutantVizModel to fill
	 * @param mut_dir, directory for mutation analysis
	 * @param source_dir, directory for source code
	 * @param test_dir, directory for test code
	 */
	public void buildModel(MutantVizModel model, 
						   String mut_dir, 
						   String source_dir,
						   String test_dir);

}
