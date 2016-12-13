package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.CollapsiblePanelAction;
import model.Mutant;
import model.MutantVizModel;
import model.SourceClass;
import model.SourceCode;
import model.Test;

/**
 * Manages the various views of the mutation visualizer for the Triangle program.
 * @author rezecib
 */
public class MutantVizWindow extends JFrame
{
	private static final long serialVersionUID = -2387852887507443608L;
	
	private BrowserPanel browserPanel;
	private SummaryPanel summaryPanel;
	private CodePanel codePanel;
	private JScrollPane codeScrollPane;
	private CodePanel comparePanel; //TODO: make this
	private MutantVizModel model;
	
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
		setLayout(new GridBagLayout());
		this.model = model;
		expandButtons = new ArrayList<JButton>();
		
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
				
		setSize(1600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	private void buildPanels(MutantVizModel model) {
		browserPanel = new BrowserPanel(model);
		browserPanel.setProgram("Triangle");
		summaryPanel = new SummaryPanel();
		codePanel = new CodePanel(model, "Code");
		codeScrollPane = new JScrollPane(codePanel);
		codeScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		comparePanel = new CodePanel(model, "Mutations");
		
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
			setCodeView();
		}
		else {
			setSummaryView(hasCode);
		}
	}

	public void setCodeView() {
		remove(summaryPanel);
		remove(collapsedCodePanel);
		remove(comparePanel);
		
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.weighty = 0.05;
		add(collapsedSummaryPanel, gbc);
		gbc.gridwidth = 1;
		
		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.weighty = 1;
		// make sure codePanel knows what it's working with
//		codePanel.addSource(curNode);
		//System.out.println(((SourceClass) curNode).getSource());
		add(codeScrollPane, gbc);
		
		validate();
		repaint();
	}
	
	public void setSummaryView(boolean hasCode) {
		remove(codeScrollPane);
		remove(collapsedSummaryPanel);
		remove(comparePanel);
		
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
	

	public void setComparison(CodeLine source) {

		int mutantLineNum = source.getLineNumber();
		//TODO: make name not hard-coded
		List<Mutant> mutants = model.getMutants("Triangle", mutantLineNum);
		
		if(!mutants.isEmpty()){
			comparePanel.clearSource();
			comparePanel.addSource(mutants);
			comparePanel.packSource();
		
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1.0;
			c.gridwidth = 1;
			c.gridheight = 2;
			c.weighty = 1.0;
			add(comparePanel, c);
			
			validate();
			repaint();
		}
		
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
}
