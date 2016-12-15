package view;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import view.ColorContext;

/**
 * Test class for the ColorContext implementation.
 * @author mlimbird
 */
public class ColorContextTest {

    /*
     * Test the 
     */
    @Test
    public void testHIGHLIGHT() {
        assertNotNull(ColorContext.valueOf("HIGHLIGHT"));
    }

    @Test
    public void testSELECTED() {
        assertNotNull(ColorContext.valueOf("SELECTED"));
    }

    @Test
    public void testSOLID() {
        assertNotNull(ColorContext.valueOf("SOLID"));
    }
}