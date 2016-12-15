package controller;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import model.TriangleModel;
import model.MutantVizModel;
import view.MutantVizWindow;

/**
 * Test class for the BrowserListener implementation.
 * @author mlimbird
 */
public class BrowserListenerTest {

    /*
     * Test the Constructor
     */
    @Test
    public void testConstructor() {
        //Setup the environment
        TriangleModel tModel = new TriangleModel();
        TriangleParser parser = new TriangleParser();
        parser.buildModel(tModel,"test_files/triangle/mutation_results", "test_files/triangle/src", "test_files/triangle/test" );
        MutantVizWindow mutantVizW = new MutantVizWindow(tModel);
    
        //Instantiate a BrowserListener
        BrowserListener bLis = new BrowserListener(mutantVizW);

        //Check that the window was attached
        MutantVizWindow expectedWindow = mutantVizW;
        MutantVizWindow actualWindow = bLis.getWindow();
        assertEquals(expectedWindow, actualWindow);
    }

    @Test
    public void testValueChanged() {
        //Setup the environment
        TriangleModel tModel = new TriangleModel();
        TriangleParser parser = new TriangleParser();
        parser.buildModel(tModel,"test_files/triangle/mutation_results", "test_files/triangle/src", "test_files/triangle/test" );
        MutantVizWindow mutantVizW = new MutantVizWindow(tModel);
    
        //Instantiate a BrowserListener
        BrowserListener bLis = new BrowserListener(mutantVizW);

        //Add a listener
        mutantVizW.setTreeSelectionListener(bLis);


        //Check the value changed function
        
    }
}