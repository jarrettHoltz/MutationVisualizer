package model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import static model.MutatorType.*;

/**
 * Test class for the MutatorType implementation.
 * @author mlimbird
 */
public class MutatorTypeTest {

    /*
     * Test the Enum MutatorType
     */
    @Test
    public void testLVR() {
        assertNotNull(MutatorType.valueOf("LVR"));
    }

    @Test
    public void testROR() {
        assertNotNull(MutatorType.valueOf("ROR"));
    }

    @Test
    public void testCOR() {
        assertNotNull(MutatorType.valueOf("COR"));
    }

    @Test
    public void testSTD() {
        assertNotNull(MutatorType.valueOf("STD"));
    }

    @Test
    public void testAOR() {
        assertNotNull(MutatorType.valueOf("AOR"));
    }
}