package view;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Test class for the VerticalButtonUI implementation.
 * @author mlimbird
 */
public class VerticalButtonUITest {

    /*
     * Test the constructor
     */
    @Test
    public void testConstructor() {
        //Instantiate the object
        VerticalButtonUI vbUI = new VerticalButtonUI(100);

        int expectedAngle = 100;
        int actualAngle = vbUI.getAngle();
        assertEquals(expectedAngle, actualAngle);
    }

    @Test
    public void testPreferredSize() {
        //Not sure how to do this
    }

    @Test
    public void testPaint() {
        //Not sure how to do this
    }
}