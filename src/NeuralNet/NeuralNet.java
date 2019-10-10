package NeuralNet;

import NeuralNet.Activationfunction.*;
import NeuralNet.Layer.*;
import NeuralNet.Neuron.Neuron;

import java.util.ArrayList;
import java.util.List;

/**
 * This neural network consists of Layers, each having a number of Neurons, set at instanciation
 */
public class NeuralNet {
    private Layer inputLayer = null;
    private Layer outputLayer = null;
    private List<Layer> hiddenLayers = null;
    private boolean hasInputLayer = false, hasHiddenLayer = false, hasOutputLayer = false;
    private int inputNeuronCnt = 0, outputNeuronCnt = 0;

    NeuralNet() {
        System.out.println("Neural Net with 0 input and 0 Outputs created.");
        // automatic type deduction
        this.hiddenLayers = new ArrayList<>();
    }

    /*
    NeuralNet(int inpCnt, int outCnt) {
        // automatic type deduction
        hiddenLayers = new ArrayList<>();
        this.inputNeuronCnt = inpCnt;
        this.outputNeuronCnt = outCnt;
    }
     */

    private int getInputNeuronCnt() {
        return this.inputNeuronCnt;
    }

    public int getOutputNeuronCnt() {
        return this.outputNeuronCnt;
    }

    /**
     *
     * @param InputNeuronCnt
     * The number of input neurons. This determines, how many weights are generated for
     * the input of the following hidden layer
     */
    void addInputLayer(int InputNeuronCnt) {
        this.inputNeuronCnt = InputNeuronCnt;
        if(this.hasInputLayer) {
            System.out.println("Replacing existing InputLayer!");
            this.inputLayer = new InputLayer(this.inputNeuronCnt);
        } else {
            System.out.println("Creating InputLayer!");
            this.inputLayer = new InputLayer(this.inputNeuronCnt);
        }
        this.hasInputLayer = true;
    }

    void removeInputLayer() {
        this.inputLayer = null;
        this.hasInputLayer = false;
        this.inputNeuronCnt = 0;
    }

    public boolean getHasInputLayer() {
        return this.hasInputLayer;
    }

    void addOutputLayer(Activationfunction actFunc, int OutpNeuronCnt) {
        // get the inputCnt for the last layer, either a hiddenLayer or the inputLayer
        int inpCnt =
                (this.hasHiddenLayer
                ? this.hiddenLayers.get(this.hiddenLayers.size()-1).getOutpCnt()
                : this.inputNeuronCnt);
        this.outputNeuronCnt = OutpNeuronCnt;
        if(this.hasOutputLayer) {
            System.out.println("Replacing existing OutputLayer!");
            this.outputLayer = new OutputLayer(actFunc, inpCnt, this.outputNeuronCnt);
        } else {
            System.out.println("Adding OutputLayer!");
            this.outputLayer = new OutputLayer(actFunc, inpCnt, this.outputNeuronCnt);
        }
        this.hasOutputLayer = true;
        System.out.println("OutputLayer: Inp: " + inpCnt + "; Out: " + this.outputNeuronCnt);
    }

    public boolean getHasOutputLayers() {
        return this.hasOutputLayer;
    }

    public Layer getOutputLayer() {
        return this.outputLayer;
    }

    void removeOutputLayer() {
        this.outputLayer = null;
        this.hasOutputLayer = false;
        this.outputNeuronCnt = 0;
    }

    void addHiddenLayer( Activationfunction actFunc, int neuronCnt) {
        // TODO finish me
        int cntLastLayer;
        // get the last layer to which the new layer will be appended
        if (this.hiddenLayers.size() == 0) {
            cntLastLayer = this.getInputNeuronCnt();
        } else {
            cntLastLayer = this.hiddenLayers.get(this.hiddenLayers.size() - 1).getOutpCnt();
        }
        this.hiddenLayers.add(new HiddenLayer(actFunc, cntLastLayer, neuronCnt));

        /*
        if (this.hasOutputLayer) {
            // FIXME did i check everything here? I don't think so
            this.outputLayer.setInpCnt(neuronCnt);
        }
         */
        this.hasHiddenLayer = true;
        System.out.println("HiddenLayer created. Inp: " + cntLastLayer + "; Outp: " + neuronCnt);
    }

    public int getHiddenLayerCnt() {
        return this.hiddenLayers.size();
    }

    // TODO check, if it returns a copy or a reference
    public Layer getHiddenLayer(int index) {
        if (index >= this.hiddenLayers.size()) {
            return null;
        }
        return this.hiddenLayers.get(index);
    }

    public void removeHiddenLayer(int index) {
        // TODO implement me
    }

    public void setWeightsHidden(int layer, int startNode, int stopNode, double w) {
        // TODO implement me
    }
    void setWeightOutp(int startNode, int stopNode, double w) {
        this.outputLayer.setWeight(startNode, stopNode, w);
    }

    // does this work?
    void setWeightsOutp(double[][] w) {
        this.outputLayer.setWeights(w);
    }

    public double[] eval(double[] inp) {
        // prepare result
        double[] res = new double[this.outputNeuronCnt];

        this.inputLayer.setNet(inp);
        this.inputLayer.eval(new double[]{});

        // check if hidden layers do exist
        if (this.hasHiddenLayer) {
            for (int i = 0; i < this.hiddenLayers.size(); i++) {
                   this.hiddenLayers.get(i).eval(i == 0 ? this.inputLayer.getOutp() : this.hiddenLayers.get(i).getOutp());
            }
            this.outputLayer.eval(this.hiddenLayers.get(this.hiddenLayers.size()-1).getOutp());
        } else {
            this.outputLayer.eval(this.inputLayer.getOutp());
        }
        res = this.outputLayer.getOutp();
        return  res;
    }

    void printInfo() {
        System.out.println(
                "InputLayer: " + (this.hasInputLayer ? "yes: " + this.inputNeuronCnt : "no") + ";\n" +
                        this.inputLayer.getInpCnt() + "\n" +
                        "HiddenLayers: " + this.hiddenLayers.size());
                        this.hiddenLayers.forEach((Layer l) -> System.out.println(l.getInpCnt() + "->" + l.getOutpCnt()));
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
            int b = 1;
            // TODO finish this implementation
        }
    }

    public void readWeightsFromFile(String filename) {
        //TODO Implement me

    }

}
