package NeuralNet.Learning;

import NeuralNet.Layer.Layer;
import NeuralNet.NeuralNet;

import NeuralNet.Learning.Errorfunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Backpropagation takes the total error and tries to minimize this error
 * We have a list of pairs <input, output> by which we will calculate the error
 */
public class Backpropagation implements LearnAlgorithm{

    // here we have (almost) all information we need
    private NeuralNet neuralNet = null;
    /**
     * trainingData consists of #row, 0: training Input; 1: expected Output , values
     */
    private double[][][] trainingData = null;


    public Backpropagation() {
    }

    public Backpropagation(NeuralNet n) {
        this.neuralNet = n;
    }

    public void setNeuralNet(NeuralNet neuralNet) {
        this.neuralNet = neuralNet;
    }
    // TODO find a clever method to load this data!
    public void setTrainingData(double[][][] data) {
        this.trainingData = data;
    }

    /**
     * Wrapper for calling initialize with default values
      */
    private void initialize() {
        initialize(-1, 1);
    }

    /**
     * Set the weights of each layer to a random value between min and max
     * @param min
     * The minimal value for initialization
     * @param max
     * The maximal value for initialization
     */
    private void initialize(double min, double max) {
        if (min > max) {
            System.out.println("max is less than min!");
            double tmp = min;
            min = max;
            max = tmp;
        }

        // for earch hidden layer create the array and set weights
        for (int i = 0; i < this.neuralNet.getHiddenLayerCnt(); i++) {
            System.out.println("Processing Layer " + i);
            initializeLayer(min, max, this.neuralNet.getHiddenLayer(i));
        }
        if(this.neuralNet.getHasOutputLayers()) {
            initializeLayer(min, max, this.neuralNet.getOutputLayer());
        }

        // do the same for the output layer
    }

    private void initializeLayer(double min, double max, Layer l) {
        int xMax = l.getInpCnt();
        int yMax = l.getOutpCnt();
        double[][] tmp = new double[xMax][yMax];
        for(int x = 0; x < xMax; x++) {
            for(int y = 0; y < yMax; y++) {
                tmp[x][y] = min + (Math.random() * (max - min));
            }
        }
        l.setWeights(tmp);

    }


    public void learn(boolean init) {
        System.out.println("Started learning");
        // initialize the network with random values
        if(init) {
            initialize();
        }

        // learn round for round by repeating the following steps:
        /*
        1. Feed the network with sampling-data
        2. Compare output with expected result and calculate Total Error
        3. For each Layer l from output to first hiddenLayer do:
            3a. For each Weight w do:
                3a1. Calculate delta = d(TotalError)/d(w)
                3a2. calculate w' = w - learningRate * delta
            3b. Update w with w'
         */
        for(int i = 0; i < this.trainingData.length; i++) {

        // 1. Feed the network with sampling-data
        // TODO implement me
            double[] outp = this.neuralNet.eval(this.trainingData[i][0]);

        // 2. Compare output with expected result
        double ErrorTotal = Errorfunction.calculateTotalError(this.trainingData[i][1], outp);
        System.out.println(ErrorTotal);

        // 3. For each Layer l from output to first hiddenLayer do:
        // create a List of Layers to work with in reversed order
        List<Layer> Layers = new ArrayList<>();
        Layers.add(neuralNet.getOutputLayer());
        for (int j = this.neuralNet.getHiddenLayerCnt()-1; j >= 0; j--) {
            Layers.add(this.neuralNet.getHiddenLayer(j));
        }

        double[] ErrorDOut = new double[0];
        // #Layers = #HiddenLayers + 1 OutputLayer
        // FIXME check if Layers.size is correct here!
        double[][] dOutdnet = new double[Layers.size()][];
        double[][] dNetdW = new double[Layers.size()][];

        for (Layer l: Layers) {
//            3a. For each Weight w do:
            for (int iW = 0; iW < l.getInpCnt(); iW++) {
//                3a1. Calculate delta = d(TotalError)/d(w)

//                3a2. calculate w' = w - learningRate * delta
//                3b. Update w with w'

            }


        }

        // start with the outputLayer
        /*
        for (double a: neuralNet.eval(new double[]{1,1})) {
            System.out.println(a);
        }
         */

        // save/export the values

        }

    }

}
