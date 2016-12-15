package view;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.awt.LayoutManager;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;

/**
 * Test class for the BarGraph implementation.
 * @author mlimbird
 */
public class BarGraphTest {
    private static double EPS = 1e-9;

    /*
     * Test the 
     */
    @Test
    public void testConstructor() {
        //Initialize the BarGraph
        BarGraph bg = new BarGraph();

        //Check the components of the BarGraph
        int countComponent = bg.getComponentCount();
        int expectedCountComp = 2;
        assertEquals(expectedCountComp, countComponent);
        
        //Check the labelPanel
        Component cLabelPanel = bg.getComponent(0);
        assert cLabelPanel instanceof JPanel;
        JPanel actualLabelPanel = (JPanel)cLabelPanel;
        GridBagLayout bgLayout = (GridBagLayout)bg.getLayout();
        GridBagConstraints actualLabelConstraints = bgLayout.getConstraints(actualLabelPanel);
        //check the weighty, weightx, and fill
        double expectedWeightY = 1;
        double expectedWeightX = 0.01;
        int expectedFill = GridBagConstraints.BOTH;
        assertEquals(expectedWeightX, actualLabelConstraints.weightx, EPS);
        assertEquals(expectedWeightY, actualLabelConstraints.weighty, EPS);
        assertEquals(expectedFill, actualLabelConstraints.fill);


        //Check the BarPanel
        Component bLabelPanel = bg.getComponent(1);
        assert bLabelPanel instanceof JPanel;
        JPanel actualBarPanel = (JPanel)bLabelPanel;
        GridBagConstraints actualBarConstraints = bgLayout.getConstraints(actualBarPanel);
        //check the weighty, weightx, and fill
        expectedWeightY = 1;
        expectedWeightX = 0.9;
        expectedFill = GridBagConstraints.BOTH;
        assertEquals(expectedWeightX, actualBarConstraints.weightx, EPS);
        assertEquals(expectedWeightY, actualBarConstraints.weighty, EPS);
        assertEquals(expectedFill, actualBarConstraints.fill);


        //Check the gbc
        GridBagConstraints actualGBC = bg.getGBC();
        expectedWeightY = 1;
        expectedWeightX = 1;
        Insets expectedInsets = new Insets(10, 10, 0, 10);
        int expectedAnchor = GridBagConstraints.CENTER;
        int expectedGridY = 0;
        assertEquals(expectedWeightX, actualGBC.weightx, EPS);
        assertEquals(expectedWeightY, actualGBC.weighty, EPS);
        assertEquals(expectedInsets, actualGBC.insets);
        assertEquals(expectedAnchor, actualGBC.anchor);
        assertEquals(expectedGridY, actualGBC.gridy);

    }

    @Test
    public void testAddBar() {
        //Initialize the BarGraph
        BarGraph bg = new BarGraph();

        String barLabel = "New Label";
        double[] sections = new double[]{0.9333333333333333, 0.06666666666666667, 0.0};
        String[] labels = new String[]{"Killed: 140","Live (covered): 10","Live (uncovered): 0"};

        bg.addBar(barLabel, sections, labels);

        //Check the label panel
        JPanel theLabelPanel = bg.getLabelPanel();
        int countComponent = theLabelPanel.getComponentCount();
        int expectedCountComp = 1;
        assertEquals(expectedCountComp, countComponent);

        Component labelComponent = theLabelPanel.getComponent(0);
        assert labelComponent instanceof ResizeableJLabel;
        ResizeableJLabel actualLabel = (ResizeableJLabel)labelComponent;
        int expectedHorAlign = JLabel.RIGHT;
        assertEquals(expectedHorAlign, actualLabel.getHorizontalAlignment());

        GridBagLayout labelLayout = (GridBagLayout)theLabelPanel.getLayout();
        GridBagConstraints actualLabelConstraints = labelLayout.getConstraints(actualLabel);
        int expectedFill = GridBagConstraints.BOTH;
        assertEquals(expectedFill, actualLabelConstraints.fill);


        //Check the bar panel
        JPanel theBarPanel = bg.getBarPanel();
        countComponent = theBarPanel.getComponentCount();
        expectedCountComp = 1;
        assertEquals(expectedCountComp, countComponent);

        Component theCompoonent = theBarPanel.getComponent(0);
        assert theCompoonent instanceof BarGraphBar;
        BarGraphBar actualBar = (BarGraphBar)theCompoonent;
        
        GridBagLayout barLayout = (GridBagLayout)theBarPanel.getLayout();
        GridBagConstraints actualBarConstraints = barLayout.getConstraints(actualBar);
        expectedFill = GridBagConstraints.HORIZONTAL;
        assertEquals(expectedFill, actualBarConstraints.fill);


        //Check gbc gridy
        GridBagConstraints actualGBC = bg.getGBC();
        int expectedGridY = 1;
        assertEquals(expectedGridY, actualGBC.gridy);

    }
}