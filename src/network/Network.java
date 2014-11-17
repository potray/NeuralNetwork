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
	}

}
