package NeuralNet.Neuron;

public class Perceptron extends Neuron{
    public Perceptron() {
    }
    @Override
    public void setInput(double inp) {
        this.inp = inp;
    }

    @Override
    public void fire() {
        System.out.println("FIRE!!!");
    }

    @Override
    public void fire(double inp) {
        System.out.println("FIRE with input!!!");

    }
}
