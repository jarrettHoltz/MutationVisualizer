package model;

/**
 * A node in a file tree (e.g. a file or directory of source or test code) which can have a mutant summary attached to it.
 * @author rezecib
 */
public abstract class SummaryNode
{
	public Summary summary;
	public String name;
	
	public String toString() {
		return name;
	}
}
