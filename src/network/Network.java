package network;
import java.util.ArrayList;


public class Network {
	
	private ArrayList <InputNeuron> inputLayer;
	private ArrayList <HiddenNeuron> hiddenLayer;
	private ArrayList <OutputNeuron> outputLayer;

	public static void main(String[] args) {
		
		String netPath = null;
		String patPath = null;
		int inputs = 0;
		int hiddens = 0;
		int outputs = 0;
		double max = 0;
		double min = 0;
		boolean isDiscretized = false;
		
		//Check arguments
		if (args.length > 1){
			//Paths provided
			netPath = args[0];
			patPath = args[1];
			
			if (args.length > 4){
				//Layer size provided
				inputs = Integer.valueOf(args[2]);
				hiddens = Integer.valueOf(args[3]);
				outputs = Integer.valueOf(args[4]);
				
				if (args.length == 8){
					//Max, min and discretized true/false provided
					max = Double.valueOf(args[5]);
					min = Double.valueOf(args[6]);
					isDiscretized = Boolean.valueOf(args[7]);
				}
			}
		}else{
			System.out.println("Arg error, usage: Network.jar netPath patPath [inputs hidden outputs [max min isDiscretized]");
			System.exit(0);
		}
		
		
		double classificationErrors = 0;
		double approximationError = 0; 
		//Parse file
		NetParser p = new NetParser (netPath, inputs, hiddens, outputs);
		
		p.parse();
		
		//Create network
		Network N = new Network();
		
		//Create input layer
		N.inputLayer = new ArrayList <InputNeuron> (p.inputNeurons);
		
		for (int i = 0; i < p.inputNeurons; i++){
			InputNeuron neuron = new InputNeuron(p.inputNeuronsBiases.get(i));
			N.inputLayer.add(neuron);
		}
		
		//Create hidden layer
		N.hiddenLayer = new ArrayList <HiddenNeuron> (p.hiddenNeurons);
		
		for (int i = 0; i < p.hiddenNeurons; i++){
			HiddenNeuron neuron = new HiddenNeuron(p.inputNeurons, p.hiddenNeuronsBiases.get(i));
			
			//Create connections
			for (int j = 0; j < p.inputNeurons; j++){
				neuron.addConnection(N.inputLayer.get(j), p.hiddenConnections.get(i).get(j));
				//System.out.println("Creando enlace desde " + j + "hasta " + i + " con valor" +  p.hiddenConnections.get(i).get(j));
			}
			
			N.hiddenLayer.add(neuron);
		}
		
		//Create output layer
		N.outputLayer = new ArrayList <OutputNeuron> (p.outputNeurons);
		
		for (int i = 0; i < p.outputNeurons; i++){
			OutputNeuron neuron = new OutputNeuron(p.hiddenNeurons, p.outputNeuronsBiases.get(i));
			
			//Create connections
			for (int j = 0; j < p.hiddenNeurons; j++){
				neuron.addConnection(N.hiddenLayer.get(j), p.outputConnections.get(i).get(j));
				//System.out.println("Creando enlace desde " + j + "hasta " + i + " con valor" +  p.outputConnections.get(i).get(j));
			}
			
			N.outputLayer.add(neuron);
		}
		
		N.printNet();
		
		//Parse pat file
		PatParser pp = new PatParser (patPath, inputs, outputs); 
		for (int i = 0; i < pp.getLines(); i++){
			//Get the next line
			ArrayList<Double> nextLine = pp.getNext();
			
			//Asign inputs
			for (int j = 0; j < inputs; j++){
				N.inputLayer.get(j).setInput(nextLine.get(j));
			}
			
			//Get outputs
			
			//If we only have 1 output do less stuff
			if (outputs == 1){
				double result = N.outputLayer.get(0).getValue();
				
				//if the result is different than the last value in nextLine, is an error
				double exampleOutput = nextLine.get(inputs); 
				//System.out.println("Result = " + result + " example output = " + exampleOutput);
				if (result != exampleOutput){
					if (isDiscretized){
						result = Math.round(result * (max - min) + min);
						exampleOutput = Math.round(exampleOutput * (max - min) + min);
					}else{
						result = Math.round(result);
						exampleOutput = Math.round(exampleOutput);
					}
					//If the discretized values are different it's a classification error.
					//System.out.println("Result = " + result + " example output = " + exampleOutput);
					if (result != exampleOutput)
						classificationErrors ++;
					
					approximationError += Math.abs(result - exampleOutput);
				}
			}else{
				//TODO to be implemented
			}
		}
		//Print results
		System.out.println("Classification error = " + (classificationErrors)/pp.getLines() * 100 + "%, approximation error = " + approximationError/pp.getLines());
	}
	
	private void printNet(){
		//Biases
		System.out.println("Input biases:");
		String inputBiases = "";
		for (int i = 0; i < inputLayer.size(); i++){
			inputBiases += String.valueOf(inputLayer.get(i).getBias()) + ", ";
		}
		System.out.println(inputBiases);
		
		System.out.println("Hidden biases:");
		String hiddenBiases = "";
		for (int i = 0; i < hiddenLayer.size(); i++){
			hiddenBiases += String.valueOf(hiddenLayer.get(i).getBias()) + ", ";
		}
		System.out.println(hiddenBiases);
		
		System.out.println("Output biases:");
		String outputBiases = "";
		for (int i = 0; i < outputLayer.size(); i++){
			outputBiases += String.valueOf(outputLayer.get(i).getBias()) + ", ";
		}
		System.out.println(outputBiases);
		
		System.out.println("============================================");
		//Weights
		for (int i = 0; i < hiddenLayer.size(); i++){
			System.out.println("Hidden neuron " + i + " weights:");
			String weights = "";
			for (int j = 0; j < inputLayer.size(); j++){
				weights += hiddenLayer.get(i).getWeights().get(j) + ", ";
			}
			
			System.out.println(weights);
		}
		
		for (int i = 0; i < outputLayer.size(); i++){
			System.out.println("Output neuron " + i + " weights:");
			String weights = "";
			for (int j = 0; j < hiddenLayer.size(); j++){
				weights += outputLayer.get(i).getWeights().get(j) + ", ";
			}
			
			System.out.println(weights);
		}
		
	}

}
