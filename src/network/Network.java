package network;
import java.util.ArrayList;


public class Network {
	
	private ArrayList <InputNeuron> inputLayer;
	private ArrayList <HiddenNeuron> hiddenLayer;
	private ArrayList <OutputNeuron> outputLayer;

	public static void main(String[] args) {
		//Parse file
		NetParser p = new NetParser ("../Red 8-3-1.net", 8, 3, 1);
		
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
				neuron.addConnection(N.inputLayer.get(i), p.hiddenConnections.get(i).get(j));
			}
			
			N.hiddenLayer.add(neuron);
		}
		
		//Create output layer
		N.outputLayer = new ArrayList <OutputNeuron> (p.outputNeurons);
		
		for (int i = 0; i < p.outputNeurons; i++){
			OutputNeuron neuron = new OutputNeuron(p.hiddenNeurons, p.outputNeuronsBiases.get(i));
			
			//Create connections
			for (int j = 0; j < p.hiddenNeurons; j++){
				neuron.addConnection(N.hiddenLayer.get(i), p.outputConnections.get(i).get(j));
			}
			
			N.outputLayer.add(neuron);
		}
		
		//Test net
		
		double sum = 0;
		
		double [] test1 = {1, 0.455, 0.365, 0.095, 0.514, 0.2245, 0.101, 0.15};
		double [] test2 = {1, 0.35, 0.265, 0.09, 0.2255, 0.0995, 0.0485, 0.07};
		double [] test3 = {0, 0.53, 0.42, 0.135, 0.677, 0.2565, 0.1415, 0.21};
		double [] test4 = {1, 0.44, 0.365, 0.125, 0.516, 0.2155, 0.114, 0.155};
		double [] test5 = {0.5, 0.33, 0.255, 0.08, 0.205, 0.0895, 0.0395, 0.055};
		
		double [] corrects = {15, 7, 9, 10, 7};

		double [][] tests = {test1, test2, test3, test4, test5};
		for (int i = 0; i < 5; i++){	
			for (int j = 0; j < N.inputLayer.size(); j++){
				N.inputLayer.get(j).setInput(tests[i][j]);
			}
			double output = N.outputLayer.get(0).getValue(); 
			double error = output - corrects[i];
			double errorPow = Math.pow(error, 2);
			sum += errorPow;
			System.out.println("Iteración " + i + ", salida = " + output + " error = " + error + " error cuadrático = " + errorPow);
		}
		
		System.out.println(sum);
	}

}
