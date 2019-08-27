package NeuralNet.Activationfunction;

public class ActivationfunctionExp implements Activationfunction {
    public double activate(double inp) {
        return (1/(1 + Math.exp(-inp)));
    }
}
