package controller;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Test class for the CollapsiblePanelAction implementation.
 * @author mlimbird
 */
public class CollapsiblePanelActionTest {

    /*
     * Test the different parts of the enum object
     */
    @Test
    public void testCode() {
        assertNotNull(CollapsiblePanelAction.valueOf("EXPAND_CODE"));
    }

    @Test
    public void testComparison() {
        assertNotNull(CollapsiblePanelAction.valueOf("EXPAND_COMPARISON"));
    }

    @Test
    public void testSummary() {
        assertNotNull(CollapsiblePanelAction.valueOf("EXPAND_SUMMARY"));
    }
}