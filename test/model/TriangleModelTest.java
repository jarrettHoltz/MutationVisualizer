package model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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

        //check the mutant list
        List<Mutant> actualList = tm.getMutants();
        List<Mutant>expectedList = new ArrayList<Mutant>();
        assertEquals(expectedList, actualList);

        //Check the sources
        Map<String,SourceClass> actualSources = tm.getSources();
        Map<String,SourceClass> expectedSources = new HashMap<String,SourceClass>();
        assertEquals(expectedSources, actualSources);

        //Check the tests
        List<model.Test> actualTests = tm.getTests();
        List<model.Test> expectedTests = new ArrayList<model.Test>();
        assertEquals(expectedTests, actualTests);

        //Check the mutators
        Map<MutatorType, Mutator> actualMutators = tm.getMutators();
        Map<MutatorType, Mutator> expectedMutators = new HashMap<MutatorType, Mutator>();
        for(MutatorType type : MutatorType.values()) {
            Mutator m = actualMutators.get(type);
            assertNotNull(m);
        }
        int expectedSize = 5;
        int actualSize = actualMutators.size();
        assertEquals(expectedSize, actualSize);
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

    //Future testing should look at the other smaller functions that we recently created
}