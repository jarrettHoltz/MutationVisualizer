package controller;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Directory;
import model.Mutant;
import model.MutantStatus;
import model.MutantVizModel;
import model.MutatorType;
import model.SourceClass;
import model.TriangleModel;
import model.Summary;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * Test class for the TriangleParser implementation.
 * @author mlimbird
 */
public class TriangleParserTest {

    /*
     * Test the Constructor
     */
    @Test
    public void testBuildModel() {
        //Create a MutantVizModel
        MutantVizModel tModel = new TriangleModel();

        //Initialize a Triangle Parser
        TriangleParser parser = new TriangleParser();

        //Build the model
        parser.buildModel(tModel,"test_files/triangle/mutation_results", "test_files/triangle/src", "test_files/triangle/test" );
    
        //Check the model
        MutantVizModel actualModel = parser.getModel();
        MutantVizModel expectedModel = tModel;
        assertEquals(expectedModel, actualModel);

        //Check the parsed Summary
        Summary expectedSummary = new Summary(150,150,10,140);
        Summary actualSummary = tModel.getSummary();
        assertEquals(expectedSummary.getTotal(), actualSummary.getTotal());
        assertEquals(expectedSummary.getCovered(), actualSummary.getCovered());
        assertEquals(expectedSummary.getLive(), actualSummary.getLive());
        assertEquals(expectedSummary.getKilled(), actualSummary.getKilled());

        //Check the parsed Source
        // DefaultMutableTreeNode expectedRoot = new DefaultMutableTreeNode(new Directory("src", tModel.getSummary()));
        // DefaultMutableTreeNode triangle = new DefaultMutableTreeNode(new Directory("triangle", tModel.getSummary()));
        // expectedRoot.add(triangle);

        // String source = null;
        // try {
        //     String sourcePath = "src" + "/triangle/Triangle.java";
        //     Scanner sc = new Scanner(new File(sourcePath));
        //     String contents = sc.useDelimiter("\\Z").next();
        //     sc.close();
        //     source = contents;
        // }catch (FileNotFoundException e) {
        //     e.printStackTrace();
        // }

        // model.Test test_class = new model.Test("TriangleTest", source, tModel.getSummary());
        // triangle.add(new DefaultMutableTreeNode(test_class));

        // TreeNode actualRoot = tModel.getSourceRoot();
        // assertEquals(expectedRoot, actualRoot);

        //Check the parsed Tests

        //Check the parsed Mutants

        //Check the parsed Killed

    }

    @Test
    public void testParseSummary() {
        
    }

    @Test
    public void testParseMutants() {
        
    }

    @Test
    public void testParseSource() {
        
    }

    @Test
    public void testParseTests() {
        
    }

    @Test
    public void testSlurpFile() {
        
    }
}