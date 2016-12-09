package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import model.MutantVizModel;

/**
 * Manages the view for browsing different visualizations of the mutations.
 * @author rezecib
 */
public class BrowserPanel extends JPanel
{
	private static final long serialVersionUID = -4364720453828499850L;
	private static final String PANEL_TITLE_PREFIX = "Browser";
	private static final String PANEL_TITLE_GLUE = ": ";
	private JLabel panelTitle;
	private JTree browserTree;
	
	public BrowserPanel(MutantVizModel model) {
		setBackground(Color.WHITE);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		panelTitle = new JLabel(PANEL_TITLE_PREFIX);
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1e-15;
		gbc.insets = new Insets(10, 10, 0, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(panelTitle, gbc);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		root.add((DefaultMutableTreeNode) model.getSourceRoot());
		root.add((DefaultMutableTreeNode) model.getTestRoot());
		root.add((DefaultMutableTreeNode) model.getMutantRoot());
		
		gbc.gridy = 1;
		browserTree = new JTree(root);
		browserTree.setRootVisible(false);
		browserTree.setCellRenderer(new MutantTreeCellRenderer());
		for(int i = 2; i >= 0; i--) {
			browserTree.expandRow(i);
		}
		add(browserTree, gbc);
				
		gbc.gridy = 2;
		gbc.weighty = 1;
		add(new JLabel(""), gbc);
	}
	
	/**
	 * Sets the program to display at the top of the browser panel, as a reminder of which program is being analyzed.
	 * @param programName The name of the program being analyzed.
	 */
	public void setProgram(String programName) {
		panelTitle.setText(PANEL_TITLE_PREFIX + PANEL_TITLE_GLUE + programName);
	}
	
	/**
	 * Selects the default node: the root of the source code tree.
	 * This will cause the view to build a view for the summary of that node.
	 */
	public void setDefaultSelection() {
		browserTree.setSelectionRow(0);
	}
	
	/**
	 * Exposes the JTree's method for adding a selection listener.
	 * @param listener The listener to add
	 */
	public void setTreeSelectionListener(TreeSelectionListener listener) {
		browserTree.addTreeSelectionListener(listener);
	}
}
