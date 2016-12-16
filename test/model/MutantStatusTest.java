package model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Test class for the MutantStatus implementation.
 * @author mlimbird
 */
public class MutantStatusTest {

    @Test
    public void testFAIL() {
        assertNotNull(MutantStatus.valueOf("FAIL"));
    }

    @Test
    public void testEXCEPTION() {
        assertNotNull(MutantStatus.valueOf("EXCEPTION"));
    }

    @Test
    public void testCOVERED() {
        assertNotNull(MutantStatus.valueOf("COVERED"));
    }

    @Test
    public void testLIVE() {
        assertNotNull(MutantStatus.valueOf("LIVE"));
    }

    @Test
    public void testIsKilled() {
        MutantStatus ms = MutantStatus.FAIL;
        boolean expectedKill = true;
        boolean actualKill = ms.isKilled();
        assertEquals(expectedKill, actualKill);

        ms = MutantStatus.EXCEPTION;
        expectedKill = true;
        actualKill = ms.isKilled();
        assertEquals(expectedKill, actualKill);

        ms = MutantStatus.COVERED;
        expectedKill = false;
        actualKill = ms.isKilled();
        assertEquals(expectedKill, actualKill);

        ms = MutantStatus.LIVE;
        expectedKill = false;
        actualKill = ms.isKilled();
        assertEquals(expectedKill, actualKill);
    }

    @Test
    public void testGetKilled() {
        MutantStatus[] killed = MutantStatus.getKilled();
        MutantStatus expectedFirst = MutantStatus.FAIL;
        MutantStatus actualFirst = killed[0];
        assertEquals(expectedFirst, actualFirst);

        MutantStatus expectedSecond = MutantStatus.EXCEPTION;
        MutantStatus actualSecond = killed[1];
        assertEquals(expectedSecond, actualSecond);
    }
}