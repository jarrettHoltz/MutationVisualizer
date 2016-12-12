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
	private int mutantId;
	private MutatorType mutator;
	private String status;
	private String className;
	private String methodName;
	private int lineNumber;
	private String mutantSource;
	private ArrayList<Test> tests = new ArrayList<Test>();
	
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


	//Accessors
	public int getMutantId(){
		return this.mutantId;
	}

	public MutatorType getMutator(){
		return this.mutator;
	}

	public String getStatus(){
		return this.status;
	}

	public String getClassName(){
		return this.className;
	}

	public String getMethodName(){
		return this.methodName;
	}

	public int getLineNumber(){
		return this.lineNumber;
	}

	public String getMutantSource(){
		return this.mutantSource;
	}

	public ArrayList<Test> getMutatorTests(){
		return this.tests;
	}



	//Mutators
	// public void setMutantId(int newMutantId){
	// 	this.mutantId = newMutantId;
	// }

	// public void setMutator(MutatorType newMutator){
	// 	this.mutator = newMutator;
	// }

	public void setStatus(String newStatus){
		this.status = newStatus;
	}

	// public void setClassName(String newClassName){
	// 	this.className = newClassName;
	// }

	// public void setMethodName(String newMethodName){
	// 	this.methodName = newMethodName;
	// }

	// public void setLineNumber(int newLineNumber){
	// 	this.lineNumber = newLineNumber;
	// }

	// public void setMutantSource(String newMutantSource){
	// 	this.mutantSource = newMutantSource;
	// }

	// public void setTests(ArrayList<Test> newTests){
	// 	this.tests = newTests;
	// }


}
