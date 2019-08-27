package NeuralNet;

import java.util.List;

/*
A Neuron has a List of Inputs, which are connected to the outputs of
other neurons by weights [-1, 1](?)

Once every neuron on the input side has fired, the neuron itself starts
to fire to the next layer, depending on the given values
 */
public class Neuron {
    private List<Neuron> inputNeurons;
    public Neuron() {
        System.out.println("Empty constructor");
    }
    public  Neuron(int a) {
        System.out.println("Constructor with int");
    }

    public void fire(){

    }
}

