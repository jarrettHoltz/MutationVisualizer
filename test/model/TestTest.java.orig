package model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import static model.MutatorType.*;

/**
 * Test class for the Test implementation.
 * @author mlimbird
 */
public class TestTest {

    /*
     * Test the 
     */
    @Test
    public void testConstructor() {

        //Initialize the SourceClass Object
        Summary s = new Summary(0,0,0,0);
        model.Test t = new model.Test("testName", "source", s);

        //Test ClassName
        String actualClass = t.getName();
        String expectedClass = "testName";
        assertEquals(expectedClass, actualClass);

        //Test Source
        String actualSource = t.getSource();
        String expectedSource = "source";
        assertEquals(expectedSource, actualSource);

        //Test Source
        Summary actualSummary = t.getSummary();
        Summary expectedSummary = new Summary(0,0,0,0);
        assertEquals(expectedSummary.getTotal(), actualSummary.getTotal());
        assertEquals(expectedSummary.getCovered(), actualSummary.getCovered());
        assertEquals(expectedSummary.getLive(), actualSummary.getLive());
        assertEquals(expectedSummary.getKilled(), actualSummary.getKilled());

        //Test Mutants
        List<Mutant> actualMutants = t.getMutants();
        List<Mutant> expectedMutants = new ArrayList<Mutant>();
        assertEquals(expectedMutants, actualMutants);
    }

    @Test
    public void testAddMutant() {

        //Initialize the SourceClass Object
        Summary s = new Summary(0,0,0,0);
        model.Test t = new model.Test("testName", "source", s);

        //Add a new mutant
<<<<<<< HEAD
        Mutant m = new Mutant(2, LVR, "class", "newName", 2, "new\nSource");
=======
    	SourceClass sourceClass = new SourceClass("class", "", null);
        Mutant m = new Mutant(2, LVR, sourceClass, "newName", 3, " \n \n \n ");
>>>>>>> ebc89a27310c3733597a401350c6bfe46a0edd4d
        t.AddMutant(m);

        List<Mutant> expectedMutants = new ArrayList<Mutant>();
        expectedMutants.add(m);
        List<Mutant> actualMutants = t.getMutants();
        assertEquals(expectedMutants, actualMutants);

    }

    @Test
    public void testToString() {

        //Initialize the SourceClass Object
        Summary s = new Summary(0,0,0,0);
        model.Test t = new model.Test("testName", "source", s);

        String expectedString = "testName.java";
        String actualString = t.toString();
        assertEquals(expectedString, actualString);

    }
}