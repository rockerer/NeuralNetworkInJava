package NeuralNet.Activationfunction;

public class ActivationfunctionReLu implements Activationfunction{

    @Override
    public double activate(double inp) {
        return (inp <= 0 ? 0 : inp);
    }

    @Override
    public double derivative(double inp) {
        return (inp <= 0 ? 0 : 1);
    }
}
