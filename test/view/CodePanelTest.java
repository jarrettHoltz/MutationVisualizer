package view;

import org.junit.Test;
import static org.junit.Assert.*;

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
import java.awt.Component;

/**
 * Test class for the CodePanel implementation.
 * @author mlimbird
 */
public class CodePanelTest {
	private static double EPS = 1e-9;

    /*
     * Test the Constructor
     */
    @Test
    public void testConstructor() {
        //Create the JPanel
        CodePanel cp = new CodePanel("title", false);

        //Check the isComparePanel
        boolean expectedComp = false;
        boolean actualComp = cp.getIsComparePanel();
        assertEquals(expectedComp, actualComp);

        //Check mouseListeners
        List<MouseListener> expectedListeners = new ArrayList<MouseListener>();
        List<MouseListener> actualListeners = cp.getMListeners();
        assertEquals(expectedListeners, actualListeners);

        //Check the JLabel
        JLabel actualLabel = cp.getTitleLabel();
        String expectedTitle = "title";
        assertEquals(expectedTitle, actualLabel.getText());

        Color expectedColor = new Color(0xCC, 0xCC, 0xCC);
        assertEquals(expectedColor, actualLabel.getBackground());

        int expectedAlignment = JLabel.CENTER;
        assertEquals(expectedAlignment, actualLabel.getHorizontalAlignment());
        assertEquals(expectedAlignment, actualLabel.getVerticalAlignment());
		
		//Check the title gbc
        Component cLabel = cp.getComponent(0);
        assert cLabel instanceof JLabel;
        actualLabel = (JLabel)cLabel;
        GridBagLayout cpLayout = (GridBagLayout)cp.getLayout();
        GridBagConstraints actualLabelConstraints = cpLayout.getConstraints(actualLabel);

        int expectedGridY = 0;
        assertEquals(expectedGridY, actualLabelConstraints.gridy);
        double expectedWeightY = 1e-15;
		assertEquals(expectedWeightY, actualLabelConstraints.weighty, EPS);
		int expectedAnchor = GridBagConstraints.CENTER;
		assertEquals(expectedAnchor, actualLabelConstraints.anchor);
		int expectedPadX = 20;
		assertEquals(expectedPadX, actualLabelConstraints.ipadx);
		int expectedPadY = 20;
		assertEquals(expectedPadY, actualLabelConstraints.ipady);
		int expectedFill = GridBagConstraints.HORIZONTAL;
		assertEquals(expectedFill, actualLabelConstraints.fill);
		int expectedGridX = 0;
		assertEquals(expectedGridX, actualLabelConstraints.gridx);
        double expectedWeightX = 1;
        assertEquals(expectedWeightX, actualLabelConstraints.weightx, EPS);


        //Check the gbc of CodePanel
        actualLabelConstraints = cp.getGBC();
        expectedGridY = 1;
        assertEquals(expectedGridY, actualLabelConstraints.gridy);
        expectedWeightY = 0;
		assertEquals(expectedWeightY, actualLabelConstraints.weighty, EPS);
		expectedAnchor = GridBagConstraints.FIRST_LINE_START;
		assertEquals(expectedAnchor, actualLabelConstraints.anchor);
		expectedPadX = 0;
		assertEquals(expectedPadX, actualLabelConstraints.ipadx);
		expectedPadY = 0;
		assertEquals(expectedPadY, actualLabelConstraints.ipady);
		expectedFill = GridBagConstraints.NONE;
		assertEquals(expectedFill, actualLabelConstraints.fill);
		expectedGridX = 0;
		assertEquals(expectedGridX, actualLabelConstraints.gridx);
        expectedWeightX = 1;
        assertEquals(expectedWeightX, actualLabelConstraints.weightx, EPS);
    }

    @Test
    public void testPackSource() {
    	//Create the JPanel
        CodePanel cp = new CodePanel("title", false);
        cp.packSource();

        Component cLabel = cp.getComponent(1);
        assert cLabel instanceof JLabel;
        JLabel actualLabel = (JLabel)cLabel;
        GridBagLayout cpLayout = (GridBagLayout)cp.getLayout();
        GridBagConstraints actualLabelConstraints = cpLayout.getConstraints(actualLabel);

        int expectedGridY = 1;
        assertEquals(expectedGridY, actualLabelConstraints.gridy);
        double expectedWeightY = 1;
		assertEquals(expectedWeightY, actualLabelConstraints.weighty, EPS);
		int expectedAnchor = GridBagConstraints.FIRST_LINE_START;
		assertEquals(expectedAnchor, actualLabelConstraints.anchor);
		int expectedPadX = 0;
		assertEquals(expectedPadX, actualLabelConstraints.ipadx);
		int expectedPadY = 0;
		assertEquals(expectedPadY, actualLabelConstraints.ipady);
		int expectedFill = GridBagConstraints.NONE;
		assertEquals(expectedFill, actualLabelConstraints.fill);
		int expectedGridX = 0;
		assertEquals(expectedGridX, actualLabelConstraints.gridx);
        double expectedWeightX = 1;
        assertEquals(expectedWeightX, actualLabelConstraints.weightx, EPS);

        //Check the gbc of CodePanel
        actualLabelConstraints = cp.getGBC();
        expectedGridY = 2;
        assertEquals(expectedGridY, actualLabelConstraints.gridy);
        expectedWeightY = 0;
		assertEquals(expectedWeightY, actualLabelConstraints.weighty, EPS);
		expectedAnchor = GridBagConstraints.FIRST_LINE_START;
		assertEquals(expectedAnchor, actualLabelConstraints.anchor);
		expectedPadX = 0;
		assertEquals(expectedPadX, actualLabelConstraints.ipadx);
		expectedPadY = 0;
		assertEquals(expectedPadY, actualLabelConstraints.ipady);
		expectedFill = GridBagConstraints.NONE;
		assertEquals(expectedFill, actualLabelConstraints.fill);
		expectedGridX = 0;
		assertEquals(expectedGridX, actualLabelConstraints.gridx);
        expectedWeightX = 1;
        assertEquals(expectedWeightX, actualLabelConstraints.weightx, EPS);

    }

    //Future test: create a test to look at addSource
    // @Test
    // public void testAddSource() {
    // }

}