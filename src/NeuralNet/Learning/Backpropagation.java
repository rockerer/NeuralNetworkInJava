package NeuralNet.Learning;


import NeuralNet.NeuralNet;

public class Backpropagation implements LearnAlgorithm{
    /**
     * Set the weights of each layer to a random value between min and max
     * @param nn
     * The net which will be initialized
     * @param min
     * The minimal value for initialization
     * @param max
     * The maximal value for initialization
     */
    static void initialize(NeuralNet nn, double min , double max) {
        if (min > max) {
            System.out.println("max is less than min!");
            double tmp = min;
            min = max;
            max = tmp;
        }

        double randomVal = min + (Math.random() * (max - min));
    }

    static void initialize(NeuralNet nn) {
        initialize(nn, 0, 1);
    }

}
