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
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.CollapsiblePanelAction;
import model.MutantVizModel;
import model.SourceCode;
import model.TriangleModel;
import controller.TriangleParser;
import java.awt.LayoutManager;
import javax.swing.JRootPane;

/**
 * Test class for the MutantVizWindow implementation.
 * @author mlimbird
 */
public class MutantVizWindowTest {
    private static double EPS = 1e-9;

    /*
     * Test the constructor
     */
    @Test
    public void testConstructor() {
        //Create the window
        TriangleModel model = new TriangleModel();
        TriangleParser parser = new TriangleParser();
        parser.buildModel(model,"test_files/triangle/mutation_results", "test_files/triangle/src", "test_files/triangle/test" );
        MutantVizWindow view = new MutantVizWindow(model);

        //Check the title of the window
        String expectedTitle = "Mutation Visualizer";
        String actualTitle = view.getTitle();
        assertEquals(expectedTitle, actualTitle);

        //Check contentPane layout
        LayoutManager actualLayout = view.getContentPane().getLayout();
        assert actualLayout instanceof BoxLayout;

        //Check the workings of buildPanels
        String expectedBTitle = "Browser: Triangle";
        BrowserPanel actualBPanel = view.getBrowserPanel();
        assertEquals(expectedBTitle, actualBPanel.getPanelTitle().getText());
        
        
    }

    @Test
    public void testSetNode() {
        //Create the window
        TriangleModel model = new TriangleModel();
        TriangleParser parser = new TriangleParser();
        parser.buildModel(model,"test_files/triangle/mutation_results", "test_files/triangle/src", "test_files/triangle/test" );
        MutantVizWindow view = new MutantVizWindow(model);

        DefaultMutableTreeNode testRoot = (DefaultMutableTreeNode)model.getTestRoot();
        view.setNode(testRoot);

        String expectedCurrentView = "SummaryView";
        String actualCurrentView = view.getCurrentView();
        assertEquals(expectedCurrentView, actualCurrentView);

        //Need test for when sorcecode is the instance of the object
    }

    @Test
    public void testCodeView() {
        TriangleModel model = new TriangleModel();
        TriangleParser parser = new TriangleParser();
        parser.buildModel(model,"test_files/triangle/mutation_results", "test_files/triangle/src", "test_files/triangle/test" );
        MutantVizWindow view = new MutantVizWindow(model);

        view.setCodeView(true);

        //Need to change this to get the added collapsedSummaryPanel
        // int lastChild = view.getComponentCount();
        // Component cChild = ((JRootPane)view.getComponent(lastChild)).getContentPane();
        // assert cChild instanceof JPanel;
        // JPanel collapsedSumPan = (JPanel)cChild;

        // // LayoutManager viewLayout = .getLayout();
        // // assert viewLayout instanceof GridBagLayout;
        // // GridBagLayout actualLayout = (GridBagLayout)viewLayout;
        // // GridBagConstraints actualConstraints = actualLayout.getConstraints(collapsedSumPan);

        // // int expectedGridY = 1;
        // // assertEquals(expectedGridY, actualConstraints.gridy);
        // // int expectedGridWidth = 2;
        // // assertEquals(expectedGridWidth, actualConstraints.gridwidth);
        // // double expectedWeightX = 0.3;
        // // assertEquals(expectedWeightX, actualConstraints.weightx, EPS);
        // // double expectedWeightY = 0.05;
        // // assertEquals(expectedWeightY, actualConstraints.weighty, EPS);

        String expectedCurrentView = "CodeView";
        String actualCurrentView = view.getCurrentView();
        assertEquals(expectedCurrentView, actualCurrentView);

    }

    @Test
    public void testSummaryView() {
        TriangleModel model = new TriangleModel();
        TriangleParser parser = new TriangleParser();
        parser.buildModel(model,"test_files/triangle/mutation_results", "test_files/triangle/src", "test_files/triangle/test" );
        MutantVizWindow view = new MutantVizWindow(model);

        view.setSummaryView(true);

        String expectedCurrentView = "SummaryView";
        String actualCurrentView = view.getCurrentView();
        assertEquals(expectedCurrentView, actualCurrentView);
    }

    @Test
    public void testSetComparison() {
        TriangleModel model = new TriangleModel();
        TriangleParser parser = new TriangleParser();
        parser.buildModel(model,"test_files/triangle/mutation_results", "test_files/triangle/src", "test_files/triangle/test" );
        MutantVizWindow view = new MutantVizWindow(model);

        CodeLine cl = new CodeLine("the code", 3, 3);
        view.setComparison(cl);

        String expectedCurrentView = "ComparisonView";
        String actualCurrentView = view.getCurrentView();
        assertEquals(expectedCurrentView, actualCurrentView);
    }
}