package NeuralNet;

import NeuralNet.Activationfunction.*;
import NeuralNet.Layer.Layer;
import NeuralNet.Neuron.Perceptron;

import java.io.NotActiveException;
import java.util.ArrayList;
import java.util.List;

class NeuralNet {
    private Layer inputLayer = null;
    private Layer outputLayer = null;
    private List<NeuralNet.Layer.Layer> hiddenLayers;
    private boolean hasInputLayer = false, hasOutputLayer = false;
    private int inputNeuronCnt = 0, OutputNeuronCnt = 0;

    NeuralNet(int inpCnt, int outCnt) {
        System.out.println("Neural net");
        layers = new ArrayList<Layer>();
    }
    void setInputLayer(Neuron neuron, Activationfunction actFunc) {
        if(this.hasInputLayer) {
            layers.set(0, new Layer(Layer.LayerTyp.inputLayer, new Perceptron(), this.inputNeuronCnt, new ActivationfunctionTanh()));
            System.out.println("InputLayer replaced");
        } else {
            inputLayer = new Layer(Layer.LayerTyp.inputLayer, new Perceptron(), this.inputNeuronCnt, new ActivationfunctionTanh());
            System.out.println("InputLayer created");
        }
        this.hasInputLayer = true;
    }

    void unsetInputLayer() {
        this.inputLayer = null;
        this.hasInputLayer = false;
    }

    void setOutputLayer() {
    }
    void unsetOutputLayer() {
        this.outputLayer = null;
        this.hasOutputLayer = false;
    }
    void addHiddenLayer() {
        if(layerTyp == Layer.LayerTyp.inputLayer) {
            // The inputlayer will always be at index = 0
            // if an input Layer already exists
        }
        if(layerTyp == Layer.LayerTyp.hiddenLayer) {
            if(this.hasOutputLayer) {
                this.layers.add(this.layers.size() - 1, new Layer());
                System.out.println("HiddenLayer created in between");
            } else {
                layers.add(new Layer());
                System.out.println("HiddenLayer created");
            }
        }
        if(layerTyp == Layer.LayerTyp.outputLayer) {
            if(!this.hasOutputLayer) {
                this.layers.add(new Layer());
                System.out.println("OutputLayer created");
            } else {
                this.layers.set(this.layers.size()-1, new Layer());
                System.out.println("OutputLayer replaced");
            }
            this.hasOutputLayer = true;
        }

    }

    public double[] run() {
        double[] tmp;
        return  tmp = new double[] {1.0};
    }


}
