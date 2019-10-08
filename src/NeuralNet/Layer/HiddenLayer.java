package NeuralNet.Layer;

import NeuralNet.Activationfunction.Activationfunction;
import NeuralNet.Error;

import java.nio.file.StandardOpenOption;

public class HiddenLayer extends Layer{
    public HiddenLayer(Activationfunction actFunc, int inpCnt, int outpCnt) {
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
        for(int i = 0; i < this.outpCnt; i++ ) {
            tmp = 0.0d;

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
    public void setWeight(int startNode, int stopNode, double w) {
        if(startNode < inpCnt && stopNode < outpCnt) {
            weights[startNode][stopNode] = w;
            return;
        }
        System.out.println("Error");
    }
}

