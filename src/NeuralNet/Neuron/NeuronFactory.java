package NeuralNet.Neuron;

import NeuralNet.Activationfunction.Activationfunction;

// TODO If you add a new kind of Neuron, you have to modify this file, too!
public class NeuronFactory {
    public static <T extends Neuron> Neuron createNeuron(Class<T> t, Activationfunction actFun) {
        if (t == Perceptron.class) {
            return new Perceptron(actFun);
        } else {
            return null;
        }
    }
}
