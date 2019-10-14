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

        // debug
        System.out.println("outPrevLayer");
        for(double x : outpPrevLayer) {
            System.out.print(x + " ");
        }
        System.out.println();

        // calculate net
        // for each input neuron inclusive bias
        for(int i = 0; i < this.outpCnt; i++ ) {
            tmp = 0.0d;

            for(int j = 0; j < this.inpCnt - 1; j++) {
                tmp += outpPrevLayer[j] * this.weights[j][i];
            }
            // bias here
            tmp += 1.0 * this.weights[this.inpCnt - 1][i];
            this.net[i] = tmp;

            // debug

            this.outp[i] = actFunc.activate(this.net[i]);
        }
        System.out.println("Input to hidden Layer:");
        for(double x : this.net) {
            System.out.print(x + ", ");
        }
        System.out.println();
        System.out.println("Output of Hidden Layer");
        for(double x : this.outp) {
            System.out.print(x + " ");
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
        if(startNode < inpCnt && stopNode < outpCnt) {
            this.weights[startNode][stopNode] = w;
            return;
        }
        System.out.println("Error");
    }
}

