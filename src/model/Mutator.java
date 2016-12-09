package model;
import java.util.ArrayList;
import java.util.List;

/**
 * Mutator objects contain the mutants created
 * by the given mutator, and a summary of the results 
 * of those mutants.
 * @author jaholtz
 *
 */
public class Mutator extends SummaryNode {
	public List<Mutant> mutants;
	
	/**
	 * 
	 * @param mutator_name
	 */
	public Mutator(String mutator_name) {
		name = mutator_name;
		summary = new Summary(0, 0, 0, 0);
		mutants = new ArrayList<Mutant>();
	}
	
	/**
	 * Adds mutants to the mutator
	 * @param mutant
	 */
	public void addMutant(Mutant mutant) {
		mutants.add(mutant);
	}
	
	/**
	 * Updates the summary object based on the added mutants
	 * and the given mutant model
	 * @param model to which this mutator object belongs
	 * 
	 */
	public void updateSummary(MutantVizModel model) {
		int covered = 0;
		int killed = 0;
		int live = 0;
		int total = mutants.size();
		for(Mutant mutant : mutants) {
			String status = mutant.status;
			if (status.equals("LIVE")) {
				live += 1;
			} else {
				covered += 1;
				killed += 1;
			}
			this.summary = new Summary(total, covered, live, killed);
		}
	}
}
