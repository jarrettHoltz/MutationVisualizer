package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import model.Mutant;
import model.MutantVizModel;
import model.SourceClass;
import model.Test;

/**
 * Manages the various views of the mutation visualizer for the Triangle program.
 * @author rezecib
 */
public class TriangleWindow extends JFrame implements ActionListener, TreeSelectionListener
{
	private static final long serialVersionUID = -2387852887507443608L;
	
	private static final String EXPAND_CODE = "ExpandCode";
	private static final String EXPAND_COMPARISON = "ExpandComparison";
	private static final String EXPAND_SUMMARY = "ExpandSummary";
	
	private BrowserPanel browserPanel;
	private SummaryPanel summaryPanel;
	private CodePanel codePanel;
	private ComparePanel comparePanel;
	
	/**
	 * These constraints hold the default layout parameters,
	 * to be used when collapsing or expanding panels
	 */
	private GridBagConstraints gbc;
	
	//Collapsed versions of the above, except the browser, which never collapses
	private JPanel collapsedSummaryPanel, collapsedCodePanel, collapsedComparePanel;

	public TriangleWindow(MutantVizModel model) {
		super("Mutation Visualizer");
		setLayout(new GridBagLayout());
		
		buildPanels(model);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1.0;
		gbc.gridy = 0;

		gbc.gridx = 0;
		gbc.weightx = 0.2;
		gbc.gridheight = 2;
		JScrollPane browserScrollPane = new JScrollPane(browserPanel);
		browserScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(browserScrollPane, gbc);
		gbc.gridheight = 1;
		
		setSummaryView(false);
		
//		gbc.gridx = 2;
//		gbc.weightx = 0.1;
//		add(collapsedComparePanel, gbc);
		
		browserPanel.setDefaultSelection();
		
		setSize(1600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	private void buildPanels(MutantVizModel model) {
		browserPanel = new BrowserPanel(model);
		browserPanel.setProgram("Triangle");
		browserPanel.setTreeSelectionListener(this);
		summaryPanel = new SummaryPanel();
		codePanel = new CodePanel();
		comparePanel = new ComparePanel();
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;
		collapsedSummaryPanel = new JPanel();
		collapsedSummaryPanel.setLayout(new GridBagLayout());
		collapsedSummaryPanel.add(buildExpandButton(false, "v Summary v", EXPAND_SUMMARY), constraints);
		collapsedCodePanel = new JPanel();
		collapsedCodePanel.setLayout(new GridBagLayout());
		collapsedCodePanel.add(buildExpandButton(false, "^ Code ^", EXPAND_CODE), constraints); //TODO: may need to say test, mutant instead; get this from node
		collapsedComparePanel = new JPanel();
		collapsedComparePanel.setLayout(new GridBagLayout());
		collapsedComparePanel.add(buildExpandButton(true, "^ Mutants ^", EXPAND_COMPARISON), constraints); //TODO: may need to say tests, code
		
		for(JPanel panel : new JPanel[]{collapsedSummaryPanel, collapsedCodePanel, collapsedComparePanel}) {
			panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
	}
	
	private JButton buildExpandButton(boolean rotate, String text, String actionCommand) {
		JButton button = new JButton(text);
		button.setFont(button.getFont().deriveFont(42f));
		if(rotate) {
			button.setUI(new VerticalButtonUI(270));
		}
		button.setActionCommand(actionCommand);
		button.addActionListener(this);
		//TODO: style this to look more like a panel and less like a button
		return button;
	}
	
	public void setNode(DefaultMutableTreeNode node) {
		summaryPanel.buildSummaryForNode(node);
		Object o = node.getUserObject();
		boolean hasCode = o instanceof SourceClass || o instanceof Test || o instanceof Mutant;
		setSummaryView(hasCode);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		System.out.println(command);
		if(command == EXPAND_CODE) {
			setCodeView();
		} else if(command == EXPAND_SUMMARY) {
			setSummaryView(true);
		}
	}
	
	private void setCodeView() {
		remove(summaryPanel);
		remove(collapsedCodePanel);
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.weighty = 0.05;
		add(collapsedSummaryPanel, gbc);
		gbc.gridwidth = 1;
		
		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.weighty = 1;
		add(codePanel, gbc);
		
		validate();
		repaint();
	}
	
	private void setSummaryView(boolean hasCode) {
		remove(codePanel);
		remove(collapsedSummaryPanel);
		
		gbc.gridy = 0;
		gbc.gridx = 1;
		gbc.weightx = 0.8;
		gbc.weighty = 1;
		gbc.gridwidth = 2;
		add(summaryPanel, gbc);
		gbc.gridwidth = 1;
		
		if(hasCode) {
			gbc.gridy = 1;
			gbc.weighty = 0.05;
			add(collapsedCodePanel, gbc);
		}
		
		validate();
		repaint();
	}

	@Override
	public void valueChanged(TreeSelectionEvent e)
	{
		setNode((DefaultMutableTreeNode) e.getPath().getLastPathComponent());
	}
}
