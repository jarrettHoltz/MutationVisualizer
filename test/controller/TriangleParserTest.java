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
     * Test the Build Model method
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
        Directory expectedDirRoot = new Directory("src", tModel.getSummary());
        Directory expectedDirChild = new Directory("triangle", tModel.getSummary());
        
        //Check the root
        Object actualRootObject = ((DefaultMutableTreeNode)tModel.getSourceRoot()).getUserObject();
        assert actualRootObject instanceof Directory;
        Directory actualRootDir = (Directory)actualRootObject;
        Summary actualRootSummary = actualRootDir.getSummary();
        assertEquals(expectedSummary.getTotal(), actualRootSummary.getTotal());
        assertEquals(expectedSummary.getCovered(), actualRootSummary.getCovered());
        assertEquals(expectedSummary.getLive(), actualRootSummary.getLive());
        assertEquals(expectedSummary.getKilled(), actualRootSummary.getKilled());
        
        String actualRootName = actualRootDir.getName();
        String expectedRootName = "src";
        assertEquals(expectedRootName, actualRootName);

        //Check the child
        Object actualChildObject = ((DefaultMutableTreeNode)tModel.getSourceRoot().getChildAt(0)).getUserObject();
        assert actualChildObject instanceof Directory;
        Directory actualChildDir = (Directory)actualChildObject;
        Summary actualChildSummary = actualChildDir.getSummary();
        assertEquals(expectedSummary.getTotal(), actualChildSummary.getTotal());
        assertEquals(expectedSummary.getCovered(), actualChildSummary.getCovered());
        assertEquals(expectedSummary.getLive(), actualChildSummary.getLive());
        assertEquals(expectedSummary.getKilled(), actualChildSummary.getKilled());
        
        String actualChildName = actualChildDir.getName();
        String expectedChildName = "triangle";
        assertEquals(expectedChildName, actualChildName);



        //Check the parsed Tests
        expectedDirRoot = new Directory("test", tModel.getSummary());
        expectedDirChild = new Directory("triangle", tModel.getSummary());
        
        //Check the root
        actualRootObject = ((DefaultMutableTreeNode)tModel.getTestRoot()).getUserObject();
        assert actualRootObject instanceof Directory;
        actualRootDir = (Directory)actualRootObject;
        actualRootSummary = actualRootDir.getSummary();
        assertEquals(expectedSummary.getTotal(), actualRootSummary.getTotal());
        assertEquals(expectedSummary.getCovered(), actualRootSummary.getCovered());
        assertEquals(expectedSummary.getLive(), actualRootSummary.getLive());
        assertEquals(expectedSummary.getKilled(), actualRootSummary.getKilled());
        
        actualRootName = actualRootDir.getName();
        expectedRootName = "test";
        assertEquals(expectedRootName, actualRootName);

        //Check the child
        actualChildObject = ((DefaultMutableTreeNode)tModel.getTestRoot().getChildAt(0)).getUserObject();
        assert actualChildObject instanceof Directory;
        actualChildDir = (Directory)actualChildObject;
        actualChildSummary = actualChildDir.getSummary();
        assertEquals(expectedSummary.getTotal(), actualChildSummary.getTotal());
        assertEquals(expectedSummary.getCovered(), actualChildSummary.getCovered());
        assertEquals(expectedSummary.getLive(), actualChildSummary.getLive());
        assertEquals(expectedSummary.getKilled(), actualChildSummary.getKilled());
        
        actualChildName = actualChildDir.getName();
        expectedChildName = "triangle";
        assertEquals(expectedChildName, actualChildName);



        //Check the parsed Mutants
        expectedDirRoot = new Directory("mutants", tModel.getSummary());
        Directory expectedDirChildAll = new Directory("all", tModel.getSummary());
        
        //Check the root
        actualRootObject = ((DefaultMutableTreeNode)tModel.getMutantRoot()).getUserObject();
        assert actualRootObject instanceof Directory;
        actualRootDir = (Directory)actualRootObject;
        actualRootSummary = actualRootDir.getSummary();
        assertEquals(expectedSummary.getTotal(), actualRootSummary.getTotal());
        assertEquals(expectedSummary.getCovered(), actualRootSummary.getCovered());
        assertEquals(expectedSummary.getLive(), actualRootSummary.getLive());
        assertEquals(expectedSummary.getKilled(), actualRootSummary.getKilled());
        
        actualRootName = actualRootDir.getName();
        expectedRootName = "mutants";
        assertEquals(expectedRootName, actualRootName);

        //Check the child
        actualChildObject = ((DefaultMutableTreeNode)tModel.getMutantRoot().getChildAt(0)).getUserObject();
        assert actualChildObject instanceof Directory;
        actualChildDir = (Directory)actualChildObject;
        actualChildSummary = actualChildDir.getSummary();
        assertEquals(expectedSummary.getTotal(), actualChildSummary.getTotal());
        assertEquals(expectedSummary.getCovered(), actualChildSummary.getCovered());
        assertEquals(expectedSummary.getLive(), actualChildSummary.getLive());
        assertEquals(expectedSummary.getKilled(), actualChildSummary.getKilled());
        
        actualChildName = actualChildDir.getName();
        expectedChildName = "all";
        assertEquals(expectedChildName, actualChildName);


        //Check the parsed Killed
        //Create asserts later

    }
}