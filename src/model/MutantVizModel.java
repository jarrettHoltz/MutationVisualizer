package model;
import java.util.List;

import javax.swing.tree.TreeNode;


/**
 * Generic interface for the model component of a mutant visualizer
 * @author jaholtz
 *
 */
public interface MutantVizModel {
	
	
	/**
	 * Sets the summary object for the mutant viz model
	 */
	public void setSummary(Summary summary);

	/**
	 * 
	 * @return The Summary Object for the entire mutation dataset
	 */
	public Summary getSummary();
	
	/**
	 * Adds a source class to the model
	 * @param source
	 */
	public void AddSource(SourceClass source);

	/**
	 * Adds Mutants to the model
	 * @param mutant
	 */
	public void addMutant(Mutant mutant);
	
	/**
	 * 
	 * @param mutant_id
	 * @return Mutant object corresponding to the mutant_id
	 */
	public Mutant getMutant(int mutant_id);

	/**
	 * Sets the root node of the source tree (e.g. the src directory).
	 * @param sourceRoot a DefaultMutableTreeNode containing the SourceDirectory for the root of the source file tree.
	 */
	public void setSourceRoot(TreeNode sourceRoot);
	
	/**
	 * Gets the root node of the source tree (e.g. the src directory).
	 * @return a DefaultMutableTreeNode containing the SourceDirectory for the root of the source file tree.
	 */
	public TreeNode getSourceRoot();
	
	/**
	 * Sets the root node of the test tree (e.g. the test directory).
	 * @param testRoot a DefaultMutableTreeNode containing the SourceDirectory for the root of the test file tree.
	 */
	public void setTestRoot(TreeNode testRoot);
	
	/**
	 * Gets the root node of the test tree (e.g. the test directory).
	 * @return a DefaultMutableTreeNode containing the SourceDirectory for the root of the test file tree.
	 */
	public TreeNode getTestRoot();
	
	/**
	 * TODO: fix this
	 * Sets the root node of the mutant tree.
	 * @param mutantRoot a DefaultMutableTreeNode containing the SourceDirectory for the root of the mutant tree.
	 */
	public void setMutantRoot(TreeNode mutantRoot);
	
	/**
	 * TODO: fix this
	 * Gets the root node of the mutant tree.
	 * @return a DefaultMutableTreeNode containing the SourceDirectory for the root of the mutant tree.
	 */
	public TreeNode getMutantRoot();
	
	/**
	 * 
	 * @param class_name
	 * @param line_num
	 * @return List of test ids that cover the line number in the class
	 */
	public List<Test> getTests(String class_name, int line_num);
	
	/**
	 * 
	 * @param class_name
	 * @param line_number
	 * @return List of mutant ids for the given class and line_number
	 */
	public List<Mutant> getMutants(String class_name, int line_number);
	
	/**
	 * 
	 * @param test_id
	 * @return list of mutant_ids corresponding to the test_id
	 */
	public List<Mutant> getMutants(int test_id);
	
	/**
	 * 
	 * @param class_name
	 * @return SourceClass object corresponding to the class_name
	 */
	public SourceClass getSourceClass(String class_name);
	
	/**
	 * 
	 * @param mutant_id
	 * @return SourceClass object corresponding to mutant_id
	 */
	public SourceClass getSource(int mutant_id);
	
	/**
	 * Gets the string associated with the line_number in the given class
	 * @param class_name
	 * @param line_number
	 * @return
	 */
	public String getLine(String class_name, int line_number);
	
	/**
	 * Get the mutator object for the given mutator string
	 * @param mutator
	 * @return
	 */
	public Mutator getMutator(MutatorType mutator);
	
	/**
	 * Get the mutator object associated with the given mutant
	 * @param mutant_id
	 * @return
	 */
	public Mutator getMutatorForMutant(int mutant_id);
}
