package NeuralNet.Activationfunction;

public class ActivationfunctionTanh implements Activationfunction {
    public double activate(double inp) {
        System.out.println("Tanh");
        return Math.tanh(inp);
    }
}
