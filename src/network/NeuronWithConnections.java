package network;

import java.util.ArrayList;

public class NeuronWithConnections {
	private ArrayList<NeuronWithoutConnections> inputNeurons;
	private ArrayList<Double> weights;
	private double bias;
	
	/**
	 * 
	 * @param connections number of neurons connected to this one in the previous layer.
	 */
	public NeuronWithConnections (int connections, double bias){
		inputNeurons = new ArrayList <NeuronWithoutConnections> (connections);
		weights = new ArrayList <Double> (connections);
		this.bias = bias;
	}
	
	public void addConnection(NeuronWithoutConnections neuron, double connectionWeight){
		inputNeurons.add(neuron);
		weights.add(connectionWeight);
	}
	
	public double getValue(){
		//Sum all inputs multiplied by their weights
		double sum = 0;
		
		for (int i = 0; i < inputNeurons.size(); i++){
			sum += inputNeurons.get(i).getValue() * weights.get(i);
		}
		
		//Add bias
		sum += bias;
		
		//Apply sigmoid function
		sum = 1/(1 + Math.exp(-sum));
		
		//Return
		return sum;
	}

	/**
	 * @param bias the bias to set
	 */
	public void setBias(double bias) {
		this.bias = bias;
	}
}
