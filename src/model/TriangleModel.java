package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.TreeNode;


public class TriangleModel implements MutantVizModel {
	private Map<String,SourceClass> sources;
	private List<Test> tests;
	private List<Mutant> mutants;
	private Map<MutatorType, Mutator> mutators;
	private Summary summary;
	
	/**
	 * Trees containing the various source code files, tests, and mutants.
	 * This is convenient for display by the View, but never needs to be recalculated,
	 * and is technically just an alternate view-independent representation of the data.
	 */
	private TreeNode sourceRoot;
	private TreeNode testRoot;
	private TreeNode mutantRoot;
	
	/**
	 * Constructor
	 */
	public TriangleModel() {
		mutants = new ArrayList<Mutant>();
		sources = new HashMap<String,SourceClass>();
		tests = new ArrayList<Test>();
		mutators = new HashMap<MutatorType, Mutator>();
		for(MutatorType type : MutatorType.values()) {
			mutators.put(type, new Mutator(type.name()));
		}
	}
	
	@Override
	public Summary getSummary() {
		return this.summary;
	}

	@Override
	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	@Override
	public void AddSource(String path, SourceClass source) {
		sources.put(path, source);
	}
	
	public void AddTest(Test test) {
		tests.add(test);
	}
	
	@Override
	public void addMutant(Mutant mutant) {
		mutants.add(mutant);
		mutators.get(mutant.getMutator()).addMutant(mutant);
		SourceClass source = mutant.getSourceClass();
		source.AddMutant(mutant);
	}

	@Override
	public void setSourceRoot(TreeNode sourceRoot) {
		this.sourceRoot = sourceRoot;
	}
	
	@Override
	public TreeNode getSourceRoot() {
		return sourceRoot;
	}
	
	@Override
	public void setTestRoot(TreeNode testRoot) {
		this.testRoot = testRoot;
	}
	
	@Override
	public TreeNode getTestRoot() {
		return testRoot;
	}
	
	@Override
	public void setMutantRoot(TreeNode mutantRoot) {
		this.mutantRoot = mutantRoot;
	}
	
	@Override
	public TreeNode getMutantRoot() {
		return mutantRoot;
	}
	
	@Override
	public List<Test> getTestsAtLine(SourceClass sourceClass, int lineNum) {
		//EXTENSION: Make this work if we actually have information about tests
		ArrayList<Test> tempTests = new ArrayList<Test>();
		for(Mutant mutant: sourceClass.getMutants()) {
			if(mutant.getLineNumber() == lineNum) {
				tempTests.addAll(mutant.getTests());
			}
		}
		return tempTests;
	}

	@Override
	public Mutant getMutant(int mutant_id) {
		return this.mutants.get(mutant_id - 1);
	}

	@Override
	public List<Mutant> getMutantsAtLine(SourceClass sourceClass, int lineNumber) {
		ArrayList<Mutant> temp_muts = new ArrayList<Mutant>();
		for(Mutant mutant : sourceClass.getMutants()) {
			if(mutant.getLineNumber() == lineNumber) {
				temp_muts.add(mutant);
			}
		}
		return temp_muts;
	}

	@Override
	public List<Mutant> getMutants(int test_id) {
		return tests.get(test_id).getMutants();
	}

	@Override
	public SourceClass getSourceClass(String classPath) {
		return sources.get(classPath);
	}

	@Override
	public String getLine(String class_name, int line) {
		String source = sources.get(class_name).getSource();
		String[] split = source.split("\\r?\\n");
		return split[line];
	}
	
	@Override
	public Mutator getMutator(MutatorType mutator) {
		return mutators.get(mutator);
	}
	
	@Override
	public Mutator getMutatorForMutant(int mutant_id) {
		return mutators.get(mutants.get(mutant_id - 1).getMutator());
	}
}
