package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Mutant;
import model.MutantStatus;
import model.MutantVizModel;
import model.SourceClass;
import model.SourceCode;

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
	private List<MouseListener> mouseListeners;
	
	public CodePanel(MutantVizModel model, String title) {
		this.model = model;
		setLayout(new GridBagLayout());
		
		mouseListeners = new ArrayList<MouseListener>();
		codeLabels = new ArrayList<JLabel>();
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		
		titleLabel = new JLabel(title);
		titleLabel.setBackground(new Color(0xCC, 0xCC, 0xCC));
		titleLabel.setOpaque(true);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setVerticalAlignment(JLabel.CENTER);
		titleLabel.setFont(titleLabel.getFont().deriveFont(42f));
		clearSource();
	}
	
	public void setTitle(String titleString) {
		titleLabel.setText(titleString);
	}
	
	/**
	 * Adds a piece of source code to the display. Note that the caller is responsible for calling
	 * {@link CodePanel#clearSource()} before code is added,
	 * {@link CodePanel#packSource()} and validate()/repaint() when addition of source code is finished.
	 * @param code The code to add.
	 */
	public void addSource(SourceCode code) {
		String[] source = null;
		source = code.getSource().split("\n");
		CodeLine[] codeLines = new CodeLine[source.length];
		int maxLines = source.length+1;
		for(int i = 0; i < source.length; i++) {
			codeLines[i] = new CodeLine(source[i], i+1, maxLines);
			add(codeLines[i], gbc);
			gbc.gridy++;
		}
		
		if(code instanceof SourceClass) {
			boolean[] listenersAdded = new boolean[source.length];
			MutantStatus[] lineMutantStatus = new MutantStatus[source.length];
			for(Mutant m : ((SourceClass)code).getMutants()) {
				int i = m.getLineNumber()-1;
				CodeLine codeLine = codeLines[i];
				if(!listenersAdded[i]) {					
					for(MouseListener listener: mouseListeners) {
						codeLine.addMouseListener(listener);
					}
					listenersAdded[i] = true;
				}
				codeLine.addTarget(m);
				MutantStatus status = m.getStatus();
				if(status == MutantStatus.LIVE) {
					lineMutantStatus[i] = MutantStatus.LIVE;
				} else if(lineMutantStatus[i] != MutantStatus.LIVE) {
					lineMutantStatus[i] = MutantStatus.FAIL;
				}
				//EXTENSION: support other mutant status colorings, with an order of priority
			}
			for(int i = 0; i < lineMutantStatus.length; i++) {
				if(lineMutantStatus[i] != null) { //there are mutants here, put the right color
					codeLines[i].setBackground(MutantColor.getColor(ColorContext.HIGHLIGHT, lineMutantStatus[i]));
					codeLines[i].setOpaque(true);					
				}
			}
		}
	}
	
	public void addSource(List<Mutant> mutants){
		MutantStatus[] lineMutantStatus = new MutantStatus[mutants.size()];
		CodeLine[] mutantLines = new CodeLine[mutants.size()];
		int maxLines = mutants.size()+1;
		int i = 0;
		for(Mutant m : mutants) {
			if(m != null){
				mutantLines[i] = new CodeLine(m.getSource(), m.getLineNumber(), maxLines);
				add(mutantLines[i], gbc);
				gbc.gridy++;
				
				MutantStatus status = m.getStatus();
				if(status == MutantStatus.LIVE) {
					lineMutantStatus[i] = MutantStatus.LIVE;
				} else if(lineMutantStatus[i] != MutantStatus.LIVE) {
					lineMutantStatus[i] = MutantStatus.FAIL;
				}
				i++;
			}
		}
		for(int j = 0; j < lineMutantStatus.length; j++) {
			if(lineMutantStatus[j] != null) { //there are mutants here, put the right color
				mutantLines[j].setBackground(MutantColor.getColor(ColorContext.HIGHLIGHT, lineMutantStatus[j]));
				mutantLines[j].setOpaque(true);					
			}
		}
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
		removeAll();

		//Add the title back in
		gbc.gridy = 0;
		gbc.weighty = 1e-15;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.ipadx = 20;
		gbc.ipady = 20;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(titleLabel, gbc);
		
		//Reset the gbc constants to what the code-adding should be using
		gbc.gridy = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.fill = GridBagConstraints.NONE;
	}
	
	public void addNewMouseListener(MouseListener listener) {
		mouseListeners.add(listener);
	}
}
