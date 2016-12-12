package model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Test class for the Summary implementation.
 * @author mlimbird
 */
public class SummaryTest {

    /*
     * Test the Constructor
     */
    @Test
    public void testConstructor() {

        //Initialize the Summary Object
        Summary actualSummary = new Summary(0,1,2,3);

        //Check Total
        int expectedTotal = 0;
        assertEquals(expectedTotal, actualSummary.getTotal());
        
        //Check Covered
        int expectedCovered = 1;
        assertEquals(expectedCovered, actualSummary.getCovered());
        
        //Check Live
        int expectedLive = 2;
        assertEquals(expectedLive, actualSummary.getLive());
        
        //Check Killed
        int expectedKilled = 3;
        assertEquals(expectedKilled, actualSummary.getKilled());
    }

    @Test
    public void testHasLive() {

        //Initialize the Summary Object
        Summary s = new Summary(4,1,2,3);

        //Check True case
        boolean expectedHasLive = true;
        boolean actualHasLive = s.hasLive();
        assertEquals(expectedHasLive, actualHasLive);

        s = new Summary(3,1,2,3);
        expectedHasLive = false;
        actualHasLive = s.hasLive();
        assertEquals(expectedHasLive, actualHasLive);
    }
}