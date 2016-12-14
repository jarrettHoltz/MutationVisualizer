package controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.tree.DefaultMutableTreeNode;

import model.Directory;
import model.Mutant;
import model.MutantStatus;
import model.MutantVizModel;
import model.MutatorType;
import model.SourceClass;
import model.Summary;
import model.Test;

/**
 * The parser is the main class for extracting data
 * from a given Major mutation analysis output directory.
 * @author jaholtz
 *
 */
public class TriangleParser implements MutantVizController{
	MutantVizModel model;
	
	/**
	 * Parses the files in the given directories to fill
	 * a provided MutantVizModel object with the required data
	 * @param model, the MutantVizModel to fill
	 * @param mutDir, directory for mutation analysis
	 * @param sourceDir, directory for source code
	 * @param testDir, directory for test code
	 */
	public void buildModel(MutantVizModel model, 
			String mutDir, String sourceDir, String testDir) {
		
		this.model = model;
		
		// Parse files and add data to model
		parseSummary(mutDir);
		
		parseSource(sourceDir);
		
		parseTests(testDir);
		
		parseMutants(mutDir);
		
		parseKilled(mutDir);
	}
	
	/**
	 * 
	 * @param directory, mutation analysis results directory (user input)
	 */
	private void parseSummary(String directory) {
		int total = 0;
		int covered = 0;
		int live = 0;
		int killed = 0;
		// Reading block
		try (BufferedReader br = new BufferedReader(new FileReader(directory + "/summary.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineSplit = line.split(",");
				if(!lineSplit[0].equals( "MutantsGenerated")) {
					total = Integer.parseInt(lineSplit[0]);
					covered = Integer.parseInt(lineSplit[1]);
					killed = Integer.parseInt(lineSplit[2]);
					live = Integer.parseInt(lineSplit[3]);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// End Reading Block
		Summary summary = new Summary(total, covered, live, killed);
		this.model.setSummary(summary);
	}
	
	/**
	 * 
	 * @param directory
	 */
	private void parseMutants(String directory) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new Directory("mutants", model.getSummary()));
		DefaultMutableTreeNode all = new DefaultMutableTreeNode(new Directory("all", model.getSummary()));
		root.add(all);
		Map<String,DefaultMutableTreeNode> mutators = new HashMap<String,DefaultMutableTreeNode>();
		for(MutatorType type : MutatorType.values()) {
			mutators.put(type.name(), new DefaultMutableTreeNode(model.getMutator(type)));
			root.add(mutators.get(type.name()));
		}
		try (BufferedReader br = new BufferedReader(new FileReader(directory + "/mutants.log"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineSplit = line.split(":");
				// Store all mutant information here
				int mutantId = Integer.parseInt(lineSplit[0]);
				String mutator = lineSplit[1];
				String method = lineSplit[4];
				String[] methodSplit = method.split("@");
				String classPath = methodSplit[0];
				method = methodSplit[1];
				SourceClass sourceClass = model.getSourceClass(classPath);
				int lineNumber = Integer.parseInt(lineSplit[5]);
				String mutantDir = directory + "/mutants/" + lineSplit[0] + "/triangle/Triangle.java";
				String mutantSource = slurpFile(mutantDir);
				Mutant mutant = new Mutant(mutantId, 
						MutatorType.valueOf(mutator), 
						sourceClass, 
						method,
						lineNumber,
						mutantSource);
				model.addMutant(mutant);
				all.add(new DefaultMutableTreeNode(mutant));
				mutators.get(mutator).add(new DefaultMutableTreeNode(mutant));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.setMutantRoot(root);
	}
	
	/**
	 * 
	 * @param directory
	 */
	private void parseKilled(String directory) {
		try (BufferedReader br = new BufferedReader(new FileReader(directory + "/killed.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineSplit = line.split(",");
				// Store all mutant information here
				if(lineSplit[0].equals("MutantNo")) {
					
				} else {
					Mutant mutant = model.getMutant(Integer.parseInt(lineSplit[0]));
					mutant.setStatus(MutantStatus.valueOf(lineSplit[1]));
					if(mutant.getStatus().isKilled()) {
						//Note: assumes only one test; this is specific to the Triangle program
						Test theTest = (Test) ((DefaultMutableTreeNode)model.getTestRoot().getChildAt(0).getChildAt(0)).getUserObject();
						//Add tests that killed this mutant
						mutant.AddTest(theTest);
						//Add this mutant to tests that killed it
						theTest.AddMutant(mutant);
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Update the summaries for the Mutators now that we've assigned the status to each mutant
		for(MutatorType type : MutatorType.values()) {
			model.getMutator(type).updateSummary(model);
		}
	}
	
	/**
	 * 
	 * @param directory
	 */
	private void parseSource(String directory) {
		String sourceRootFolder = directory.replaceFirst("^.*/([^/]*)$", "$1"); //get the last folder in the path
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new Directory(sourceRootFolder, model.getSummary()));
		DefaultMutableTreeNode triangle = new DefaultMutableTreeNode(new Directory("triangle", model.getSummary()));
		root.add(triangle);
		//EXTENSION: actually enumerate the source files and add them all in
		String sourcePath = directory + "/triangle/Triangle.java";
		String classPath = "triangle.Triangle";
		try {
			String source = slurpFile(sourcePath);
			SourceClass sourceClass = new SourceClass("Triangle", source, model.getSummary());
			model.AddSource(classPath, sourceClass);
			triangle.add(new DefaultMutableTreeNode(sourceClass));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.setSourceRoot(root);
	}
	
	private void parseTests(String directory) {
		String sourceRootFolder = directory.replaceFirst("^.*/([^/]*)$", "$1"); //get the last folder in the path
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new Directory(sourceRootFolder, model.getSummary()));
		DefaultMutableTreeNode triangle = new DefaultMutableTreeNode(new Directory("triangle", model.getSummary()));
		root.add(triangle);
		String sourcePath = directory + "/triangle/TriangleTest.java";
		try {
			String source = slurpFile(sourcePath);
			Test testClass = new Test("TriangleTest", source, model.getSummary());
			triangle.add(new DefaultMutableTreeNode(testClass));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.setTestRoot(root);
	}
	
	private String slurpFile(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path));
		String contents = sc.useDelimiter("\\Z").next();
		sc.close();
		return contents;
	}
}
