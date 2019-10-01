package NeuralNet.Activationfunction;

public class ActivationfunctionTanh implements Activationfunction {
    @Override
    public double activate(double inp) {
        System.out.println("Tanh");
        return Math.tanh(inp);
    }

    @Override
    public double derivative(double inp) {
        return 1./(Math.pow(Math.cosh(inp), 2));
    }
}
