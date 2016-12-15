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

    // @Test
    // public void testAdjustSectionSizes() {
    //     //Create a MutantVizModel
    //     TriangleModel tModel = new TriangleModel();
    //     //Initialize a Triangle Parser
    //     TriangleParser parser = new TriangleParser();
    //     //Build the model
    //     parser.buildModel(tModel,"test_files/triangle/mutation_results", "test_files/triangle/src", "test_files/triangle/test" );

    //     MutantVizWindow mView = new MutantVizWindow(tModel);

    //     double[] sections = new double[]{0.9333333333333333, 0.06666666666666667, 0.0};
    //     String[] labels = new String[]{"Killed: 140","Live (covered): 10","Live (uncovered): 0"};
    //     Color[] colors = null;

    //     BarGraphBar bgb = new BarGraphBar(sections, labels, colors);
    //     bgb.adjustSectionSizes(bgb);
    //     double total = bgb.getBounds().getWidth();
    //     System.out.print(total);


    //     JPanel[] jpArray = bgb.getBarSections();

    //     for (int i = 0; i < 3; i++){
    //         JPanel jp = jpArray[i];
    //         double inter = sections[i]*total;
    //         System.out.print(inter);
    //         Dimension expectedDim = new Dimension((int)(sections[i]*total), 40);
    //         System.out.print(expectedDim);
    //         Dimension actualDim = jp.getPreferredSize();
    //         System.out.print(actualDim);
    //         assertEquals(expectedDim, actualDim);
    //     }
    // }

    private static final Color[] DEFAULT_COLORS = {
            MutantColor.getColor(ColorContext.SOLID, MutantStatus.FAIL),
            MutantColor.getColor(ColorContext.SOLID, MutantStatus.COVERED),
            MutantColor.getColor(ColorContext.SOLID, MutantStatus.LIVE),
    };
}