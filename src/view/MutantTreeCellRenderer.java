package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTree;
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
				if(mutant.status.equals("LIVE")) {
					setLiveColor();
				}
				else {
					setDeadColor();
				}
			}
			else if(contents instanceof SummaryNode) {
				SummaryNode sumNode = (SummaryNode) contents;
				if(sumNode.summary.hasLive()) {
					//There are some mutants still alive under this node, so color it to show that
					setLiveColor();
				}
				else {
					//All mutants under this have been killed, yay!
					setDeadColor();
				}
			}
			else //this shouldn't happen, but it wouldn't be terrible if it did
			{
				System.out.println(contents.getClass());
				System.out.println(contents);
				setDefaultColor();
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
