package model;
/**
 * Summary class contains a summary of mutant coverage
 * and kill statistics
 * @author jaholtz
 *
 */
public class Summary {
	private int total;
	private int covered;
	private int live;
	private int killed;
	
	public Summary(int total, 
				   int covered, 
				   int live, 
				   int killed) {
		this.total = total;
		this.covered = covered;
		this.live = live;
		this.killed = killed;
	}
	
	public boolean hasLive() {
		return killed != total;
	}


	//Accessors
	public int getTotal(){
		return this.total;
	}

	public int getCovered(){
		return this.covered;
	}

	public int getLive(){
		return this.live;
	}

	public int getKilled(){
		return this.killed;
	}


	//Mutators
	public void setTotal(int newTotal){
		this.total = newTotal;
	}

	public void setCovered(int newCovered){
		this.covered = newCovered;
	}

	public void setLive(int newLive){
		this.live = newLive;
	}

	public void setKilled(int newKilled){
		this.killed = newKilled;
	}

}
