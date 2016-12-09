package model;
import java.util.ArrayList;

/**
 * Mutant objects contain the information from the
 * mutant log. The mutated full class code,
 * status of the mutant, by what method it was killed, 
 * by what tests, and the line number.
 * @author jaholtz
 *
 */
public class Mutant {
	public int mutantId;
	public MutatorType mutator;
	public String status;
	public String className;
	public String methodName;
	public int lineNumber;
	public String mutantSource;
	public ArrayList<Test> tests = new ArrayList<Test>();
	
	public Mutant(int mutant_id,
			MutatorType mutator,
			String class_name,
			String method_name,
			int line_number,
			String mutant_source) {
		
		this.mutantId = mutant_id;
		this.mutator = mutator;
		this.className = class_name;
		this.methodName = method_name;
		this.lineNumber = line_number;
		this.mutantSource = mutant_source;
		this.status = "unknown";
	}
	
	/**
	 * Adds a test to the mutant
	 * @param test
	 */
	public void AddTest(Test test) {
		tests.add(test);
	}
	
	public String toString() {
		return mutantId + " " + className + " (" + mutator + ") " + status;
	}
}
