package view;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import model.TriangleModel;
import java.awt.Color;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Component;
import model.TriangleModel;
import controller.TriangleParser;
import java.awt.LayoutManager;

/**
 * Test class for the BrowserPanel implementation.
 * @author mlimbird
 */
public class BrowserPanelTest {
    private static double EPS = 1e-9;

    /*
     * Test the 
     */
    @Test
    public void testConstructor() {
        //Instantiate objects
        TriangleModel model = new TriangleModel();
        TriangleParser parser = new TriangleParser();
        parser.buildModel(model,"test_files/triangle/mutation_results", "test_files/triangle/src", "test_files/triangle/test" );
        BrowserPanel bPanel = new BrowserPanel(model);


        //Check the Background and layout
        Color expectedBackground = Color.WHITE;
        Color actualBackground = bPanel.getBackground();
        assertEquals(expectedBackground, actualBackground);
        LayoutManager bPanelLayout = bPanel.getLayout();
        assert bPanelLayout instanceof GridBagLayout;
        GridBagLayout actualLayout = (GridBagLayout)bPanelLayout;
        

        //Check the components of the BrowserPanel
        int countComponent = bPanel.getComponentCount();
        int expectedCountComp = 3;
        assertEquals(expectedCountComp, countComponent);


        //Check the JLabel component
        Component cLabel = bPanel.getComponent(0);
        assert cLabel instanceof JLabel;
        JLabel actualLabel = (JLabel)cLabel;
        GridBagConstraints actualLabelConstraints = actualLayout.getConstraints(actualLabel);
        int expectedGridY = 0;
        double expectedWeightX = 1;
        double expectedWeightY = 1e-15;
        Insets expectedInsets = new Insets(10, 10, 0, 10);
        int expectedFill = GridBagConstraints.HORIZONTAL;
        String expectedText = "Browser";
        assertEquals(expectedGridY, actualLabelConstraints.gridy);
        assertEquals(expectedWeightX, actualLabelConstraints.weightx, EPS);
        assertEquals(expectedWeightY, actualLabelConstraints.weighty, EPS);
        assertEquals(expectedInsets, actualLabelConstraints.insets);
        assertEquals(expectedFill, actualLabelConstraints.fill);
        assertEquals(expectedText, actualLabel.getText());


        //Check the JTree
        Component cTree = bPanel.getComponent(1);
        assert cTree instanceof JTree;
        JTree actualTree = (JTree)cTree;

        Object oRoot = actualTree.getModel().getRoot();
        assert oRoot instanceof DefaultMutableTreeNode;
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)oRoot;

        int children = actualTree.getModel().getChildCount(root);
        assertEquals(3, children);

        for (int i = 0; i < 3; i++){
            Object oChild = actualTree.getModel().getChild(root, i);
            assert oChild instanceof DefaultMutableTreeNode;
            DefaultMutableTreeNode child = (DefaultMutableTreeNode)oChild;
            if (i == 0){
                DefaultMutableTreeNode expectedSourceChild = (DefaultMutableTreeNode)model.getSourceRoot();
                assertEquals(expectedSourceChild, child);
            } else if (i == 1){
                DefaultMutableTreeNode expectedTestChild = (DefaultMutableTreeNode)model.getTestRoot();
                assertEquals(expectedTestChild, child);
            } else {
                DefaultMutableTreeNode expectedMutantChild = (DefaultMutableTreeNode)model.getMutantRoot();
                assertEquals(expectedMutantChild, child);
            }

        }

        actualLabelConstraints = actualLayout.getConstraints(actualTree);
        expectedGridY = 1;
        expectedWeightX = 1;
        expectedWeightY = 1e-15;;
        expectedInsets = new Insets(10, 10, 0, 10);
        expectedFill = GridBagConstraints.HORIZONTAL;
        assertEquals(expectedGridY, actualLabelConstraints.gridy);
        assertEquals(expectedWeightX, actualLabelConstraints.weightx, EPS);
        assertEquals(expectedWeightY, actualLabelConstraints.weighty, EPS);
        assertEquals(expectedInsets, actualLabelConstraints.insets);
        assertEquals(expectedFill, actualLabelConstraints.fill);


        //Check the empty JLabel component
        cLabel = bPanel.getComponent(2);
        assert cLabel instanceof JLabel;
        actualLabel = (JLabel)cLabel;
        actualLabelConstraints = actualLayout.getConstraints(actualLabel);
        expectedGridY = 2;
        expectedWeightX = 1;
        expectedWeightY = 1;
        expectedInsets = new Insets(10, 10, 0, 10);
        expectedFill = GridBagConstraints.HORIZONTAL;
        expectedText = "";
        assertEquals(expectedGridY, actualLabelConstraints.gridy);
        assertEquals(expectedWeightX, actualLabelConstraints.weightx, EPS);
        assertEquals(expectedWeightY, actualLabelConstraints.weighty, EPS);
        assertEquals(expectedInsets, actualLabelConstraints.insets);
        assertEquals(expectedFill, actualLabelConstraints.fill);
        assertEquals(expectedText, actualLabel.getText());

    }

    @Test
    public void testSetProgram() {
        //Instantiate objects
        TriangleModel model = new TriangleModel();
        TriangleParser parser = new TriangleParser();
        parser.buildModel(model,"test_files/triangle/mutation_results", "test_files/triangle/src", "test_files/triangle/test" );
        BrowserPanel bPanel = new BrowserPanel(model);

        bPanel.setProgram("programName");

        //Check the JLabel component
        Component cLabel = bPanel.getComponent(0);
        assert cLabel instanceof JLabel;
        JLabel actualLabel = (JLabel)cLabel;
        String expectedText = "Browser: programName";
        assertEquals(expectedText, actualLabel.getText());
    }

    @Test
    public void testDefaultSelection() {
        //Instantiate objects
        TriangleModel model = new TriangleModel();
        TriangleParser parser = new TriangleParser();
        parser.buildModel(model,"test_files/triangle/mutation_results", "test_files/triangle/src", "test_files/triangle/test" );
        BrowserPanel bPanel = new BrowserPanel(model);

        bPanel.setDefaultSelection();

        //Check the JLabel component
        Component cTree = bPanel.getComponent(1);
        assert cTree instanceof JTree;
        JTree actualTree = (JTree)cTree;
        int expectedSelected = 0;
        int actualSelected = actualTree.getSelectionRows()[0];
        assertEquals(expectedSelected, actualSelected);
    }

}