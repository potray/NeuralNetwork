package network;

import java.util.ArrayList;

public class OutputNeuron {
	private ArrayList<HiddenNeuron> inputNeurons;
	private ArrayList<Double> weights;
	private double bias;
	
	/**
	 * 
	 * @param connections number of neurons connected to this one in the previous layer.
	 */
	public OutputNeuron (int connections, double bias){
		inputNeurons = new ArrayList <HiddenNeuron> (connections);
		weights = new ArrayList <Double> (connections);
		this.bias = bias;
	}
	
	public void addConnection(HiddenNeuron neuron, double connectionWeight){
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
