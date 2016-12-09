package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import model.Summary;
import model.SummaryNode;

public class SummaryPanel extends JPanel {
	private static final long serialVersionUID = -4294620494918182221L;
	
	public SummaryPanel() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	public void buildSummaryForNode(DefaultMutableTreeNode root) {
		removeAll(); //Clear out any previous contents
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.insets = new Insets(10, 10, 0, 10);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridy = 0;
		gbc.weighty = 1e-15;
		JLabel title = new JLabel("Summary"); //TODO: Have this say what it's summarizing
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(title.getFont().deriveFont(42f));
		add(title, gbc);
		
		gbc.gridy = 1;
		gbc.weighty = 1;
		gbc.insets.bottom = 10;
		BarGraph barGraph = new BarGraph();
		buildBarFromNode("Overall", barGraph, root);
		@SuppressWarnings("unchecked")
		Enumeration<DefaultMutableTreeNode> en = root.children();
		while(en.hasMoreElements()) {
			DefaultMutableTreeNode node = en.nextElement();
			String name = node.getUserObject().toString();
			if(!name.equals("all")){ //TODO: Better check for the artificial "all" folder of mutants?
				buildBarFromNode(name, barGraph, node);
			}
		}
		add(barGraph, gbc);
	}
	
	private void buildBarFromNode(String barLabel, BarGraph barGraph, DefaultMutableTreeNode node) {
		Object obj = node.getUserObject();
		double[] sections;
		String[] labels;
		if(obj instanceof SummaryNode) {
			Summary s = ((SummaryNode)obj).summary;
			double total = s.total;
			sections = new double[]{s.killed/total, (s.covered - s.killed)/total, (s.total - s.covered)/total};
			labels = new String[]{"Killed: " + s.killed, "Live (covered): " + (s.covered - s.killed), "Live (uncovered): " + (s.total - s.covered)};
		} else {
			//TODO: throw exception?
			return;
		}
		barGraph.addBar(barLabel, sections, labels);
	}
}
