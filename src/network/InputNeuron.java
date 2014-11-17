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
	
	public InputNeuron (double bias){
		this.bias = bias;
	}

	/**
	 * @return input * bias.
	 */
	public double getValue() {
		return input * bias;
	}

	/**
	 * @param input the input to set.
	 */
	public void setInput(double input) {
		this.input = input;
	}	
}
