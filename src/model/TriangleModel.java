package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
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
	public void AddSource(SourceClass source) {
		sources.put(source.name, source);
	}
	
	public void AddTest(Test test) {
		tests.add(test);
	}
	
	@Override
	public void addMutant(Mutant mutant) {
		mutants.add(mutant);
		//mutators.get(mutant.mutator).addMutant(mutant);
		mutators.get(mutant.getMutator()).addMutant(mutant);
		//SourceClass source = sources.get(mutant.className);
		SourceClass source = sources.get(mutant.getClassName());
		//source.AddMutant(mutant.mutantId);
		source.AddMutant(mutant.getMutantId());
		//if(!mutant.status.equals("LIVE")) {
		if(!mutant.getStatus().equals("LIVE")) {
			//Assumes only one test
			Test theTest = (Test) ((DefaultMutableTreeNode)testRoot.getChildAt(0).getChildAt(0)).getUserObject();
			//Add tests that killed this mutant
			mutant.AddTest(theTest);
			//Add this mutant to tests that killed it
			theTest.AddMutant(mutant);
		}
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
	public List<Test> getTests(String class_name, int line_num) {
		ArrayList<Test> temp_tests = new ArrayList<Test>();
		for(Mutant mutant: this.mutants) {
			//if(mutant.className.equals(class_name) && mutant.lineNumber == line_num) {
			if(mutant.getClassName().equals(class_name) && mutant.getLineNumber() == line_num) {
				//temp_tests.addAll(mutant.tests);
				temp_tests.addAll(mutant.getTests());
			}
		}
		return temp_tests;
	}

	@Override
	public Mutant getMutant(int mutant_id) {
		return this.mutants.get(mutant_id - 1);
	}

	@Override
	public List<Mutant> getMutants(String class_name, int line_number) {
		ArrayList<Mutant> temp_muts = new ArrayList<Mutant>();
		for(Mutant mutant : mutants) {
			//if(mutant.className.equals(class_name) && mutant.lineNumber == line_number) {
			if(mutant.getClassName().equals(class_name) && mutant.getLineNumber() == line_number) {
				temp_muts.add(mutant);
			}
		}
		return temp_muts;
	}

	@Override
	public List<Mutant> getMutants(int test_id) {
		//return tests.get(test_id).mutants;
		return tests.get(test_id).getMutants();
	}

	@Override
	public SourceClass getSourceClass(String class_name) {
		return sources.get(class_name);
	}

	@Override
	public SourceClass getSource(int mutant_id) {
		//return sources.get(mutants.get(mutant_id - 1).className);
		return sources.get(mutants.get(mutant_id - 1).getClassName());
	}
	
	@Override
	public String getLine(String class_name, int line) {
		//String source = sources.get(class_name).source;
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
		//return mutators.get(mutants.get(mutant_id - 1).mutator);
		return mutators.get(mutants.get(mutant_id - 1).getMutator());
	}
}
