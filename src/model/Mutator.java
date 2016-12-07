package model;
import java.util.ArrayList;

/**
 * Mutator objects contain the mutants created
 * by the given mutator, and a summary of the results 
 * of those mutants.
 * @author jaholtz
 *
 */
public class Mutator {
	public String mutator_name;
	public ArrayList<Integer> mutant_ids = new ArrayList<Integer>();
	public Summary summary;
	
	/**
	 * 
	 * @param mutator_name
	 */
	public Mutator(String mutator_name) {
		this.mutator_name = mutator_name;
	}
	
	/**
	 * Adds mutants to the mutator
	 * @param mutant
	 */
	public void AddMutant(int mutant) {
		mutant_ids.add(mutant);
	}
	
	/**
	 * Updates the summary object based on the added mutants
	 * and the given mutant model
	 * @param model to which this mutator object belongs
	 * 
	 */
	public void UpdateSummary(MutantVizModel model) {
		int covered = 0;
		int killed = 0;
		int live = 0;
		int total = mutant_ids.size();
		for(int i : mutant_ids) {
			Mutant mutant = model.GetMutant(i);
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
