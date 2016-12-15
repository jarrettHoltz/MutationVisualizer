package view;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.CollapsiblePanelAction;
import model.MutantVizModel;
import model.SourceCode;
import model.TriangleModel;
import controller.TriangleParser;
import java.awt.LayoutManager;

/**
 * Test class for the MutantVizWindow implementation.
 * @author mlimbird
 */
public class MutantVizWindowTest {

    /*
     * Test the 
     */
    @Test
    public void testConstructor() {
        //Create the window
        TriangleModel model = new TriangleModel();
        TriangleParser parser = new TriangleParser();
        parser.buildModel(model,"triangle/mutation_results", "triangle/src", "triangle/test" );
        MutantVizWindow view = new MutantVizWindow(model);

        //Check the title of the window
        String expectedTitle = "Mutation Visualizer";
        String actualTitle = view.getTitle();
        assertEquals(expectedTitle, actualTitle);

        //Check contentPane layout
        LayoutManager actualLayout = view.getContentPane().getLayout();
        assert actualLayout instanceof BoxLayout;

        //Check the workings of buildPanels
        BrowserPanel expectedBPanel = new BrowserPanel(model);
        BrowserPanel actualBPanel = view.getBrowserPanel();
        assertEquals(expectedBPanel, actualBPanel);
        
        //String expectedBTitle = "Triangle";


    }

    @Test
    public void testSetNode() {
        
    }

    @Test
    public void testCodeView() {
        
    }

    @Test
    public void testSummaryView() {
        
    }

    @Test
    public void testSetComparison() {
        
    }

    @Test
    public void testSetTreeSelectionListener() {
        
    }

    @Test
    public void testSetActionListener() {
        
    }

    @Test
    public void testSetMouseListener() {
        
    }
}