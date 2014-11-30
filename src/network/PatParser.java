package network;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class PatParser {
	private int inputs;
	private int outputs;
	private int lines;
	private Scanner scanner;
	
	public PatParser (String path, int inputs, int outputs){
		this.inputs = inputs;
		this.outputs = outputs;

		//Open file
		File f = new File (path);
		try {
			scanner = new Scanner (f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.useLocale(Locale.US);
		
		//Jump to 4rd line
		for (int i = 0; i < 3; i++)
			scanner.nextLine();
		
		//Parse the line count
		String patternLine = scanner.nextLine();
		lines = Integer.valueOf(patternLine.split(" : ")[1]);
		
		//Jump to 7th line
		for (int i = 0; i < 2; i++)
			scanner.nextLine();
		
		System.out.println(scanner.nextLine());
	}
	
	/**
	 * Parse the next line.
	 * @return the values of the next line.
	 */
	public ArrayList<Double> getNext (){
		ArrayList<Double> line = new ArrayList<Double> (inputs + outputs);
		
		for (int i = 0; i < inputs + outputs; i++){
			line.add(Double.valueOf(scanner.nextDouble()));
		}
		
		return line;
	}

	/**
	 * @return the lines
	 */
	public int getLines() {
		return lines;
	}
}
