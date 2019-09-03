package NeuralNet.Layer;

import NeuralNet.Activationfunction.Activationfunction;
import NeuralNet.Neuron.Neuron;

import java.util.ArrayList;

public class InputLayer extends Layer{
    public <T extends Neuron> InputLayer(Class<T> neuron, Activationfunction actFunc, int cnt) {
        this.inpCnt = cnt;
        this.outpCnt = cnt;
        this.outp = new double[this.outpCnt];
    }

    @Override
    public void eval(double[] val) {
        // TODO implement me
        this.outp = val;
    }

    @Override
    public void setWeight(int a, int b, double x) {
        System.out.println("Srsly???");
    }

    @Override
    public void setBias(int endNode, double val) {
    }
}
