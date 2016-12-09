package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

public class SummaryPanel extends JPanel {
	private static final long serialVersionUID = -4294620494918182221L;
	
	public SummaryPanel() {
		setLayout(new GridBagLayout());
	}
	
	public void buildSummaryForNode(DefaultMutableTreeNode root) {
		removeAll(); //Clear out any previous contents
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(10, 10, 0, 10);
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridx = 0;
		gbc.gridwidth = 3;
		JLabel title = new JLabel("Summary"); //TODO: Have this say what it's summarizing
		title.setFont(title.getFont().deriveFont(42.0f));
		add(title, gbc);
		gbc.gridwidth = 1;
		

	}
}
