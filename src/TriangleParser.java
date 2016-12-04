import java.io.BufferedReader;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The parser is the main class for extracting data
 * from a given Major mutation analysis output directory.
 * @author jaholtz
 *
 */
public class TriangleParser implements MutantVizController{
	MutantVizModel model;
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
				if(line_split[0].equals( "MutantsGenerated")) {
					
				} else {
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
		this.model.SetSummary(summary);
	}
	
	/**
	 * 
	 * @param directory
	 */
	private void ParseMutants(String directory) {
		// Reading block
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
				String mutant_source = new Scanner(new File(mutant_dir)).useDelimiter("\\Z").next();
				Mutant mutant = new Mutant(mutant_id, 
						mutator, 
						class_name, 
						method,
						line_number,
						mutant_source);
				this.model.AddMutant(mutant);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// End Reading Block
	}
	
	/**
	 * 
	 * @param directory
	 */
	private void ParseKilled(String directory) {
		// Reading block
		try (BufferedReader br = new BufferedReader(new FileReader(directory + "/killed.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] line_split = line.split(",");
				// Store all mutant information here
				if(line_split[0].equals("MutantNo")) {
					
				} else {
					Mutant mutant = model.GetMutant(Integer.parseInt(line_split[0]));
					mutant.status = line_split[1];
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
	}
	
	/**
	 * 
	 * @param directory
	 */
	private void ParseSource(String directory) {
		// Reading block
		String source_path = directory + "/triangle/Triangle.java";
		try {
			String source = new Scanner(new File(source_path)).useDelimiter("\\Z").next();
			SourceClass source_class = new SourceClass("Triangle", source, model.GetSummary());
			System.out.println(source_class.ClassName);
			model.AddSource(source_class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void ParseTests(String directory) {
		// Reading block
				String source_path = directory + "/triangle/TriangleTest.java";
				try {
					String source = new Scanner(new File(source_path)).useDelimiter("\\Z").next();
					Test test_class = new Test(source, model.GetSummary());
					model.AddTest(test_class);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

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
		
		System.out.println("BuildModel Called");
		System.out.println(mut_dir);
		this.model = model;
		
		// Parse files and add data to model
		ParseSummary(mut_dir);
				
		ParseSource(source_dir);
		
		ParseTests(test_dir);
		
		ParseMutants(mut_dir);
		
		ParseKilled(mut_dir);
	}

}
