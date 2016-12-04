/**
 * Summary class contains a summary of mutant coverage
 * and kill statistics
 * @author jaholtz
 *
 */
public class Summary {
	public int total;
	public int covered;
	public int live;
	public int killed;
	
	public Summary(int total, 
				   int covered, 
				   int live, 
				   int killed) {
		this.total = total;
		this.covered = covered;
		this.live = live;
		this.killed = killed;
	}
}
