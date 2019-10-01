package NeuralNet.Neuron;

import NeuralNet.Activationfunction.Activationfunction;

/*
A Neuron has a List of Inputs, which are connected to the outputs of
other neurons by weights [-1, 1](?)

Once every neuron on the input side has fired, the neuron itself starts
to fire to the next layer, depending on the given values
 */
public abstract class Neuron {
    protected double inp = 0;
    private double outp = 0;
    protected Activationfunction actFunc = null;


    public abstract double eval(double inp);
    public double getOutp() {
        return this.outp;
    }
}

