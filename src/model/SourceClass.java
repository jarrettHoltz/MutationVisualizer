package model;
import java.util.ArrayList;

/**
 * SourceClass contains the text of a class from the mutated
 * source, a list of mutants for the class, and the class name
 * @author jaholtz
 *
 */
public class SourceClass {
	public Summary summary;
	public String ClassName;
	// Storage of entire code base
	public String source;
	public ArrayList<Integer> mutants = new ArrayList<Integer>();
	
	// Map of line# to mutant# or pointers to mutants
	
	// map of line# to test# or pointer to test 
	
	public SourceClass(String ClassName, String source_string, Summary summary) {
		this.ClassName = ClassName;
		this.source = source_string;
		this.summary = summary;
		this.mutants = new ArrayList<Integer>();
	}
	
	public void AddMutant(int mutant_id) {
		mutants.add(mutant_id);
	}
}
