package view;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Test class for the ResizeableJLabel implementation.
 * @author mlimbird
 */
public class ResizeableJLabelTest {

    /*
     * Test the 
     */
    @Test
    public void testConstructor() {
        //Instantiate the object
        ResizeableJLabel rjl = new ResizeableJLabel("NewText");
        String actualText = rjl.getText();
        String expectedText = "NewText";
        assertEquals(expectedText, actualText);
    }
}