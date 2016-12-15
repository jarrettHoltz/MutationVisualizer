package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.CollapsiblePanelAction;
import model.MutantVizModel;
import model.SourceCode;

/**
 * Manages the various views of the mutation visualizer for the Triangle program.
 * @author rezecib
 */
public class MutantVizWindow extends JFrame
{
	private static final long serialVersionUID = -2387852887507443608L;
	
	private BrowserPanel browserPanel;
	private JPanel contentPanel;
	private SummaryPanel summaryPanel;
	private CodePanel codePanel, comparePanel;
	private JScrollPane codeScrollPane, compareScrollPane;
	
	/**
	 * These constraints hold the default layout parameters,
	 * to be used when collapsing or expanding panels
	 */
	private GridBagConstraints gbc;
	
	//Collapsed versions of the above, except the browser, which never collapses
	private JPanel collapsedSummaryPanel, collapsedCodePanel, collapsedComparePanel;
	private List<JButton> expandButtons;

	public MutantVizWindow(MutantVizModel model) {
		super("Mutation Visualizer");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
		expandButtons = new ArrayList<JButton>();
		
		buildPanels(model);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1;
		gbc.gridy = 0;

		JScrollPane browserScrollPane = new JScrollPane(browserPanel);
		browserScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		browserScrollPane.setPreferredSize(new Dimension(1000,1000));
		add(browserScrollPane);
		
		contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setPreferredSize(new Dimension(4000,1000));
		add(contentPanel);//Dummy panel to make sure browserPanel doesn't resize when summary/code/compare are manipulated
		
		setSummaryView(false);
		
		setSize(1600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	private void buildPanels(MutantVizModel model) {
		browserPanel = new BrowserPanel(model);
		browserPanel.setProgram("Triangle");
		summaryPanel = new SummaryPanel();
		codePanel = new CodePanel("Code", false);
		codeScrollPane = new JScrollPane(codePanel);
		codeScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		comparePanel = new CodePanel("Mutations", true);
		compareScrollPane = new JScrollPane(comparePanel);
		compareScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;
		collapsedSummaryPanel = new JPanel();
		collapsedSummaryPanel.setLayout(new GridBagLayout());
		collapsedSummaryPanel.add(buildExpandButton(false, "v Summary v", CollapsiblePanelAction.EXPAND_SUMMARY.name()), constraints);
		collapsedCodePanel = new JPanel();
		collapsedCodePanel.setLayout(new GridBagLayout());
		collapsedCodePanel.add(buildExpandButton(false, "^ Code ^", CollapsiblePanelAction.EXPAND_CODE.name()), constraints); //TODO: may need to say test, mutant instead; get this from node
		collapsedComparePanel = new JPanel();
		collapsedComparePanel.setLayout(new GridBagLayout());
		collapsedComparePanel.add(buildExpandButton(true, "^ Mutants ^", CollapsiblePanelAction.EXPAND_COMPARISON.name()), constraints); //TODO: may need to say tests, code
		
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
		//TODO: style this to look more like a panel and less like a button
		return button;
	}
	
	public void setNode(DefaultMutableTreeNode node) {
		summaryPanel.buildSummaryForNode(node);
		Object contents = node.getUserObject();
		boolean hasCode = contents instanceof SourceCode;
		if(hasCode) {
			codePanel.clearSource();
			codePanel.addSource((SourceCode)contents);
			codePanel.packSource();
			codePanel.setTitle(contents.toString());
			setCodeView(false); //No code also has a summary, at least now?
		}
		else {
			setSummaryView(hasCode); //No summary also has code, at least now?
		}
	}

	public void setCodeView(boolean hasSummary) {
		contentPanel.removeAll();
		
		if(hasSummary) {
			gbc.gridy = 1;
			gbc.gridwidth = 2;
			gbc.weightx = 0.3;
			gbc.weighty = 0.05;
			add(collapsedSummaryPanel, gbc);
			gbc.gridwidth = 1;
		}
		
		gbc.gridy = 2;
		gbc.gridx = 1;
		gbc.weighty = 1;
		contentPanel.add(codeScrollPane, gbc);
		
		validate();
		repaint();
	}
	
	public void setSummaryView(boolean hasCode) {
		contentPanel.removeAll();
		
		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.weightx = 0.8;
		gbc.weighty = 1;
		gbc.gridwidth = 2;
		contentPanel.add(summaryPanel, gbc);
		gbc.gridwidth = 1;
		
		if(hasCode) {
			gbc.gridy = 2;
			gbc.weighty = 0.05;
			contentPanel.add(collapsedCodePanel, gbc);
		}
		
		validate();
		repaint();
	}
	

	public void setComparison(CodeLine source) {
		List<SourceCode> targets = source.getTargets();
		
		if(!targets.isEmpty()){
			comparePanel.clearSource();
			comparePanel.packSource();
			for(SourceCode target : targets) {
				comparePanel.addSource(target);
				comparePanel.packSource();
			}		
		}
		comparePanel.setTitle("Mutants at line " + source.getLineNumber());
		gbc.gridx = 2;
		gbc.gridy = 2;
		contentPanel.add(compareScrollPane, gbc);
		
		validate();
		repaint();
		
	}
	
	public void setTreeSelectionListener(TreeSelectionListener listener) {
		browserPanel.setTreeSelectionListener(listener);
		browserPanel.setDefaultSelection();
	}
	
	public void setActionListener(ActionListener listener) {
		for(JButton button : expandButtons) {
			button.addActionListener(listener);
		}
	}
	
	public void setMouseListener(MouseListener listener) {
		//TODO: give to CodePanel, ComparePanel
		codePanel.addNewMouseListener(listener);
		comparePanel.addNewMouseListener(listener);
	}

	//Accessors
	public CodePanel getCodePanel(){
		return this.codePanel;
	}
}
