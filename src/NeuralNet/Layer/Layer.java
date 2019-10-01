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
    private List<Neuron> Neuronen;
    int inpCnt, outpCnt;
    double[][] weights;
    double[] outp;

    Layer() {
        this.Neuronen = new ArrayList<>();
        this.inpCnt = 0;
        this.outpCnt = 0;
        this.outp = new double[0];
    }
    protected Layer(int inpCnt, int outpCnt) {
        this.Neuronen = new ArrayList<>();
        this.inpCnt = inpCnt;
        this.outpCnt = outpCnt;
        this.outp = new double[outpCnt];
    }

    /**
     * @return a value
     */
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

    public <T extends Neuron> Layer(Class<T> neuron, int cnt, boolean hasBias, Activationfunction actFunc) {
    }

    // take care of the bias!
    public abstract void eval(double[] val);
    public abstract void setWeight(int start, int end, double val);
    public abstract void setBias(int endNode, double val);


}
