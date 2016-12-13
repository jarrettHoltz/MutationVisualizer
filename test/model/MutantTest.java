package model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import static model.MutatorType.*;

/**
 * Test class for the Mutant implementation.
 */
public class MutantTest {

    @Test
    public void testConstructor() {

        //Initialize the Mutant
        Mutant m = new Mutant(2, LVR, "class", "newName", 3, "newSource");

        //Check mutantId
        int actualId = m.getMutantId();
        int expectedId = 2;
        assertEquals(expectedId, actualId);

        //Check mutator
        MutatorType actualType = m.getMutator();
        MutatorType expectedType = LVR;
        assertEquals(expectedType, actualType);

        //Check Status
        String actualStatus = m.getStatus();
        String expectedStatus = "unknown";
        assertEquals(expectedStatus, actualStatus);

        //Check Class Name
        String actualClass = m.getClassName();
        String expectedClass = "class";
        assertEquals(expectedClass, actualClass);

        //Check Method Name
        String actualMethod = m.getMethodName();
        String expectedMethod = "newName";
        assertEquals(expectedMethod, actualMethod);

        //Check Line Number
        int actualLine = m.getLineNumber();
        int expectedLine = 3;
        assertEquals(expectedLine, actualLine);

        //Check Mutant Source
        String actualSource = m.getSource();
        String expectedSource = "newSource";
        assertEquals(expectedSource, actualSource);

        //Check Tests
        ArrayList<model.Test> actualTests = m.getMutatorTests();
        ArrayList<model.Test> expectedTests = new ArrayList<model.Test>();
        assertEquals(expectedTests, actualTests);
    }

    @Test
    public void testAddTest() {

        //Initialize the Mutant
        Mutant m = new Mutant(2, LVR, "class", "newName", 3, "newSource");

        //Initialize a new test
        Summary newSum = new Summary(2, 3, 4, 5);
        model.Test test = new model.Test("testName", "source", newSum);

        //Check Test was added successfully
        m.AddTest(test);

        ArrayList<model.Test> actualTests = m.getMutatorTests();
        ArrayList<model.Test> expectedTests = new ArrayList<model.Test>();
        expectedTests.add(test);
        assertEquals(expectedTests, actualTests);
    }

    @Test
    public void testToString() {

        //Initialize the Mutant
        Mutant m = new Mutant(2, LVR, "class", "newName", 3, "newSource");

        String actual = m.toString();
        String expected = "2 class (LVR) unknown";
        assertEquals(expected, actual);
    }

}