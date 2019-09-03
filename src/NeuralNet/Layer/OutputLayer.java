package NeuralNet.Layer;

import NeuralNet.Activationfunction.Activationfunction;
import NeuralNet.Neuron.Neuron;

public class OutputLayer extends Layer{
    public <T extends Neuron> OutputLayer(Class<T> neuron, Activationfunction actFunc, int outpCnt) {
        this.inpCnt = 0;
        this.outpCnt = outpCnt;
        this.weights = new double[this.inpCnt][this.outpCnt];
        this.outp = new double[this.outpCnt];
    }

    public <T extends Neuron> OutputLayer(Class<T> neuron, Activationfunction actFunc, int outpCnt, int inpCnt) {
        this.inpCnt = inpCnt;
        this.outpCnt = outpCnt;
        this.weights = new double[this.inpCnt][this.outpCnt];
        this.outp = new double[this.outpCnt];
    }

    @Override
    public void setInpCnt(int cnt) {
        this.inpCnt = cnt;
        this.weights = new double[this.inpCnt][this.outpCnt];
    }

    @Override
    public void eval(double[] val) {
        // TODO implement me
        // FIXME find the error
        for(int i = 0; i < this.outpCnt; i++ ) {
            this.outp[i] = 0.0d;
            for(int j = 0; j < this.inpCnt; j++) {
                this.outp[i] += this.weights[j][i] * val[j];
            }
        }
    }

    @Override
    public void setWeight(int startNode, int stopNode, double w) {
        if (startNode < inpCnt && stopNode < outpCnt) {
            weights[startNode][stopNode] = w;
            return;
        }
        System.out.println("ERROR!!!!");
    }

    @Override
    public void setBias(int endNode, double val) {
    }
}
