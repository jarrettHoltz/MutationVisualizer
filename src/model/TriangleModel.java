package model;
import java.util.ArrayList;
import java.util.HashMap;


public class TriangleModel implements MutantVizModel {
	private ArrayList<Mutant> mutants = new ArrayList<Mutant>();
	private HashMap<String,SourceClass> sources = new HashMap<String, SourceClass>();
	private ArrayList<Test> tests = new ArrayList<Test>();
	private HashMap<String, Mutator> mutators = new HashMap<String, Mutator>();
	private Summary summary;
	
	/**
	 * Constructor
	 */
	public TriangleModel() {
		mutators.put("LVR", new Mutator("LVR"));
		mutators.put("ROR", new Mutator("ROR"));
		mutators.put("COR", new Mutator("COR"));
		mutators.put("STD", new Mutator("STD"));
		mutators.put("AOR", new Mutator("AOR"));
	}
	
	@Override
	public Summary GetSummary() {
		return this.summary;
	}

	@Override
	public void SetSummary(Summary summary) {
		this.summary = summary;

	}

	@Override
	public void AddMutant(Mutant mutant) {
		mutants.add(mutant);
		mutators.get(mutant.mutator).AddMutant(mutant.mutant_id);
		SourceClass source = sources.get(mutant.class_name);
		source.AddMutant(mutant.mutant_id);
		if(!mutant.status.equals("LIVE")) {
			mutant.AddTest(1);
			tests.get(0).AddMutant(mutant.mutant_id);
		}
	}
	
	@Override
	public void AddTest(Test test) {
		tests.add(test);
	}

	@Override
	public void AddSource(SourceClass source) {
		sources.put(source.ClassName, source);
	}
	
	@Override
	public Test GetTest(int test_id) {
		return tests.get(test_id);
	}

	@Override
	public ArrayList<Integer> GetTests(int mutant_id) {
		return mutants.get(mutant_id).tests;
	}

	@Override
	public ArrayList<Integer> GetTests(String class_name, int line_num) {
		ArrayList<Integer> temp_tests = new ArrayList<Integer>();
		for(Mutant mutant: this.mutants) {
			if(mutant.class_name.equals(class_name) && mutant.line_number == line_num) {
				temp_tests.addAll(mutant.tests);
			}
		}
		return temp_tests;
	}

	@Override
	public Mutant GetMutant(int mutant_id) {
		return this.mutants.get(mutant_id - 1);
	}

	@Override
	public ArrayList<Mutant> GetMutants(String class_name, int line_number) {
		ArrayList<Mutant> temp_muts = new ArrayList<Mutant>();
		for(Mutant mutant : mutants) {
			if(mutant.class_name.equals(class_name) && mutant.line_number == line_number) {
				temp_muts.add(mutant);
			}
		}
		return temp_muts;
	}

	@Override
	public ArrayList<Integer> GetMutants(int test_id) {
		return tests.get(test_id).mutants;
	}

	@Override
	public SourceClass GetSourceClass(String class_name) {
		return sources.get(class_name);
	}

	@Override
	public SourceClass GetSource(int mutant_id) {
		return sources.get(mutants.get(mutant_id - 1).class_name);
	}
	
	@Override
	public String GetLine(String class_name, int line) {
		String source = sources.get(class_name).source;
		String[] split = source.split("\\r?\\n");
		return split[line];
	}
	
	@Override
	public Mutator GetMutator(String mutator) {
		return mutators.get(mutator);
	}
	
	@Override
	public Mutator GetMutator(int mutant_id) {
		return mutators.get(mutants.get(mutant_id - 1).mutator);
	}
}
