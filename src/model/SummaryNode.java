package model;

/**
 * A node in a file tree (e.g. a file or directory of source or test code) which can have a mutant summary attached to it.
 * @author rezecib
 */
public abstract class SummaryNode
{
	protected Summary summary;
	protected String name;
	
	public String toString() {
		return this.name;
	}


	//Accessors
	public Summary getSummary(){
		return this.summary;
	}

	public String getName(){
		return this.name;
	}


	//Mutators
	public void setSummary(Summary newSummary){
		this.summary = newSummary;
	}

	// public void setName(String newName){
	// 	this.name = newName;
	// }

}
