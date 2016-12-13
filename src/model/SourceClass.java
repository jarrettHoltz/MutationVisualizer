package model;
import java.util.ArrayList;

/**
 * SourceClass contains the text of a class from the mutated
 * source, a list of mutants for the class, and the class name
 * @author jaholtz
 */
public class SourceClass extends SummaryNode implements SourceCode {
	// Storage of entire code base
	private String source;
	private ArrayList<Mutant> mutants;
	// If the information were available, list of covering/killing tests
	
	public SourceClass(String className, String source_string, Summary summary) {
		this.name = className;
		this.source = source_string;
		this.summary = summary;
		this.mutants = new ArrayList<Mutant>();
	}
	
	public void AddMutant(Mutant mutant) {
		mutants.add(mutant);
	}
	
	@Override
	public String toString() {
		return name + ".java";
	}

	//Accessors
	@Override
	public String getSource(){
		return this.source;
	}

	public ArrayList<Mutant> getMutants(){
		return this.mutants;
	}

	//Mutators
	// public void setSource(String newSource){
	// 	this.source = newSource;
	// }

	// public void setMutants(ArrayList<Integer> newMutants){
	// 	this.mutants = newMutants;
	// }


}
