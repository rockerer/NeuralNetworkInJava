package NeuralNet.Layer;

import NeuralNet.Activationfunction.Activationfunction;
import NeuralNet.Error;

public class OutputLayer extends Layer{
    public OutputLayer(Activationfunction actFunc, int inpCnt, int outpCnt) {
        this.inpCnt = inpCnt + 1;
        this.outpCnt = outpCnt;
        this.weights = new double[this.inpCnt][this.outpCnt];
        for(int i = 0; i < this.inpCnt; i++) {
            for(int j = 0; j< this.outpCnt; j++) {
                this.weights[i][j] = 0.0;
            }
        }
        this.net = new double[this.outpCnt];
        this.outp = new double[this.outpCnt];
        this.actFunc = actFunc;
    }

    @Override
    public void setInpCnt(int cnt) {
        this.inpCnt = cnt;
        this.weights = new double[this.inpCnt][this.outpCnt];
    }

    @Override
    public void eval(double[] outpPrevLayer) {
        if (outpPrevLayer.length != this.inpCnt - 1) {
            System.err.println(Error.SIZE_MISSMATCH);
            System.exit(Error.SIZE_MISSMATCH.ordinal());
        }
        double tmp;

        // calculate net
        // for each input neuron inclusive bias
        for(int i = 0; i < this.outpCnt; i++) {
            tmp = 0.0;
            // f***ing bias!
            for(int j = 0; j < this.inpCnt - 1; j++) {
                tmp += outpPrevLayer[j] * this.weights[j][i];
            }
            // bias here
            tmp += 1 * this.weights[this.inpCnt - 1][i];
            this.net[i] = tmp;
            this.outp[i] = actFunc.activate(this.net[i]);
        }
    }

    @Override
    public void setWeights(double[][] w) {
        System.out.println("w: " + w.length + "weigths: " + this.weights.length);
        if (w.length != this.weights.length) {
            System.err.println(Error.SIZE_MISSMATCH);
            System.exit(Error.SIZE_MISSMATCH.ordinal());
        }
        this.weights = w;
    }

    @Override
    public double getWeight(int start, int end) {
        return this.weights[start][end];
    }

    @Override
    public void setWeight(int startNode, int stopNode, double w) {
        if (startNode < inpCnt && stopNode < outpCnt) {
            weights[startNode][stopNode] = w;
            return;
        }
        System.err.println(Error.NEURON_NOT_EXISTS_IN_LAYER);
        System.exit(Error.NEURON_NOT_EXISTS_IN_LAYER.ordinal());
        System.out.println("ERROR!!!!");
    }
}
