package NeuralNet.Neuron;

// TODO If you add a new kind of Neuron, you have to modify this file, too!
public class NeuronFactory {
    public static <T extends Neuron> Neuron createNeuron(Class<T> t) {
        if (t == Perceptron.class) {
            return new Perceptron();
        } else {
            return null;
        }
    }
}
