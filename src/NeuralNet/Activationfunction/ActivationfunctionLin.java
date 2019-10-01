package NeuralNet.Activationfunction;

public  class ActivationfunctionLin implements Activationfunction {
    @Override
    public double activate(double inp) {
        return inp;
    }
    @Override
    public double derivative(double inp) {return 1;}
}
