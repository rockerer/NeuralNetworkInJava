package NeuralNet.Activationfunction;

public class ActivationfunctionTanh implements Activationfunction {
    public double activate(double inp) {
        return Math.tanh(inp);
    }
}
