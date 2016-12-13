package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.*;

public class CodePanel extends JPanel
{
	//TODO: Make a JLabel for each line, style background of JLabel by mutants that are at that line
	// SourceClass, Test, Mutant
	// For colors, they are in MutantTreeCellRenderer and BarGraphBar
	
	MutantVizModel model;
	GridBagConstraints gbc_default;
	
	public CodePanel(MutantVizModel model){
		this.model = model;
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		////setBackground(Color.MAGENTA);
		
		gbc_default = new GridBagConstraints();
		gbc_default.gridy = 0;
		gbc_default.insets = new Insets(10, 10, 0, 10);
		gbc_default.anchor = GridBagConstraints.CENTER;
		gbc_default.fill = GridBagConstraints.BOTH;
		
		gbc_default.weighty = 1e-15;
		layout1();
	}
	public void layout1(){
		setTitle("Code");
	}
	
	public void setTitle(String titleString){
		GridBagConstraints gbc= gbc_default;
		gbc.weightx = 1;
		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		JLabel title = new JLabel(titleString);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.TOP);
		title.setFont(title.getFont().deriveFont(42f));
		add(title, gbc);
	}
	
	public void displayCode(){
		
	}
	
	private SourceClass src;
	private Test test;
	private Mutant mutant;
	private JLabel content; // needs to be global so it can be removed
	
	public void registerObject(Object o){
		// most remove previous content JLabel, or else will keep accumulating
		if(content != null) remove(content);
		GridBagConstraints gbc= gbc_default;
		if(o instanceof SourceClass){
			src = (SourceClass) o;
			//System.out.println(src.getSource());
			// in order to facilitate new line in JLabel, need to wrap in html
			// then replace \n with <br> (so weird!)
			String txt_formatted = formatCode(src.getSource());
			content = new JLabel(txt_formatted);
			content.setHorizontalAlignment(JLabel.LEFT);
			
			// get mutants
			//ArrayList<Integer> mutants = src.getMutants();
			
			
		} else
		if(o instanceof Test){
			test = (Test) o;
			content = new JLabel(formatCode(test.getSource()));
			content.setHorizontalAlignment(JLabel.LEFT);
		} else
		if(o instanceof Mutant){
			mutant = (Mutant) o;
			int m_id = mutant.getMutantId();
			content = new JLabel(formatCode(mutant.toString() + model.getSource(m_id).getSource()));
		}
		add(content, gbc);
	}
	
	private String formatCode(String raw){
		return "<html>"+raw.replace("\n", "<br>") + "</html>";
	}
}
