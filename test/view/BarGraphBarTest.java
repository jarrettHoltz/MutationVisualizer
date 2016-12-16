package view;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.awt.Color;
import model.MutantStatus;
import javax.swing.JPanel;
import java.awt.Dimension;
import model.MutantVizModel;
import model.TriangleModel;
import controller.TriangleParser;

/**
 * Test class for the BarGraphBar implementation.
 * @author mlimbird
 */
public class BarGraphBarTest {

    /*
     * Test the constructor
     */
    @Test
    public void testConstructor() {
        double[] sections = new double[]{0.9333333333333333, 0.06666666666666667, 0.0};
        String[] labels = new String[]{"Killed: 140","Live (covered): 10","Live (uncovered): 0"};
        Color[] colors = null;

        BarGraphBar bgb = new BarGraphBar(sections, labels, colors);
        JPanel[] jpArray = bgb.getBarSections();

        for (int i = 0; i < 3; i++){
            JPanel jp = jpArray[i];

            //Check the background color
            Color expectedColor = DEFAULT_COLORS[i];
            Color actualColor = jp.getBackground();
            assertEquals(expectedColor, actualColor);

            //Check the tool tips
            String expectedText = labels[i];
            String actualText = jp.getToolTipText();
            assertEquals(expectedText, actualText);
        }

    }

    private static final Color[] DEFAULT_COLORS = {
            MutantColor.getColor(ColorContext.SOLID, MutantStatus.FAIL),
            MutantColor.getColor(ColorContext.SOLID, MutantStatus.COVERED),
            MutantColor.getColor(ColorContext.SOLID, MutantStatus.LIVE),
    };
}