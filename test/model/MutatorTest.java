package model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static model.MutatorType.*;

/**
 * Test class for the Mutator implementation.
 * @author mlimbird
 */
public class MutatorTest {

    /**
     * Test the constructor of MutatorTest
     */
    @Test
    public void testConstructor() {
        
        //Initialize the Mutator
        Mutator m = new Mutator("newName");

        //Check the Name
        String actualName = m.getName();
        String expectedName = "newName";
        assertEquals(expectedName, actualName);

        //Check the Summary
        Summary actualSummary = m.getSummary();
        Summary expectedSummary = new Summary(0,0,0,0);
        //need to check the total, covered, live, and killed of both summaries
        assertEquals(expectedSummary.getTotal(), actualSummary.getTotal());
        assertEquals(expectedSummary.getCovered(), actualSummary.getCovered());
        assertEquals(expectedSummary.getLive(), actualSummary.getLive());
        assertEquals(expectedSummary.getKilled(), actualSummary.getKilled());

        //Check the Mutants
        List<Mutant> actualMutants = m.getMutants();
        List<Mutant> expectedMutants = new ArrayList<Mutant>();
        assertEquals(expectedMutants, actualMutants);
    }

    @Test
    public void testAddMutant() {
        
        //Initialize the Mutator
        Mutator mt = new Mutator("newName");

        //Initialize a new Mutant
        Mutant m = new Mutant(2, LVR, "class", "newName", 3, "newSource");

        //Check adding the mutant
        mt.addMutant(m);
        List<Mutant> actualMutants = mt.getMutants();
        List<Mutant> expectedMutants = new ArrayList<Mutant>();
        expectedMutants.add(m);
        assertEquals(expectedMutants, actualMutants);

    }

    @Test
    public void testUpdateSummary() {
        
        //Initialize the Mutator
        Mutator mt = new Mutator("newName");

        //Initialize a mutant to add
        Mutant m = new Mutant(2, LVR, "class", "newName", 3, "newSource");

        //Initialize the Model to test with
        TriangleModel triMod = new TriangleModel();

        //Check the updateSummary without adding mutant
        mt.updateSummary(triMod);



        //Check the updateSummary with a new mutant
        mt.addMutant(m);

    }


















}