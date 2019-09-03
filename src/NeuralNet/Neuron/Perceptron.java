package NeuralNet.Neuron;

/*
 */

/**
 * This class implements a simple Neuron, based on the function
 * of a Perceptron
 * @author Florian Aul
 */
public class Perceptron extends Neuron{
    /**
     * Voll die tolle Funktion!
     * @author Florian Aul
     */
    public Perceptron() {
    }

    /**
    * This function evaluates the Input and does stuff...
     *
     * @param inp The input which will be put into the function
     */
    @Override
    public void eval(double inp) {
        this.outp = inp;
    }
}
