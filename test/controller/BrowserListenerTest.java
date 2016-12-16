package controller;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import model.TriangleModel;
import model.MutantVizModel;
import view.MutantVizWindow;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;

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

        //Make a treeSelectionEvent
        TreePath tp = new TreePath(new Object[] {new DefaultMutableTreeNode(), new DefaultMutableTreeNode(), new DefaultMutableTreeNode()});
        TreeSelectionEvent tse = new TreeSelectionEvent(new Object(), tp, true, null, null);

        //Check the value changed function
        bLis.valueChanged(tse);

        MutantVizWindow mvw = bLis.getWindow();
        String expectedView = "SummaryView";
        String actualView = mvw.getCurrentView();
        assertEquals(expectedView, actualView);
    }
}