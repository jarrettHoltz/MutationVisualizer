package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BarGraph extends JPanel
{
	private static final long serialVersionUID = -7084023771083447286L;
	private GridBagConstraints gbc;
	private JPanel labelPanel, barPanel;
	
	public BarGraph() {
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		
		constraints.weightx = 0.01;
		labelPanel = new JPanel();
		labelPanel.setLayout(new GridBagLayout());
		add(labelPanel, constraints);
		
		constraints.weightx = 0.9;
		barPanel = new JPanel();
		barPanel.setLayout(new GridBagLayout());
		add(barPanel, constraints);
		
		gbc = new GridBagConstraints();
		gbc.weighty = 1;
		gbc.weightx = 1;
		gbc.insets = new Insets(10, 10, 0, 10);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridy = 0;
		
	}
	
	public void addBar(String barLabel, double[] sections, String[] labels) {
		JLabel label = new ResizeableJLabel(barLabel);
		label.setHorizontalAlignment(JLabel.RIGHT);
		gbc.fill = GridBagConstraints.BOTH;
		labelPanel.add(label, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		barPanel.add(new BarGraphBar(sections, labels, null), gbc);
		gbc.gridy += 1;
	}
}
