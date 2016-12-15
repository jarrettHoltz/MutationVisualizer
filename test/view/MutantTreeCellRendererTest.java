package view;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.Color;


/**
 * Test class for the MutantTreeCellRenderer implementation.
 * @author mlimbird
 */
public class MutantTreeCellRendererTest {

    /*
     * Test the 
     */
    @Test
    public void testConstructor() {
        //Instantiate the object
        MutantTreeCellRenderer mtcr = new MutantTreeCellRenderer();

        DefaultTreeCellRenderer dtcr = new DefaultTreeCellRenderer();
        Color expectedDefaultColor = dtcr.getBackgroundNonSelectionColor();
        Color actualDefaultColor = mtcr.getDefaultColor();
        assertEquals(expectedDefaultColor, actualDefaultColor);

        Color expectedDefaultColorSelected = dtcr.getBackgroundSelectionColor();
        Color actualDefaultColorSelected = mtcr.getDefaultColorSelected();
        assertEquals(expectedDefaultColorSelected, actualDefaultColorSelected);
    }

    @Test
    public void testTreeCellRendererComponent() {
        //Instantiate the object
        
    }

    // @Test
    // public void testSetLiveColor() {
    //     //Instantiate the object
    //     MutantTreeCellRenderer mtcr = new MutantTreeCellRenderer();

    //     mtcr.setLiveColor();

    //     Color expectedlive = new Color(0xFF, 0xBB, 0xBB);
    //     Color actualNonSelectionColor = mtcr.getBackgroundNonSelectionColor();
    //     assertEquals(expectedlive, actualNonSelectionColor);

    //     Color expectedlive_selected = new Color(0xDD, 0x99, 0x99);
    //     Color actualSelectionColor = mtcr.getBackgroundSelectionColor();
    //     assertEquals(expectedlive_selected, actualSelectionColor);
        
    // }

    // @Test
    // public void testSetDeadColor() {
    //     //Instantiate the object
    //     MutantTreeCellRenderer mtcr = new MutantTreeCellRenderer();

    //     mtcr.setDeadColor();

    //     Color expectedDead = new Color(0xBB, 0xFF, 0xBB);
    //     Color actualNonSelectionColor = mtcr.getBackgroundNonSelectionColor();
    //     assertEquals(expectedDead, actualNonSelectionColor);

    //     Color expectedDead_selected = new Color(0x99, 0xDD, 0x99);
    //     Color actualSelectionColor = mtcr.getBackgroundSelectionColor();
    //     assertEquals(expectedDead_selected, actualSelectionColor);
        
    // }

    // @Test
    // public void testSetDefaultColor() {
    //     //Instantiate the object
    //     MutantTreeCellRenderer mtcr = new MutantTreeCellRenderer();

    //     mtcr.setDefaultColor();

    //     Color expectedDefaultColor = mtcr.getDefaultColor();
    //     Color actualDefaultColor = mtcr.getBackgroundNonSelectionColor();
    //     assertEquals(expectedDefaultColor, actualDefaultColor);

    //     Color expectedDefaultColorSelected = mtcr.getDefaultColorSelected();
    //     Color actualDefaultColorSelected = mtcr.getBackgroundSelectionColor();
    //     assertEquals(expectedDefaultColorSelected, actualDefaultColorSelected);
        
    // }
}