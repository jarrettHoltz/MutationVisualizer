package model;
import java.util.ArrayList;

/**
 * SourceClass contains the text of a class from the mutated
 * source, a list of mutants for the class, and the class name
 * @author jaholtz
 *
 */
public class SourceClass extends SummaryNode {
	// Storage of entire code base
	private String source;
	private ArrayList<Integer> mutants;
	
	// Map of line# to mutant# or pointers to mutants
	
	// map of line# to test# or pointer to test 
	
	public SourceClass(String className, String source_string, Summary summary) {
		this.name = className;
		this.source = source_string;
		this.summary = summary;
		this.mutants = new ArrayList<Integer>();
	}
	
	public void AddMutant(int mutant_id) {
		mutants.add(mutant_id);
	}
	
	public String toString() {
		return name + ".java";
	}


	//Accessors
	public String getSource(){
		return this.source;
	}

	public ArrayList<Integer> getMutants(){
		return this.mutants;
	}


	//Mutators
	public void setSource(String newSource){
		this.source = newSource;
	}

	public void setMutants(ArrayList<Integer> newMutants){
		return this.mutants = newMutants;
	}


}
