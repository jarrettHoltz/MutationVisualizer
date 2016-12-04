import java.util.ArrayList;

/**
 * Test object contains a string for the test code
 * the mutants covered/killed by the test,
 * and a summary object.
 * @author jaholtz
 *
 */
public class Test {
	public String source;
	public Summary summary;
	public ArrayList<Integer> mutants = new ArrayList<Integer>();
	
	public Test(String source, Summary summary) {
		this.source = source;
		this.summary = summary;
	}
	
	public void AddMutant(int mutant) {
		mutants.add(mutant);
	}
}
