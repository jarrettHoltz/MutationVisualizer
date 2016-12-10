package model;
import java.util.List;
import java.util.ArrayList;

/**
 * Test object contains a string for the test code
 * the mutants covered/killed by the test,
 * and a summary object.
 * @author jaholtz
 *
 */
public class Test extends SummaryNode {
	private String source;
	private List<Mutant> mutants;
	
	public Test(String testName, String source, Summary summary) {
		this.name = testName;
		this.source = source;
		this.summary = summary;
		this.mutants = new ArrayList<Mutant>();
	}
	
	public void AddMutant(Mutant mutant) {
		mutants.add(mutant);
	}
	
	public String toString() {
		return name + ".java";
	}


	//Accessors
	public String getSource(){
		return this.source;
	}

	public List<Mutant> getMutants(){
		return this.mutants;
	}


	//Mutators
	public void setSource(String newSource){
		this.source = newSource;
	}

	public void setMutants(List<Mutant> newMutants){
		return this.mutants = newMutants;
	}

}
