package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.MutantStatus;

public class BarGraphBar extends JPanel
{
	private static final long serialVersionUID = -9230562314273402L;
	private static final Color[] DEFAULT_COLORS = {
			MutantColor.getColor(ColorContext.SOLID, MutantStatus.FAIL),
			MutantColor.getColor(ColorContext.SOLID, MutantStatus.COVERED),
			MutantColor.getColor(ColorContext.SOLID, MutantStatus.LIVE),
	};
	private static final String[] DEFAULT_LABELS = {
			"Killed",
			"Live (covered)",
			"Live (uncovered)",
	};
	private JPanel[] barSections;
	private double[] sections;
	
	/**
	 * Constructs a bar for a bar graph
	 * @param sections The percentages of each of the sections of the bar; these should add up to 1.
	 * @param labels The labels to show as hovertext for each of the sections; should be at least as long as sections.
	 */
	public BarGraphBar(double[] sections, String[] labels, Color[] colors) {
		//TODO: Add exception if the parameter constraints are violated?
		barSections = new JPanel[sections.length];
		this.sections = sections.clone();
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		if(colors == null) {
			colors = DEFAULT_COLORS;
		}
		if(labels == null) {
			labels = DEFAULT_LABELS;
		}
		for(int i = 0; i < sections.length; i++) {
			JPanel barSection = new JPanel();
			barSection.setBackground(colors[i]);
			barSection.setToolTipText(labels[i]);
			add(barSection);
			barSections[i] = barSection;
		}
		setMinimumSize(new Dimension(0, 40));
		setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
		setOpaque(false);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                adjustSectionSizes(BarGraphBar.this);
            }
        });
	}
	
	private void adjustSectionSizes(BarGraphBar bar) {
		double total = bar.getBounds().getWidth();
		for(int i = 0; i < sections.length; i++) {
			barSections[i].setPreferredSize(new Dimension((int)(sections[i]*total), 40));
		}
	}
}
