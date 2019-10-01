package NeuralNet.Activationfunction;

public class ActivationfunctionSignum implements Activationfunction {
    @Override
    public double activate(double inp) {
        return Math.signum(inp);
    }

    @Override
    public double derivative(double inp) {
        return 0;
    }
}
