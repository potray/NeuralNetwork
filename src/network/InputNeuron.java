/**
 * 
 */
package network;

/**
 * @author Daniel
 *
 */
public class InputNeuron {
	private double input;
	private double bias;

	/**
	 * Contructor
	 * @param bias the bias of the neuron.
	 */
	public InputNeuron (double bias){
		this.bias = bias;
	}

	/**
	 * @return input * bias.
	 */
	public double getValue() {
		return input /*+ bias*/;
	}

	/**
	 * @param input the input to set.
	 */
	public void setInput(double input) {
		this.input = input;
	}
	
	/**
	 * @return the bias
	 */
	public double getBias() {
		return bias;
	}

	/**
	 * @param bias the bias to set
	 */
	public void setBias(double bias) {
		this.bias = bias;
	}
}
