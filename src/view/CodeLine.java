package view;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import model.MutantStatus;
import model.SourceCode;

public class CodeLine extends JLabel {
	private static final long serialVersionUID = -3345614810564531206L;
	private List<SourceCode> targets;
	private int lineNumber;
	
	public CodeLine(String code, int lineNumber, int maxLines) {
		this.lineNumber = lineNumber;
		int numDigits = (int)Math.log10(maxLines) + 1;
		String.format("%1$" + numDigits + "s", lineNumber);
		setText(String.format("%1$" + numDigits + "s", lineNumber) + " | " + code);
		setFont(new Font("Monospaced", Font.PLAIN, 12));
		targets = new ArrayList<SourceCode>();
	}
	
	public void setStatus(MutantStatus status) {
		setBackground(MutantColor.getColor(ColorContext.HIGHLIGHT, status));
		setOpaque(true);
	}

	public void addTarget(SourceCode target) {
		targets.add(target);
	}
	
	public List<SourceCode> getTargets() {
		return targets;
	}
	
	public int getLineNumber(){
		return lineNumber;
	}
	
	public String toString() {
		return getText();
	}
}
