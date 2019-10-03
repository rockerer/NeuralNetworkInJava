package NeuralNet.Learning;


import NeuralNet.NeuralNet;

/**
 * Backpropagation takes the total error and tries to minimize this error
 * We have a list of pairs <input, output> by which we will calculate the error
 */
public class Backpropagation implements LearnAlgorithm{

    // here we have (almost) all information we need
    NeuralNet neuralNet = null;


    public Backpropagation() {
    }

    public Backpropagation(NeuralNet n) {
        this.neuralNet = n;
    }

    public void setNeuralNet(NeuralNet neuralNet) {
        this.neuralNet = neuralNet;
    }

    /**
     * Set the weights of each layer to a random value between min and max
     * @param min
     * The minimal value for initialization
     * @param max
     * The maximal value for initialization
     */
    void initialize(double min , double max) {
        if (min > max) {
            System.out.println("max is less than min!");
            double tmp = min;
            min = max;
            max = tmp;
        }

        double randomVal = min + (Math.random() * (max - min));
    }

    // Wrapper for calling initialize with standartvalues
    void initialize() {
        initialize(0, 1);
    }

    public void learn() {
        System.out.println("Started learning");
        // initialize the network with random values
        initialize();

        // learn round for round
        for (double a: neuralNet.eval(new double[]{10,10})) {
            System.out.println(a);
        }

        // save/export the values
    }

}
