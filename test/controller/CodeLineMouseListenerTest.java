package controller;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import model.TriangleModel;
import model.MutantVizModel;
import view.MutantVizWindow;
import java.awt.event.MouseEvent;

/**
 * Test class for the CodeLineMouseListener implementation.
 * @author mlimbird
 */
public class CodeLineMouseListenerTest {

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
    
        //Instantiate a CodeLineMouseListener
        CodeLineMouseListener cLis = new CodeLineMouseListener(mutantVizW);

        //Check that the window was attached
        MutantVizWindow expectedWindow = mutantVizW;
        MutantVizWindow actualWindow = cLis.getWindow();
        assertEquals(expectedWindow, actualWindow);
    }

    @Test
    public void testMouseClicked() {
        //Setup the environment
        TriangleModel tModel = new TriangleModel();
        TriangleParser parser = new TriangleParser();
        parser.buildModel(tModel,"test_files/triangle/mutation_results", "test_files/triangle/src", "test_files/triangle/test" );
        MutantVizWindow mutantVizW = new MutantVizWindow(tModel);
    
        //Instantiate a CodeLineMouseListener
        CodeLineMouseListener cLis = new CodeLineMouseListener(mutantVizW);

        //Add a listener and throw event
        mutantVizW.setMouseListener(cLis);
        MouseEvent me = new MouseEvent(mutantVizW.getCodePanel(), 0, 0, 0, 100, 100, 1, false);

        //Check the mouse clicked event
        cLis.mouseClicked(me);
        //need a better test here.
    }
}