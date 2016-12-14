package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Mutant;
import model.MutantStatus;
import model.SourceClass;
import model.SourceCode;

public class CodePanel extends JPanel
{
	private static final long serialVersionUID = -586643883942555488L;
	
	//TODO: Make a JLabel for each line, style background of JLabel by mutants that are at that line
	// SourceClass, Test, Mutant
	// For colors, they are in MutantTreeCellRenderer and BarGraphBar
	private JLabel titleLabel;
	private GridBagConstraints gbc;
	private List<MouseListener> mouseListeners;
	private boolean isComparePanel;
	
	public CodePanel(String title, boolean isComparePanel) {
		this.isComparePanel = isComparePanel;
		setLayout(new GridBagLayout());
		
		mouseListeners = new ArrayList<MouseListener>();
		
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
		int mutantLineNum = -1;
		int lineNumberOffset = 1;
		String source = code.getSource();
		if(code instanceof Mutant) { //comparison panels should only show the mutant line
			Mutant m = ((Mutant)code);
			if(isComparePanel) {
				//This is the comparison panel, the mutant will show only one line but it should be numbered correctly
				lineNumberOffset = m.getLineNumber();
			} else {
				mutantLineNum = m.getLineNumber()-1;
				//This is the main code panel it should show the full original source too
				source = m.getSourceClass().getSource();
			}
		}
		String[] sourceLines = source.split("\n");
		CodeLine[] codeLines = new CodeLine[sourceLines.length];
		int maxLines = sourceLines.length+1;
		for(int i = 0; i < sourceLines.length; i++) {
			codeLines[i] = new CodeLine(sourceLines[i], i+lineNumberOffset, maxLines);
			add(codeLines[i], gbc);
			gbc.gridy++;
			if(i == mutantLineNum) {
				codeLines[i].setStatus(MutantStatus.LIVE);
				CodeLine mutantLine = new CodeLine(((Mutant)code).getSource(), i+1, maxLines);
				mutantLine.setStatus(MutantStatus.FAIL);
				add(mutantLine, gbc);
				gbc.gridy++;
			}
		}
		
		if(isComparePanel && code instanceof Mutant) {
			//Color the mutant according to its status
			codeLines[0].setStatus(((Mutant)code).getStatus());
		}
		
		if(code instanceof SourceClass) {
			boolean[] listenersAdded = new boolean[sourceLines.length];
			MutantStatus[] lineMutantStatus = new MutantStatus[sourceLines.length];
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
					codeLines[i].setStatus(lineMutantStatus[i]);
				}
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
		gbc.gridy++;
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
