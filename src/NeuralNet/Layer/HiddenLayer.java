package NeuralNet.Layer;

import NeuralNet.Activationfunction.Activationfunction;
import NeuralNet.Neuron.Neuron;

// A simple HiddenLayer, nothing fancy like a featuremap
public class HiddenLayer extends Layer{
    private boolean bias;
    private double[] biasVal;
    Activationfunction actFunc;
    public <T extends Neuron> HiddenLayer(Class<T> neuron,Activationfunction actFunc, int inpCnt, int outpCnt, boolean bias) {
        this.inpCnt = inpCnt;
        this.outpCnt = outpCnt;
        this.bias = bias;
        this.biasVal = new double[outpCnt];
        this.outp = new double[this.outpCnt];
        this.weights = new double[inpCnt][outpCnt];
        this.actFunc = actFunc;
    }


    @Override
    public void eval(double[] val) {
        // TODO implement me!
        // FIXME take care of the bias!
        double tmp;
        for(int i = 0; i < this.outpCnt; i++ ) {
            this.outp[i] = 0.0d;
            tmp = 0.0d;
            for(int j = 0; j < this.inpCnt; j++) {
                tmp += this.weights[j][i] * val[j];
            }
            this.outp[i] = this.actFunc.activate(tmp);
        }
        if(bias) {
            for(int i = 0; i < this.outpCnt; i++) {
                this.outp[i] += biasVal[i];
            }
        }
    }

    @Override
    public void setWeight(int start, int end, double val) {
        this.weights[start][end] = val;
    }
    public void setBias(int endNode, double val)  {
        this.biasVal[endNode] = val;
    }
}

