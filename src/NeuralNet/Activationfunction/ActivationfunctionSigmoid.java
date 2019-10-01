package NeuralNet.Activationfunction;

public class ActivationfunctionSigmoid implements Activationfunction {
    public double activate(double inp) {
        return (1/(1 + Math.exp(-inp)));
    }

    @Override
    public double derivative(double inp) {
        return (activate(inp) * (1.0 - activate(inp)));
    }
}
