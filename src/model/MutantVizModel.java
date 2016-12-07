package model;
import java.util.ArrayList;


/**
 * Generic interface for the model component of a mutant visualizer
 * @author jaholtz
 *
 */
public interface MutantVizModel {
	
	
	/**
	 * Sets the summary object for the mutant viz model
	 */
	public void SetSummary(Summary summary);

	/**
	 * Adds Mutants to the model
	 * @param mutant
	 */
	public void AddMutant(Mutant mutant);
	
	/**
	 * Add a test to the model
	 * @param test
	 */
	public void AddTest(Test test);
	
	/**
	 * Adds a source class to the model
	 * @param source
	 */
	public void AddSource(SourceClass source);
	
	/**
	 * 
	 * @return The Summary Object for the entire mutation dataset
	 */
	public Summary GetSummary();
	
	/**
	 * 
	 * @param test_id, id of the test
	 * @return Test object pertaining to id
	 */
	public Test GetTest(int test_id);
	/**
	 * 
	 * @param mutant_id
	 * @return list of tests which cover this mutant
	 */
	public ArrayList<Integer> GetTests(int mutant_id);
	
	/**
	 * 
	 * @param class_name
	 * @param line_num
	 * @return List of test ids that cover the line number in the class
	 */
	public ArrayList<Integer> GetTests(String class_name, int line_num);
	/**
	 * 
	 * @param mutant_id
	 * @return Mutant object corresponding to the mutant_id
	 */
	public Mutant GetMutant(int mutant_id);
	/**
	 * 
	 * @param class_name
	 * @param line_number
	 * @return List of mutant ids for the given class and line_number
	 */
	public ArrayList<Mutant> GetMutants(String class_name, int line_number);
	/**
	 * 
	 * @param test_id
	 * @return list of mutant_ids corresponding to the test_id
	 */
	public ArrayList<Integer> GetMutants(int test_id);
	/**
	 * 
	 * @param class_name
	 * @return SourceClass object corresponding to the class_name
	 */
	public SourceClass GetSourceClass(String class_name);
	/**
	 * 
	 * @param mutant_id
	 * @return SourceClass object corresponding to mutant_id
	 */
	public SourceClass GetSource(int mutant_id);
	
	/**
	 * Gets the string associated with the line_number in the given class
	 * @param class_name
	 * @param line_number
	 * @return
	 */
	public String GetLine(String class_name, int line_number);
	
	/**
	 * Get the mutator object for the given mutator string
	 * @param mutator
	 * @return
	 */
	public Mutator GetMutator(String mutator);
	
	/**
	 * Get the mutator object associated with the given mutant
	 * @param mutant_id
	 * @return
	 */
	public Mutator GetMutator(int mutant_id);
}
