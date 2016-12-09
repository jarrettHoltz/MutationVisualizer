package model;

/**
 * Directory contains the name of a directory for source code or tests, and the associated summary for that directory's children.
 * @author rezecib
 */
public class Directory extends SummaryNode {
	public Directory(String directoryName, Summary summary) {
		this.name = directoryName;
		this.summary = summary;
	}
}
