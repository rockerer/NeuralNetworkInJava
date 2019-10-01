package NeuralNet.Activationfunction;

public abstract interface Activationfunction {
    abstract double activate(double inp);
    abstract double derivative(double inp);
}
