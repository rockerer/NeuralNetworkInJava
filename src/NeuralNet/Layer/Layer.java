package NeuralNet.Layer;

import NeuralNet.Activationfunction.Activationfunction;
import NeuralNet.Neuron.*;
import java.util.List;

public class Layer {
    private List<Neuron> Neuronen;
    private double[][] weights;

    public <T extends Neuron> Layer(LayerTyp layerTyp, Class<T> neuron, int neuronCnt, Activationfunction actFunc) {


    }


    public enum LayerTyp {
        // Input Layer is the first layer, do we don't have any incoming nodes
        // There can be just one inputlayer per neural net
        inputLayer,
        // Layer between input- and output-Layer. We have no limit on them
        hiddenLayer,
        // Output layer is the last layer in the neural net
        outputLayer,
    }

}
