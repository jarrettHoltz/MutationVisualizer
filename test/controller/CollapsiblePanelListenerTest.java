package controller;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import model.TriangleModel;
import model.MutantVizModel;
import view.MutantVizWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.util.List;

/**
 * Test class for the CollapsiblePanelListener implementation.
 * @author mlimbird
 */
public class CollapsiblePanelListenerTest {

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
        CollapsiblePanelListener cLis = new CollapsiblePanelListener(mutantVizW);

        //Check that the window was attached
        MutantVizWindow expectedWindow = mutantVizW;
        MutantVizWindow actualWindow = cLis.getWindow();
        assertEquals(expectedWindow, actualWindow);
    }

    @Test
    public void testActionPerformed() {
        //Setup the environment
        TriangleModel tModel = new TriangleModel();
        TriangleParser parser = new TriangleParser();
        parser.buildModel(tModel,"test_files/triangle/mutation_results", "test_files/triangle/src", "test_files/triangle/test" );
        MutantVizWindow mutantVizW = new MutantVizWindow(tModel);
    
        //Instantiate a CollapsiblePanelListener
        CollapsiblePanelListener cLis = new CollapsiblePanelListener(mutantVizW);

        //Add a listener and create an event
        mutantVizW.setActionListener(cLis);
        //Summary panel, code panel, compare panel
        List<JButton> buttonList = mutantVizW.getButtons();
        
        //Check the action performed made Summary View current
        ActionEvent ae = new ActionEvent(buttonList.get(0), 0, "EXPAND_SUMMARY");
        cLis.actionPerformed(ae);
        String actualView = mutantVizW.getCurrentView();
        String expectedView = "SummaryView";
        assertEquals(expectedView, actualView);

        //Check the action performed made Summary View current
        ae = new ActionEvent(buttonList.get(1), 0, "EXPAND_CODE");
        cLis.actionPerformed(ae);
        actualView = mutantVizW.getCurrentView();
        expectedView = "CodeView";
        assertEquals(expectedView, actualView);

        //Would check the Comparison, but not used
        // ae = new ActionEvent(buttonList.get(2), 0, "EXPAND_COMPARISON");
        // cLis.actionPerformed(ae);
        // actualView = mutantVizW.getCurrentView();
        // expectedView = "ComparisonView";
        // assertEquals(expectedView, actualView);
    }

}