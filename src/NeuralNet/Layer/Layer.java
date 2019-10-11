package NeuralNet.Layer;

import NeuralNet.Activationfunction.Activationfunction;
import NeuralNet.Neuron.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Florian Aul
 * This abstract class defines all methods necessary for implementing the layers
 *
 */
public abstract class Layer {
    int inpCnt, outpCnt;
    /**
     * First index: from
     * Second index: to
     */
    double[][] weights;
    double[] outp;
    double[] net;
    Activationfunction actFunc;

    Layer() {
        this.inpCnt = 0;
        this.outpCnt = 0;
        this.actFunc = null;
        initArrays();
    }
    protected Layer(Activationfunction func, int inpCnt, int outpCnt) {
        this.actFunc = func;
        this.inpCnt = inpCnt;
        this.outpCnt = outpCnt;
        initArrays();
    }

    public Activationfunction getActFunc() {
        return this.actFunc;
    }

    private void initArrays() {
        net = new double[inpCnt];
        outp = new double[outpCnt];
        // take into account the bias!
        weights = new double[inpCnt + 1][outpCnt];
    }

    public void setNet(double[] val) {
        this.net = val;
    }
    public double[] getOutp() {
        return  this.outp;
    }
    public int getOutpCnt() {
        return this.outpCnt;
    }
    public int getInpCnt() {
        return this.inpCnt;
    }
    public void setInpCnt(int cnt) {
        this.inpCnt = cnt;
    }

    // take care of the bias!
    public abstract void eval(double[] outpPrevLayer);
    public abstract void setWeight(int start, int end, double val);
    public abstract void setWeights(double[][] w);


}
