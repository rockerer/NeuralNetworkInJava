package NeuralNet.Layer;

import NeuralNet.Activationfunction.Activationfunction;
import NeuralNet.Neuron.Neuron;
import NeuralNet.Error;

// A simple HiddenLayer, nothing fancy like a featuremap
public class HiddenLayer extends Layer{
    private Neuron n;
    public HiddenLayer(Activationfunction actFunc, int inpCnt, int outpCnt) {
        // take the bias into account
        this.inpCnt = inpCnt + 1;
        this.outpCnt = outpCnt;
        this.net = new double[this.inpCnt];
        this.outp = new double[this.outpCnt];
        this.weights = new double[inpCnt][outpCnt];
    }


    @Override
    public void eval() {
        // TODO implement me!
        // go for every outp and calculate it by Vector-Matrix multiplication
        double tmp;
        for(int i = 0; i < this.outpCnt; i++ ) {
            // collect all inputs
            tmp = 0.0d;
            for(int j = 0; j < this.inpCnt; j++) {
                tmp += this.weights[j][i] * net[j];
            }
            // and push them through the activationFunction
            this.outp[i] = n.eval(tmp);
        }
    }

    @Override
    public void setWeights(double[][] w) {
        if (w.length != this.weights.length) {
            System.err.println(Error.SIZE_MISSMATCH);
            System.exit(Error.SIZE_MISSMATCH.ordinal());
        }
        this.weights = w;
    }

    @Override
    public void setWeight(int start, int end, double val) {
        this.weights[start][end] = val;
    }
}

