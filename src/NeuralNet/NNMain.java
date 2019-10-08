package NeuralNet;

import NeuralNet.Activationfunction.*;
import NeuralNet.Learning.Backpropagation;
import NeuralNet.Neuron.Perceptron;


class NNMain {
    public static void main(String[] args) {
        System.out.println("Main started");
//        testNN0();
//        testNN1();
        testNN2();
    }

    private static void testNN0() {
        NeuralNet n = new NeuralNet();
        n.addInputLayer(2);
        n.addOutputLayer(new ActivationfunctionSigmoid(), 1);
        /*
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
         */
//        n.printInfo();
        double[] res = n.eval(new double[]{1.0,1.0});
        System.out.println("Output:");
        for (double v : res) {
            System.out.println(v);
        }

        n.setWeightOutp(0,0,1);
        res = n.eval(new double[]{1.0,1.0});
        System.out.println("Output:");
        for (double v : res) {
            System.out.println(v);
        }

//        n.setWeightsOutp(new double[][] {{1,1,0}});
        n.setWeightsOutp(new double[][] {{-1},{-1},{10}});
        res = n.eval(new double[]{1.0,1.0});
        System.out.println("Output:");
        for (double v : res) {
            System.out.println(v);
        }

    }

    private static void testNN1() {
        NeuralNet n = new NeuralNet();
        // we input 10 values in range [-1, 1]
//        n.addInputLayer(Perceptron.class, new ActivationfunctionTanh());
//        n.setOutputLayer(Perceptron.class, new ActivationfunctionTanh());
//        n.addHiddenLayer(Perceptron.class, new ActivationfunctionTanh(), 10, false);
        // hidden Layer, don't know, what it will do
//        n.addHiddenLayer(Perceptron.class, new ActivationfunctionTanh(), 10, false);
    }

    private static void testNN2() {
        NeuralNet n = new NeuralNet();
        n.addInputLayer(2);
        n.addHiddenLayer(new ActivationfunctionSigmoid(), 1);
        n.addHiddenLayer(new ActivationfunctionSigmoid(), 30);
        n.addOutputLayer(new ActivationfunctionSigmoid(), 1);
        for (double a: n.eval(new double[]{10,10})) {
            System.out.println(a);
        }
        Backpropagation bp = new Backpropagation(n);
        bp.learn();
        n.printInfo();

        for (double a: n.eval(new double[]{1,0})) {
            System.out.println(a);
        }
        for (double a: n.eval(new double[]{0,1})) {
            System.out.println(a);
        }
    }

}
