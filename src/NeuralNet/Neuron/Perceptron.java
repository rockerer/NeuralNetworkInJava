package NeuralNet.Neuron;

/*
 */

import NeuralNet.Activationfunction.Activationfunction;

/**
 * This class implements a simple Neuron, based on the function
 * of a Perceptron
 * @author Florian Aul
 */
public class Perceptron extends Neuron{
    // TODO check if you can handle the activationFunction as an generic parameter, too
    /**
     * Voll die tolle Funktion!
     * @author Florian Aul
     * @param actFunc An Instance of an Activation Function
     */
    public Perceptron(Activationfunction actFunc) {
        this.actFunc = actFunc;
    }
    /**
    * This function evaluates the Input and does stuff...
     *
     * @param inp The input which will be put into the function
     */
    @Override
    public double eval(double inp) {
        return actFunc.activate(inp);
    }
}
