package model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Test class for the TriangleModel implementation.
 * @author mlimbird
 */
public class TriangleModelTest {

    /*
     * Test the Contructor
     */
    @Test
    public void testConstructor() {
        
        //Initialize the TriangleModel
        TriangleModel tm = new TriangleModel();

        //Check the sources

        //Check the tests

        //Check the mutants

        //Check the mutator map

        //Check the summary

    }

    @Test
    public void testSetSummary() {
        
        //Initialize the TriangleModel
        TriangleModel tm = new TriangleModel();

        Summary expectedSummary = new Summary(4,3,2,1);
        tm.setSummary(expectedSummary);
        Summary actualSummary = tm.getSummary();

        assertEquals(expectedSummary.getTotal(), actualSummary.getTotal());
        assertEquals(expectedSummary.getCovered(), actualSummary.getCovered());
        assertEquals(expectedSummary.getLive(), actualSummary.getLive());
        assertEquals(expectedSummary.getKilled(), actualSummary.getKilled());

        
    }
}