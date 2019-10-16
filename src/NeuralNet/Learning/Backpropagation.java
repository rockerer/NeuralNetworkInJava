package NeuralNet.Learning;

import NeuralNet.Layer.Layer;
import NeuralNet.NeuralNet;

import NeuralNet.Error;

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
     * trainingData consists of<br/>
     * #row <br/>
     *  0: training Input<br/>
     *  1: expected Output<br>
     * values
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
        if (this.neuralNet == null) {
            System.err.println(Error.NO_NEURAL_NET_GIVEN);
            System.exit(Error.NO_NEURAL_NET_GIVEN.ordinal());
        }
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
            System.out.println("Evaluating the " + i + ".th round");
//            double[] outp = this.neuralNet.eval(new double[] {1,1});
            double[] outp = this.neuralNet.eval(this.trainingData[i][0]);
                    //this.neuralNet.eval(new double[] {1,2});
            double[] target = this.trainingData[i][1];
            System.out.println("Evaluation finished");

            // 2. Compare output with expected result
            double[] Error = Errorfunction.calculateError(this.trainingData[i][1], outp);
            double ErrorTotal = Errorfunction.calculateTotalError(Error);
//        double ErrorTotal = Errorfunction.calculateTotalError(this.trainingData[i][1], outp);
            System.out.println("Total Error: " + ErrorTotal);

            // 3. For each Layer l from output to first hiddenLayer do:
            // create a List of Layers to work with in reversed order
            List<Layer> Layers = new ArrayList<>();
            Layers.add(neuralNet.getOutputLayer());
            for (int j = this.neuralNet.getHiddenLayerCnt()-1; j >= 0; j--) {
                Layers.add(this.neuralNet.getHiddenLayer(j));
            }

            // #Layers = #HiddenLayers + 1 OutputLayer
            // FIXME check if Layers.size is correct here!
            int layerCnt = Layers.size();

            // Errors
            double[][][] dError_dOut = new double[layerCnt][][];
            double[][][] dError_dNet = new double[layerCnt][][];
            double[][] dErrorSum_dOut = new double[layerCnt][];
            // TODO calculate this value
            double[][] dError_dW = new double[layerCnt][];

            // Net -> Something
            double[][][] dNet_dW = new double[layerCnt][][];
            double[][][] dNet_dOut = new double[layerCnt][][];

            // Net -> next Net
            double[][] dOut_dNet = new double[layerCnt][];

            double[][] wUpdated = new double[layerCnt][];

            for (Layer l: Layers) {
                int actLayer = Layers.indexOf(l);
                dOut_dNet[actLayer] = new double[l.getInpCnt()];
                dNet_dW[actLayer] = new double[l.getInpCnt()][];
                dNet_dOut[actLayer] = new double[l.getInpCnt()][];

                dError_dNet[actLayer] = new double[l.getOutpCnt()][];
                dError_dOut[actLayer] = new double[l.getOutpCnt()][];

//            3a. For each Node n do:
                for(int n = 0; n < l.getOutpCnt(); n++ ) {
                    // Initialize dNet_dW for node n
                    dNet_dW[actLayer][n] = new double[l.getInpCnt()];
                    dNet_dOut[actLayer][n] = new double[l.getInpCnt()];
                    dOut_dNet[actLayer][n] = l.getActFunc().derivative(l.getOutp()[n]);
                    dError_dOut[actLayer][n] = new double[outp.length];
                    dError_dNet[actLayer][n] = new double[outp.length];
                    dErrorSum_dOut[actLayer] = new double[outp.length];


//                  3a1. For each Weight w pointing to no n do:
                    for (int iW = 0; iW < l.getInpCnt(); iW++) {

                        // Set dNet_dW
                        // Bias
                        if (iW == l.getInpCnt() - 1) {
                            dNet_dW[actLayer][n][iW] = 1;
                        } else {
                            // Non-Bias
                            if (layerCnt == Layers.size()) {
                                dNet_dW[actLayer][n][iW] = neuralNet.getInputLayer().getOutp()[iW];
                            } else {
                                dNet_dW[actLayer][n][iW] = Layers.get(layerCnt + 1).getOutp()[iW];
                            }
                        }

                        // set dNet_dOut
                        // Bias has no effect here
                        dNet_dOut[actLayer][n][iW] = l.getWeight(iW, n);

                        // calculate dOut_dNet
                        dOut_dNet[actLayer][n] = l.getOutp()[n] * (1 - l.getOutp()[n]);


                        // calculate dError_dOut
                        for(int x = 0; x < outp.length; x++) {
                            // if output-layer
                            if (actLayer == 0) {
                                dError_dOut[actLayer][n][x] = outp[x] - target[x];
                            } else {
                                double tmp = 0.0d;
                                for(int actNeuron = 0; actNeuron < l.getOutpCnt(); actNeuron++) {
                                    tmp += dError_dNet[actLayer - 1][actNeuron][x] * dNet_dOut[actLayer-1][n][actNeuron];
                                }
                                dError_dOut[actLayer][n][x] = tmp;
                            }
                            // calculate dError_dNet
                            dError_dNet[actLayer][n][x] = dError_dOut[actLayer][n][x] * dOut_dNet[actLayer][n];
                        }


//                Take care of the bias
//                3a1. Calculate delta = d(TotalError)/d(w)
//                calculate dOut_dNet[actLayer][iW]
//                    dNet_dW[actLayer][0] = Layers.get(actLayer + 1).getOutp()[iW];
//                    dOut_dNet[actLayer][iW] = l.getActFunc().derivative(l.getOutp()[iW]);
//                dOut_dNet[actLayer][iW] = l.getActFunc().derivative(l.getNet()[iW]);

//                dError_dOut[actLayer][iW] = summe von dingen
                    /*
                    dError_dOut[actLayer][iW] =
                            ( actLayer == layerCnt ?
                                    (outp[iW] - this.trainingData[i][1][iW]) :
//                                    TODO calculate this value
                                    -1
                                    );
                     */




                    }
                    dErrorSum_dOut[actLayer][n] = 0;
                    for(double x : dError_dOut[actLayer][n]) {
                        dErrorSum_dOut[actLayer][n] += x;
                    }
                }
//                3a2. calculate w' = w - learningRate * delta
//                3b. Update w with w'
//                  get old weights and update them with the corresponding learningrate

            }
        }
    }
}
