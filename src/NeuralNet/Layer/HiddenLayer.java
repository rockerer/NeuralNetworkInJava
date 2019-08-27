package NeuralNet.Layer;

import NeuralNet.Activationfunction.Activationfunction;
import NeuralNet.Neuron.Neuron;

public class HiddenLayer extends Layer{
    HiddenLayer(LayerTyp layerTyp, Neuron neuron, int neuronCnt, Activationfunction actFunc) {
        super(layerTyp, neuron, neuronCnt, actFunc);
    }
}
