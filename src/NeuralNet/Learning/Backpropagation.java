package NeuralNet.Learning;


import NeuralNet.Layer.Layer;
import NeuralNet.NeuralNet;

/**
 * Backpropagation takes the total error and tries to minimize this error
 * We have a list of pairs <input, output> by which we will calculate the error
 */
public class Backpropagation implements LearnAlgorithm{

    // here we have (almost) all information we need
    private NeuralNet neuralNet = null;


    public Backpropagation() {
    }

    public Backpropagation(NeuralNet n) {
        this.neuralNet = n;
    }

    public void setNeuralNet(NeuralNet neuralNet) {
        this.neuralNet = neuralNet;
    }

    /**
     * Wrapper for calling initialize with default values
      */
    private void initialize() {
        initialize(0, 1);
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


    public void learn() {
        System.out.println("Started learning");
        // initialize the network with random values
        initialize();

        // learn round for round
        /*
        for (double a: neuralNet.eval(new double[]{1,1})) {
            System.out.println(a);
        }
         */

        // save/export the values
    }

}
