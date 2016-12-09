package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;

import model.TheModel;

/**
 * Manages the various views of the mutation visualizer for the Triangle program.
 * @author rezecib
 */
public class TriangleWindow extends JFrame
{
	private static final long serialVersionUID = -2387852887507443608L;
	
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

	public TriangleWindow() {
		super("Mutation Visualizer");
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		setLayout(new GridBagLayout());
		
		buildPanels();
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1.0;
		gbc.gridy = 0;

		gbc.gridx = 0;
		gbc.weightx = 0.2;
		gbc.gridheight = 2;
		add(new JScrollPane(browserPanel), gbc);
		gbc.gridheight = 1;
		
		gbc.gridx = 1;
		gbc.weightx = 0.8;
		//A tracer to make sure it's taking up the area it's supposed to
//		summaryPanel.setBackground(Color.magenta);
		add(summaryPanel, gbc);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	private void buildPanels() {
		browserPanel = new BrowserPanel();
		browserPanel.setProgram("Triangle");
		summaryPanel = new SummaryPanel();
		summaryPanel.buildSummaryForNode((DefaultMutableTreeNode)TheModel.model.getSourceRoot());
		codePanel = new CodePanel();
		comparePanel = new ComparePanel();
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;
		collapsedSummaryPanel = new JPanel();
		collapsedSummaryPanel.setLayout(new GridBagLayout());
		collapsedSummaryPanel.add(buildExpandButton(false, "Summary"), constraints);
		collapsedCodePanel = new JPanel();
		collapsedCodePanel.setLayout(new GridBagLayout());
		collapsedSummaryPanel.add(buildExpandButton(false, "Code"), constraints); //TODO: may need to say test, mutant instead; get this from node
		collapsedComparePanel = new JPanel();
		collapsedComparePanel.setLayout(new GridBagLayout());
		collapsedComparePanel.add(buildExpandButton(true, "Mutants"), constraints); //TODO: may need to say tests, code
		//TODO: Attach listeners
	}
	
	private JButton buildExpandButton(boolean rotate, String text) {
		JButton button = new JButton(text);
		if(rotate) {
			button.setUI(new VerticalButtonUI(90));;
		}
		//TODO: style this to look more like a panel and less like a button
		return button;
	}
}
