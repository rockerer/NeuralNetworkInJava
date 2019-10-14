package NeuralNet.Layer;

import NeuralNet.Activationfunction.Activationfunction;
import NeuralNet.Error;

public class InputLayer extends Layer{
    public InputLayer(int cnt) {
        this.inpCnt = cnt;
        this.outpCnt = cnt;
        this.outp = new double[this.outpCnt];
    }

    /**
     * This function sets the output of the layer to the given input
     * There is nothing left what has to be done!
     * The vector of input values
     */
    @Override
    public void eval(double[] outpPrevLayer) {
        this.outp = this.net;

        // debug
        System.out.println("Output of Input-Layer:");
        for(double x : this.outp) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public void eval() {
        this.outp = this.net;
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
    public double getWeight(int start, int end) {
        return this.weights[start][end];
    }

    @Override
    public void setWeight(int a, int b, double x) {
        System.err.println(Error.SET_BIAS_IN_INPUT_LAYER);
        System.exit(Error.SET_BIAS_IN_INPUT_LAYER.ordinal());
    }
}
