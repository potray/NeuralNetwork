/**
 * 
 */
package network;

/**
 * @author Daniel
 *
 */
public class NeuronWithoutConnections {
	private double input;
	private double bias;
	
	public NeuronWithoutConnections (double bias){
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
