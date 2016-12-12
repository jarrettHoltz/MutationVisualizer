package model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Test class for the SourceClass implementation.
 * @author mlimbird
 */
public class SourceClassTest {

    /*
     * Test the Constructor
     */
    @Test
    public void testConstructor() {

        //Initialize the SourceClass Object
        Summary s = new Summary(0,0,0,0);
        SourceClass sc = new SourceClass("className", "source", s);

        //Test ClassName
        String actualClass = sc.getName();
        String expectedClass = "className";
        assertEquals(expectedClass, actualClass);

        //Test Source
        String actualSource = sc.getSource();
        String expectedSource = "source";
        assertEquals(expectedSource, actualSource);

        //Test Source
        Summary actualSummary = sc.getSummary();
        Summary expectedSummary = new Summary(0,0,0,0);
        assertEquals(expectedSummary.getTotal(), actualSummary.getTotal());
        assertEquals(expectedSummary.getCovered(), actualSummary.getCovered());
        assertEquals(expectedSummary.getLive(), actualSummary.getLive());
        assertEquals(expectedSummary.getKilled(), actualSummary.getKilled());

        //Test Mutants
        ArrayList<Integer> actualMutants = sc.getMutants();
        ArrayList<Integer> expectedMutants = new ArrayList<Integer>();
        assertEquals(expectedMutants, actualMutants);
    }

    @Test
    public void testAddMutant() {

        //Initialize the SourceClass Object
        Summary s = new Summary(0,0,0,0);
        SourceClass sc = new SourceClass("className", "source", s);

        //Add a new mutant
        sc.AddMutant(4);

        ArrayList<Integer> expectedMutants = new ArrayList<Integer>();
        expectedMutants.add(4);
        ArrayList<Integer> actualMutants = sc.getMutants();
        assertEquals(expectedMutants, actualMutants);

    }

    @Test
    public void testToString() {

        //Initialize the SourceClass Object
        Summary s = new Summary(0,0,0,0);
        SourceClass sc = new SourceClass("className", "source", s);

        String expectedString = "className.java";
        String actualString = sc.toString();
        assertEquals(expectedString, actualString);

    }
}