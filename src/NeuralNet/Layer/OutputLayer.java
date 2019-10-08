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
        this.net = new double[this.inpCnt];
        this.outp = new double[this.outpCnt];
        this.actFunc = actFunc;
    }

    @Override
    public void setInpCnt(int cnt) {
        this.inpCnt = cnt;
        this.weights = new double[this.inpCnt][this.outpCnt];
    }

    @Override
    public void eval() {
        double tmp;
        for(int i = 0; i < this.outpCnt; i++ ) {
            tmp = 0.0;
            for(int j = 0; j < this.inpCnt - 1; j++) {
                tmp += this.weights[j][i] * net[j];
            }
            // bias here
            tmp += this.weights[this.inpCnt - 1][i] * 1;
            this.outp[i] = actFunc.activate(tmp);
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
        if (startNode < inpCnt && stopNode < outpCnt) {
            weights[startNode][stopNode] = w;
            return;
        }
        System.out.println("ERROR!!!!");
    }
}
