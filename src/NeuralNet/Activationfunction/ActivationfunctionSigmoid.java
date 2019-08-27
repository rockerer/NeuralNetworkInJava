package NeuralNet.Activationfunction;

public class ActivationfunctionSigmoid implements Activationfunction {
    public double activate(double inp) {
        return Math.signum(inp);
    }
}
