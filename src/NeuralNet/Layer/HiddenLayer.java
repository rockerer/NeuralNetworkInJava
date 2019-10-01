package NeuralNet.Layer;

import NeuralNet.Activationfunction.Activationfunction;
import NeuralNet.Neuron.Neuron;
import NeuralNet.Neuron.NeuronFactory;

// A simple HiddenLayer, nothing fancy like a featuremap
public class HiddenLayer extends Layer{
    private boolean bias;
    private double[] biasVal;
    private Neuron n;
    public <T extends Neuron> HiddenLayer(Class<T> neuron,Activationfunction actFunc, int inpCnt, int outpCnt, boolean bias) {
        this.inpCnt = inpCnt;
        this.outpCnt = outpCnt;
        this.bias = bias;
        this.biasVal = new double[outpCnt];
        this.outp = new double[this.outpCnt];
        this.weights = new double[inpCnt][outpCnt];
        n = NeuronFactory.createNeuron(neuron, actFunc);
    }


    @Override
    public void eval(double[] val) {
        // TODO implement me!
        // FIXME take care of the bias!
        double tmp;
        for(int i = 0; i < this.outpCnt; i++ ) {
            // collect all inputs
            tmp = 0.0d;
            for(int j = 0; j < this.inpCnt; j++) {
                tmp += this.weights[j][i] * val[j];
            }
            if(bias) {
                tmp += biasVal[i];
            }
            // and push them through the activationFunction
            this.outp[i] = n.eval(tmp);
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

