package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Mutant;
import model.MutantVizModel;
import model.SourceClass;
import model.SourceCode;
import model.Test;

public class CodePanel extends JPanel
{
	private static final long serialVersionUID = -586643883942555488L;
	
	//TODO: Make a JLabel for each line, style background of JLabel by mutants that are at that line
	// SourceClass, Test, Mutant
	// For colors, they are in MutantTreeCellRenderer and BarGraphBar
	private JLabel titleLabel;
	private MutantVizModel model;
	private GridBagConstraints gbc;
	private List<JLabel> codeLabels;
	
	public CodePanel(MutantVizModel model) {
		this.model = model;
		setLayout(new GridBagLayout());
		
		codeLabels = new ArrayList<JLabel>();
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		
		gbc.gridy = 0;
		gbc.weighty = 1e-15;
		gbc.anchor = GridBagConstraints.CENTER;
		titleLabel = new JLabel("Code");
		gbc.ipadx = 20;
		gbc.ipady = 20;
		titleLabel.setBackground(new Color(0xCC, 0xCC, 0xCC));
		titleLabel.setOpaque(true);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setVerticalAlignment(JLabel.CENTER);
		titleLabel.setFont(titleLabel.getFont().deriveFont(42f));
		add(titleLabel, gbc);
		
		//Reset the gbc constants to what the code-adding should be using
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 1;
		gbc.weighty = 0;
	}
	
	public void setTitle(String titleString) {
		titleLabel.setText(titleString);
	}
	
	/**
	 * Adds a piece of source code to the display. Note that the caller is responsible for calling
	 * {@link CodePanel#packSource()} and validate()/repaint() when addition of source code is finished.
	 * @param code The code to add.
	 */
	public void addSource(SourceCode code) {
		//TODO: Maybe packSource() can be called at the end? maybe not for the comparison panel, though...
		// most remove previous content JLabel, or else will keep accumulating
//		if(content != null) {
//			remove(content);
//		}
		String[] source = code.getSource().split("\n");
		for(String line : source) {
			JLabel lineLabel = new JLabel(line);
			//TODO: real color logic, add listeners for click (MouseListener?)
			if(Math.random() < 0.1) {				
				lineLabel.setBackground(new Color(0xFF, 0xBB, 0xBB));
				lineLabel.setOpaque(true);
			}
			add(lineLabel, gbc);
			gbc.gridy++;
		}
		if(code instanceof SourceClass) {
//			src = (SourceClass) code;
			//System.out.println(src.getSource());
			// in order to facilitate new line in JLabel, need to wrap in html
			// then replace \n with <br> (so weird!)
//			String txt_formatted = formatCode(src.getSource());
//			content = new JLabel(txt_formatted);
//			content.setHorizontalAlignment(JLabel.LEFT);
			
			// get mutants
			//ArrayList<Integer> mutants = src.getMutants();
			
			
		} else if(code instanceof Test) {
//			test = (Test) code;
//			content = new JLabel(formatCode(test.getSource()));
//			content.setHorizontalAlignment(JLabel.LEFT);
		} else if(code instanceof Mutant) {
//			mutant = (Mutant) code;
//			int m_id = mutant.getMutantId();
//			content = new JLabel(formatCode(mutant.toString() + model.getSource(m_id).getSource()));
		}
//		add(content, gbc);
	}
	
	/**
	 * This should be called after done with all calls to {@link CodePanel#addSource(SourceCode)};
	 * this will push the source code to the top of the panel (so it doesn't space itself out).
	 */
	public void packSource() {
		gbc.weighty = 1;
		add(new JLabel(), gbc);
		gbc.weighty = 0;
	}
	
	/**
	 * Removes all source code from the display.
	 * Note that the caller is responsible for calling validate()/repaint() after removal.
	 */
	public void clearSource() {
		gbc.gridy = 1;
		int last_idx = codeLabels.size()-1;
		while(!codeLabels.isEmpty()) {
			remove(codeLabels.remove(last_idx--));
		}
	}
	
	private String formatCode(String raw) {
		return "<html>"+raw.replace("\n", "<br>") + "</html>";
	}
}
