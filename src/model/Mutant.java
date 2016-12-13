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
public class Mutant implements SourceCode {
	private int mutantId;
	private MutatorType mutator;
	private MutantStatus status;
	private String className;
	private String methodName;
	private int lineNumber;
	private String mutantSource;
	private String fullSource;
	private ArrayList<Test> tests = new ArrayList<Test>();
	
	public Mutant(int mutantId,
			MutatorType mutator,
			String className,
			String methodName,
			int lineNumber,
			String mutantSource) {
		
		this.mutantId = mutantId;
		this.mutator = mutator;
		this.className = className;
		this.methodName = methodName;
		this.lineNumber = lineNumber;
		this.fullSource = mutantSource;
		this.mutantSource = mutantSource.split("\n")[lineNumber-1];
		this.status = null;
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

	public MutantStatus getStatus(){
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

	/*
	 * returns just the line where the mutant occurs
	 * @see model.SourceCode#getSource()
	 */
	public String getSource(){
		return this.mutantSource;
	}

	/*
	 * returns the entire code with the mutant embedded
	 */
	public String getFullSource(){
		return fullSource;
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

	public void setStatus(MutantStatus newStatus){
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
