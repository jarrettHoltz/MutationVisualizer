package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import model.TheModel;

/**
 * Manages the view for browsing different visualizations of the mutations.
 * @author rezecib
 */
public class BrowserPanel extends JPanel
{
	private static final long serialVersionUID = -4364720453828499850L;
	private static final String PANEL_TITLE_PREFIX = "Browser";
	private static final String PANEL_TITLE_GLUE = ": ";
	private static final String SOURCE_TITLE = "Source";
	private static final String TESTS_TITLE = "Tests";
	private static final String MUTANTS_TITLE = "Mutants";
	private JLabel panelTitle;
	private JTree source, tests, mutants;
	
	public BrowserPanel() {
		setBackground(Color.WHITE);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		MutantTreeCellRenderer treeRenderer = new MutantTreeCellRenderer();
		
		panelTitle = new JLabel(PANEL_TITLE_PREFIX);
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1e-15;
		gbc.insets = new Insets(10, 10, 0, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(panelTitle, gbc);
		
		gbc.gridy = 1;
		add(new JLabel(SOURCE_TITLE), gbc);
		
		gbc.gridy = 2;
		source = new JTree(TheModel.model.getSourceRoot());
		source.setCellRenderer(treeRenderer);
		add(source, gbc);
		
		gbc.gridy = 3;
		add(new JLabel(TESTS_TITLE), gbc);
		
		gbc.gridy = 4;
		tests = new JTree(TheModel.model.getTestRoot());
		tests.setCellRenderer(treeRenderer);
		add(tests, gbc);
		
		gbc.gridy = 5;
		add(new JLabel(MUTANTS_TITLE), gbc);
		
		gbc.gridy = 6;
		mutants = new JTree(TheModel.model.getMutantRoot());
		mutants.setCellRenderer(treeRenderer);
		add(mutants, gbc);
		
		gbc.gridy = 7;
		gbc.weighty = 1;
		add(new JLabel(""), gbc);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	/**
	 * Sets the program to display at the top of the browser panel, as a reminder of which program is being analyzed.
	 * @param programName The name of the program being analyzed.
	 */
	public void setProgram(String programName) {
		panelTitle.setText(PANEL_TITLE_PREFIX + PANEL_TITLE_GLUE + programName);
	}
}
