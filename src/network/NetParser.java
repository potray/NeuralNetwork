/**
 * 
 */
package network;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Reads a .net file generated by JavaNNS with 1 input layer, 1 hidden layer and 1 output layer.
 * @author Daniel
 *
 */
public class NetParser {
	private String path;
	protected int inputNeurons;
	protected int hiddenNeurons;
	protected int outputNeurons;
	protected ArrayList <Double> inputNeuronsBiases;
	protected ArrayList <Double> hiddenNeuronsBiases;
	protected ArrayList <Double> outputNeuronsBiases;
	protected ArrayList <ArrayList <Double>> hiddenConnections;
	protected ArrayList <ArrayList <Double>> outputConnections;
	private Scanner s;
	private Scanner lineScanner;
	
	
	public NetParser (String path, int inputNeurons, int hiddenNeurons, int outputNeurons){
		this.path = path;
		this.inputNeurons = inputNeurons;
		this.hiddenNeurons = hiddenNeurons;
		this.outputNeurons = outputNeurons;
		inputNeuronsBiases = new ArrayList <Double> (inputNeurons);
		hiddenNeuronsBiases = new ArrayList <Double> (hiddenNeurons);
		outputNeuronsBiases = new ArrayList <Double> (outputNeurons);
		hiddenConnections = new ArrayList <ArrayList <Double>> (hiddenNeurons);
		outputConnections = new ArrayList <ArrayList <Double>> (outputNeurons);
		
		
	}
	
	public void parse (){
		//Open file
		InputStream is = this.getClass().getResourceAsStream(path);
		s = new Scanner (is);
		
		//Biases start in line 28.
		for (int i = 0; i < 27; i++)
			s.nextLine();
		
		//Parse input layer biases
		for (int i = 0; i < inputNeurons; i++){			
			inputNeuronsBiases.add(getBiasInLine(s.nextLine()));
			//System.out.println(inputNeuronsBiases.get(i));
		}		
		
		//Parse hidden layer biases
		for (int i = 0; i < hiddenNeurons; i++){
			hiddenNeuronsBiases.add(getBiasInLine(s.nextLine()));
			//System.out.println(hiddenNeuronsBiases.get(i));
		}
		
		//Parse output layer biases

		for (int i = 0; i < outputNeurons; i++){
			outputNeuronsBiases.add(getBiasInLine(s.nextLine()));
			//System.out.println(outputNeuronsBiases.get(i));
		}
		
		//Skip 7 lines
		for (int i = 0; i < 7; i++)
			s.nextLine();
				
		//Parse connections
		for (int i = 0; i < hiddenNeurons; i++){	
			hiddenConnections.add(getWeightsInLine(s.nextLine(), inputNeurons));
		}
		
		for (int i = 0; i < outputNeurons; i++){
			outputConnections.add(getWeightsInLine(s.nextLine(), hiddenNeurons));
		}
	}
	
	private double getBiasInLine (String line){
		//Remove all unnecessary characters.
		line = line.replaceAll("\\|", "");
		
		//Replace commas
		line = line.replaceAll(",", ".");
		
		//Parse the line		
		lineScanner = new Scanner (line);
		lineScanner.useLocale(Locale.US);
		
		//Skip to 4rd token
		for (int j = 0; j < 3; j++)
			lineScanner.next();
		
		//Use 4rd token as a bias
		return lineScanner.nextDouble();		
	}
	
	private ArrayList <Double> getWeightsInLine (String line, int connections){
		line = line.replaceAll("\\|", "");
		line = line.replaceAll(", ", " ");
		line = line.replaceAll(",", ".");
		line = line.replaceAll("-", " -");
		System.out.println(line);
		lineScanner = new Scanner (line);
		lineScanner.useLocale(Locale.US);
		
		//Skip first double
		lineScanner.nextDouble();
		
		ArrayList <Double> currentConnections = new ArrayList <Double> ();
		
		for (int j = 0; j < connections; j++){
			//Skip a non double
			lineScanner.next();
			//System.out.println(lineScanner.nextDouble());
			
			//Insert connection
			currentConnections.add(lineScanner.nextDouble());				
		}
		
		return (currentConnections);		
	}
}