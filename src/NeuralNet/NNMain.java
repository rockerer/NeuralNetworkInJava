package NeuralNet;

import NeuralNet.Activationfunction.*;
import NeuralNet.Layer.HiddenLayer;
import NeuralNet.Layer.InputLayer;
import NeuralNet.Layer.Layer;
import NeuralNet.Neuron.Perceptron;

import java.lang.reflect.AnnotatedType;


public class NNMain {
    public static void main(String[] args) {
        System.out.println("Main started");
        testNN0();
//        testNN1();
    }

    private static void testNN0() {
        NeuralNet n = new NeuralNet(2, 1);
        n.setInputLayer(Perceptron.class, new ActivationfunctionTanh());
        n.setOutputLayer(Perceptron.class, new ActivationfunctionTanh());
        n.addHiddenLayer(Perceptron.class, new ActivationfunctionTanh(), 2, false);
        n.addHiddenLayer(Perceptron.class, new ActivationfunctionTanh(), 2, true);
//        n.addHiddenLayer(Perceptron.class, new ActivationfunctionTanh(), 12, false);
//        n.addHiddenLayer(Perceptron.class, new ActivationfunctionTanh(), 200, false);
        n.getHiddenLayer(0).setWeight(0,0,1);
        n.getHiddenLayer(0).setWeight(0,1,1);
        n.getHiddenLayer(0).setWeight(1,0,1);
        n.getHiddenLayer(0).setWeight(1,1,1);
        n.getHiddenLayer(1).setWeight(0,0,1);
        n.getHiddenLayer(1).setWeight(1,1,1);
        n.getHiddenLayer(1).setBias(1, 10);
        n.setWeightsOutp(0,0,1);
        n.setWeightsOutp(1,0,1);
//        n.printInfo();
        double[] res = n.eval(new double[]{1.0,1.0});
        for (double v : res) {
            System.out.println(v);
        }
    }

    private static void testNN1() {
        NeuralNet n = new NeuralNet(10, 1);
        // we input 10 values in range [-1, 1]
        n.setInputLayer(Perceptron.class, new ActivationfunctionTanh());
        n.setOutputLayer(Perceptron.class, new ActivationfunctionTanh());
        n.addHiddenLayer(Perceptron.class, new ActivationfunctionTanh(), 10, false);
        // hidden Layer, don't know, what it will do
        n.addHiddenLayer(Perceptron.class, new ActivationfunctionTanh(), 10, false);


    }

}
