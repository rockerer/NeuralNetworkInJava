package NeuralNet;

import java.util.List;

class Layer {
    private List<Neuron> Neuronen;
    private double[][] weights;

    Layer() {

    }


    public enum LayerTyp {
        // Input Layer is the first layer, do we don't have any incoming nodes
        // There can be just one inputlayer per neural net
        inputLayer,
        // Layer between input- and output-Layer. We have no limit on them
        hiddenLayer,
        // Output layer is the last layer in the neural net
        outputLayer;
    }

}
