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
	public int mutant_id;
	public String mutator;
	public String status;
	public String class_name;
	public String method_name;
	public int line_number;
	public String mutant_source;
	public ArrayList<Integer> tests = new ArrayList<Integer>();
	
	public Mutant(int mutant_id,
			String mutator,
			String class_name,
			String method_name,
			int line_number,
			String mutant_source) {
		
		this.mutant_id = mutant_id;
		this.mutator = mutator;
		this.class_name = class_name;
		this.method_name = method_name;
		this.line_number = line_number;
		this.mutant_source = mutant_source;
		this.status = "unknown";
	}
	
	/**
	 * Adds a test id to the mutant
	 * @param test_id
	 */
	public void AddTest(int test_id) {
		tests.add(test_id);
	}
}
