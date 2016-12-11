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
	 * @param mut_dir, directory for mutation analysis
	 * @param source_dir, directory for source code
	 * @param test_dir, directory for test code
	 */
	public void BuildModel(MutantVizModel model, 
			String mut_dir, String source_dir, String test_dir) {
		
		this.model = model;
		
		// Parse files and add data to model
		ParseSummary(mut_dir);
		
		ParseSource(source_dir);
		
		ParseTests(test_dir);
		
		ParseMutants(mut_dir);
		
		ParseKilled(mut_dir);
	}
	
	/**
	 * 
	 * @param directory, mutation analysis results directory (user input)
	 */
	private void ParseSummary(String directory) {
		int total = 0;
		int covered = 0;
		int live = 0;
		int killed = 0;
		// Reading block
		try (BufferedReader br = new BufferedReader(new FileReader(directory + "/summary.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] line_split = line.split(",");
				if(!line_split[0].equals( "MutantsGenerated")) {
					total = Integer.parseInt(line_split[0]);
					covered = Integer.parseInt(line_split[1]);
					killed = Integer.parseInt(line_split[2]);
					live = Integer.parseInt(line_split[3]);
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
	private void ParseMutants(String directory) {
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
				String[] line_split = line.split(":");
				// Store all mutant information here
				int mutant_id = Integer.parseInt(line_split[0]);
				String mutator = line_split[1];
				String method = line_split[4];
				String[] method_split = method.split("@");
				String class_name = method_split[0];
				method = method_split[1];
				String[] split = class_name.split("\\.");
				class_name = split[1];
				int line_number = Integer.parseInt(line_split[5]);
				String mutant_dir = directory + "/mutants/" + line_split[0] + "/triangle/Triangle.java";
				String mutant_source = SlurpFile(mutant_dir);
				Mutant mutant = new Mutant(mutant_id, 
						MutatorType.valueOf(mutator), 
						class_name, 
						method,
						line_number,
						mutant_source);
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
	private void ParseKilled(String directory) {
		try (BufferedReader br = new BufferedReader(new FileReader(directory + "/killed.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] line_split = line.split(",");
				// Store all mutant information here
				if(line_split[0].equals("MutantNo")) {
					
				} else {
					Mutant mutant = model.getMutant(Integer.parseInt(line_split[0]));
					mutant.setStatus(line_split[1]);
					//mutant.status = line_split[1];
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
	private void ParseSource(String directory) {
		String sourceRootFolder = directory.replaceFirst("^.*/([^/]*)$", "$1"); //get the last folder in the path
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new Directory(sourceRootFolder, model.getSummary()));
		DefaultMutableTreeNode triangle = new DefaultMutableTreeNode(new Directory("triangle", model.getSummary()));
		root.add(triangle);
		String sourcePath = directory + "/triangle/Triangle.java";
		try {
			String source = SlurpFile(sourcePath);
			SourceClass sourceClass = new SourceClass("Triangle", source, model.getSummary());
			model.AddSource(sourceClass);
			triangle.add(new DefaultMutableTreeNode(sourceClass));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.setSourceRoot(root);
	}
	
	private void ParseTests(String directory) {
		String sourceRootFolder = directory.replaceFirst("^.*/([^/]*)$", "$1"); //get the last folder in the path
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new Directory(sourceRootFolder, model.getSummary()));
		DefaultMutableTreeNode triangle = new DefaultMutableTreeNode(new Directory("triangle", model.getSummary()));
		root.add(triangle);
		String source_path = directory + "/triangle/TriangleTest.java";
		try {
			String source = SlurpFile(source_path);
			Test test_class = new Test("TriangleTest", source, model.getSummary());
			triangle.add(new DefaultMutableTreeNode(test_class));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.setTestRoot(root);
	}
	
	private String SlurpFile(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path));
		String contents = sc.useDelimiter("\\Z").next();
		sc.close();
		return contents;
	}
}
