package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import model.Mutant;
import model.SummaryNode;

public class MutantTreeCellRenderer extends DefaultTreeCellRenderer
{
	private static final long serialVersionUID = -5481343225088718815L;
	private static final Color LIVE_COLOR = new Color(0xFF, 0xBB, 0xBB);
	private static final Color LIVE_COLOR_SELECTED = new Color(0xDD, 0x99, 0x99);
	private static final Color DEAD_COLOR = new Color(0xBB, 0xFF, 0xBB);
	private static final Color DEAD_COLOR_SELECTED = new Color(0x99, 0xDD, 0x99);
	private static final Border EMPTY_BORDER = BorderFactory.createEmptyBorder();
	private static final Border SPACY_BORDER = BorderFactory.createMatteBorder(20, 0, 5, 0, Color.white);
	private Color default_color, default_color_selected;
	
	public MutantTreeCellRenderer() {
		default_color = getBackgroundNonSelectionColor();
		default_color_selected = getBackgroundSelectionColor();
	}
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasfocus) {
		//comp should be equal to _this_, but just in case that randomly changes...
		Component comp = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		if(value instanceof DefaultMutableTreeNode) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			Object contents = node.getUserObject();
			if(contents instanceof Mutant) {
				Mutant mutant = (Mutant) contents;
				//if(mutant.status.equals("LIVE")) {
				if(mutant.getStatus().equals("LIVE")) {
					setLiveColor();
				}
				else {
					setDeadColor();
				}
			} else if(contents instanceof SummaryNode) { //This is a node that can be summarized as having live mutants or not
				SummaryNode sumNode = (SummaryNode) contents;
				//if(sumNode.summary.hasLive()) {
				if(sumNode.getSummary().hasLive()) {
					//There are some mutants still alive under this node, so color it to show that
					setLiveColor();
				} else {
					//All mutants under this have been killed, yay!
					setDeadColor();
				}
			} else //This node isn't summarizeable? This shouldn't happen, but it wouldn't be terrible if it did
			{
				setDefaultColor();
			}
			//Add some extra spacing to the top-level nodes (just under the root)
			if(node.getParent().getParent() == null) {
				setBorder(SPACY_BORDER);
			} else {
				setBorder(EMPTY_BORDER);
			}
		}
		return comp;
	}
	
	private void setLiveColor() {
		setBackgroundNonSelectionColor(LIVE_COLOR);
		setBackgroundSelectionColor(LIVE_COLOR_SELECTED);
	}
	
	private void setDeadColor() {
		setBackgroundNonSelectionColor(DEAD_COLOR);
		setBackgroundSelectionColor(DEAD_COLOR_SELECTED);
	}
	
	private void setDefaultColor() {
		setBackgroundNonSelectionColor(default_color);
		setBackgroundSelectionColor(default_color_selected);
	}
}
