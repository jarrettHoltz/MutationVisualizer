package model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Test class for the Directory implementation.
 * @author mlimbird
 */
public class DirectoryTest {

    /*
     * Test the Constructor
     */
    @Test
    public void testConstructor() {

        //Make a name and a summary
        String expectedDirName = "dirName";
        Summary expectedSummary = new Summary(3,2,1,0);

        Directory d = new Directory(expectedDirName, expectedSummary);

        Summary actualSummary = d.getSummary();
        String actualName = d.getName();

        //Check the Summary
        assertEquals(expectedSummary.getTotal(), actualSummary.getTotal());
        assertEquals(expectedSummary.getCovered(), actualSummary.getCovered());
        assertEquals(expectedSummary.getLive(), actualSummary.getLive());
        assertEquals(expectedSummary.getKilled(), actualSummary.getKilled());

        //Check the directory name
        assertEquals(expectedDirName, actualName);

    }
}