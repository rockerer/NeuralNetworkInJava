package NeuralNet;

import NeuralNet.Activationfunction.*;
import NeuralNet.Layer.HiddenLayer;
import NeuralNet.Layer.InputLayer;
import NeuralNet.Layer.*;
import NeuralNet.Layer.OutputLayer;
import NeuralNet.Neuron.Neuron;
import NeuralNet.Neuron.Perceptron;

import java.io.NotActiveException;
import java.util.ArrayList;
import java.util.List;

class NeuralNet {
    private Layer inputLayer = null;
    private Layer outputLayer = null;
    private List<Layer> hiddenLayers;
    private boolean hasInputLayer = false, hasOutputLayer = false;
    private int inputNeuronCnt = 0, outputNeuronCnt = 0;

//    NeuralNet() {
//        System.out.println("Neural Net with 0 input and 0 Outputs created.");
//        // automatic type deduction
//        this.hiddenLayers = new ArrayList<>();
//    }

    NeuralNet(int inpCnt, int outCnt) {
        // automatic type deduction
        hiddenLayers = new ArrayList<Layer>();
        this.inputNeuronCnt = inpCnt;
        this.outputNeuronCnt = outCnt;
    }

    public int getInputNeuronCnt() {
        return this.inputNeuronCnt;
    }

    public int getOutputNeuronCnt() {
        return this.outputNeuronCnt;
    }

    <T extends Neuron> void setInputLayer(Class<T> neuron, Activationfunction actFunc) {
        if(this.hasInputLayer) {
            this.inputLayer = new InputLayer(neuron,  actFunc, this.inputNeuronCnt);
        } else {
            this.inputLayer = new InputLayer(neuron,  actFunc, this.inputNeuronCnt);
        }
        this.hasInputLayer = true;
    }

    <T extends Neuron> void setOutputLayer(Class<T> neuron, Activationfunction actFunc) {
        if(this.hasOutputLayer) {
            this.outputLayer = new OutputLayer(neuron, actFunc, this.outputNeuronCnt);
        } else {
            this.outputLayer = new OutputLayer(neuron, actFunc, this.outputNeuronCnt);
        }
        this.hasOutputLayer = true;
    }

    void unsetInputLayer() {
        this.inputLayer = null;
        this.hasInputLayer = false;
    }

    void unsetOutputLayer() {
        this.outputLayer = null;
        this.hasOutputLayer = false;
    }

    <T extends Neuron> void addHiddenLayer(Class<T> neuron, Activationfunction actFunc, int neuronCnt, boolean bias) {
        // TODO finish me
        int cntLastLayer;
        if (this.hiddenLayers.size() == 0) {
            cntLastLayer = this.getInputNeuronCnt();
        } else {
            cntLastLayer = this.hiddenLayers.get(this.hiddenLayers.size() - 1).getOutpCnt();
        }
        this.hiddenLayers.add(new HiddenLayer(neuron, actFunc, cntLastLayer, neuronCnt, bias));
        this.outputLayer.setInpCnt(neuronCnt);
    }

    // TODO check, if it returns a copy or a reference
    public Layer getHiddenLayer(int index) {
        if (index >= this.hiddenLayers.size()) {
            return null;
        }
        return this.hiddenLayers.get(index);
    }

    public void setWeightsHidden(int layer, int startNode, int stopNode, double w) {
    }
    public void setWeightsOutp(int startNode, int stopNode, double w) {
        this.outputLayer.setWeight(startNode, stopNode, w);
    }

    double[] eval(double[] inp) {
        // prepare result
        double[] res = new double[this.outputNeuronCnt];
        this.inputLayer.eval(inp);
        for(int i = 0; i<this.hiddenLayers.size(); i++) {
            this.hiddenLayers.get(i).eval((i == 0?this.inputLayer.getOutp() : this.hiddenLayers.get(i-1).getOutp()));
        }
        this.outputLayer.eval(this.hiddenLayers.get(this.hiddenLayers.size()-1).getOutp());
        res = this.outputLayer.getOutp();
        return  res;
    }

    public void printInfo() {
        System.out.println(
                "InputLayer: " + (this.hasInputLayer ? "yes: " + this.inputNeuronCnt : "no") + ";\n" +
                        this.inputLayer.getInpCnt() + "\n" +
                        "HiddenLayers: " + this.hiddenLayers.size());
                        this.hiddenLayers.forEach((Layer l) -> {System.out.println(l.getInpCnt() + "->" + l.getOutpCnt());});
                                System.out.println(
                        "OutputLayer: " + (this.hasOutputLayer ? "yes: " + this.outputNeuronCnt : "no") + "\n" +
                                this.outputLayer.getInpCnt() + "->" + this.outputLayer.getOutpCnt());
    }

    /**
     * @param min minimal weight
     * @param max maximal weight
     * This function is used to initialize all weights with random weights and works as followed:
     * for each row r do:
     *    for each weight w in r do:
     *       w = rand(min, max)
     *
     */
    public void setRandomWeights(double min, double max) {
        for (Layer a : this.hiddenLayers) {
            // TODO finish this implementation
        }

    }

}
