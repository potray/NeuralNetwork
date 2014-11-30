package network;

import java.util.ArrayList;

public class OutputNeuron {
	private ArrayList<HiddenNeuron> inputNeurons;
	private ArrayList<Double> weights;
	private double bias;

	/**
	 * @param connections number of neurons connected to this one in the previous layer.
	 * @param bias the bias of the neuron.
	 */
	public OutputNeuron (int connections, double bias){
		inputNeurons = new ArrayList <HiddenNeuron> (connections);
		weights = new ArrayList <Double> (connections);
		this.bias = bias;
	}
	
	/**
	 * Adds a link to this neuron's input.
	 * @param neuron the neuron that has a link to this neuron.
	 * @param connectionWeight the weight of the link.
	 */
	public void addConnection(HiddenNeuron neuron, double connectionWeight){
		inputNeurons.add(neuron);
		weights.add(connectionWeight);
	}
	
	/**
	 * Gets a sum of all the outputs from the hidden layer multiplied by the links' weights.
	 * @return the output of this neuron.
	 */
	public double getValue(){
		//Sum all inputs multiplied by their weights
		double sum = 0;
		
		for (int i = 0; i < inputNeurons.size(); i++){
			sum += inputNeurons.get(i).getValue() * weights.get(i);
		}
		
		//JavaNNS doesn't use bias
		//sum += bias;
		
		//Return
		return sum;
	}

	/**
	 * @param bias the bias to set
	 */
	public void setBias(double bias) {
		this.bias = bias;
	}
	
	/**
	 * @return the weights
	 */
	public ArrayList<Double> getWeights() {
		return weights;
	}
	
	
	/**
	 * @return the bias
	 */
	public double getBias() {
		return bias;
	}
}
