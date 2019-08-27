package NeuralNet;

import NeuralNet.Activationfunction.*;

import java.util.ArrayList;
import java.util.List;

class NeuralNet {
    private List<Layer> layers;
    private boolean hasInputLayer = false, hasOutputLayer = false;
    NeuralNet() {
        System.out.println("Neural net");
        layers = new ArrayList<Layer>();
    }
    void addLayer(Layer.LayerTyp layerTyp, int neuronCnt, Activationfunction actFunc) {
        if(layerTyp == Layer.LayerTyp.inputLayer) {
            System.out.println("InputLayer created");
            // The inputlayer will always be at index = 0
            // if an input Layer already exists
            if(this.hasInputLayer) {
                layers.set(0, new Layer());
            } else {
                layers.add(0, new Layer());
            }
            this.hasInputLayer = true;
        }
        if(layerTyp == Layer.LayerTyp.hiddenLayer) {
            System.out.println("HiddenLayer created");
        }
        if(layerTyp == Layer.LayerTyp.outputLayer) {
            System.out.println("OutputLayer created");
            if(!this.hasOutputLayer) {
                this.layers.add(new Layer());
            } else {
                this.layers.remove(this.layers.) = null;
            }
            this.hasOutputLayer = true;
        }

    }


}
