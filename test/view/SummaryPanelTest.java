package view;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.GridBagLayout;

/**
 * Test class for the SummaryPanel implementation.
 * @author mlimbird
 */
public class SummaryPanelTest {

    /*
     * Test the constructor
     */
    @Test
    public void testConstructor() {
        SummaryPanel sp = new SummaryPanel();

        //Future test: check the gridbaglayout : current implementation doesn't work
        // GridBagLayout expectedLayout = new GridBagLayout();
        // GridBagLayout actualLayout = (GridBagLayout)sp.getLayout();
        // assertEquals(expectedLayout, actualLayout);

        // Future test: check the borders as well
        // LineBorder expectedBorder = (LineBorder)BorderFactory.createLineBorder(Color.BLACK);
        // LineBorder actualBorder = (LineBorder)sp.getBorder();
        // assertEquals(expectedBorder, actualBorder);
    }

    //Future Test needs to be created for BuildSummaryForNode
    // @Test
    // public void testBuildSummaryForNode() {

    // }
}