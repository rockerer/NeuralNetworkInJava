package NeuralNet;

import NeuralNet.Activationfunction.*;
import NeuralNet.Learning.Backpropagation;
import NeuralNet.Neuron.Perceptron;


class NNMain {
    public static void main(String[] args) {
        System.out.println("Main started");
//        testNN0();
//        testNN1();
//        testNN2();
//        testNN4();
        testNNXORReLu();
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
        // create neural network
        NeuralNet n = new NeuralNet();
        n.addInputLayer(2);
        n.addOutputLayer(new ActivationfunctionSigmoid(), 1);

//        n.setWeightsOutp(new double[][] {{1},{1},{-1}});

        Backpropagation bp = new Backpropagation(n);
        bp.setTrainingData(new double[][][]
                {
                        {
                                {0, 0},
                                {0}
                        },
                        {
                                {0, 1},
                                {0}
                        },
                        {
                                {1, 0},
                                {0}
                        },
                        {
                                {1, 1},
                                {1}
                        },
                });

        bp.learn(true);

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
//        n.addHiddenLayer(new ActivationfunctionSigmoid(), 1);
        n.addHiddenLayer(new ActivationfunctionSigmoid(), 30);
        n.addOutputLayer(new ActivationfunctionSigmoid(), 1);
        for (double a: n.eval(new double[]{10,10})) {
            System.out.println(a);
        }
        Backpropagation bp = new Backpropagation(n);
        bp.learn(true);
        n.printInfo();

        for (double a: n.eval(new double[]{1,0})) {
            System.out.println(a);
        }
        for (double a: n.eval(new double[]{0,1})) {
            System.out.println(a);
        }

    }

    private static void testNN4() {
        NeuralNet n = new NeuralNet();
        n.addInputLayer(2);
        n.addHiddenLayer(new ActivationfunctionSigmoid(), 2);
        n.addOutputLayer(new ActivationfunctionSigmoid(), 2);

        n.setWeightsHidden(0, 0,0, 0.15);
        n.setWeightsHidden(0, 0,1, 0.25);
        n.setWeightsHidden(0, 1,0, 0.2);
        n.setWeightsHidden(0, 1,1, 0.3);
        // bias
        n.setWeightsHidden(0, 2,0, 0.35);
        n.setWeightsHidden(0, 2,1, 0.35);

//        /*
        n.setWeightsOutp(new double[][] {
                {0.4, 0.5},
                {0.45, 0.55},
                {0.6, 0.6}
        });
//         */
        /*
        n.setWeightOutp(0, 0, 0.4);
        n.setWeightOutp(0, 1, 0.5);
        n.setWeightOutp(1, 0, 0.45);
        n.setWeightOutp(1, 1, 0.55);
        n.setWeightOutp(2, 0, 0.6);
        n.setWeightOutp(2, 1, 0.6);
*/
//        n.printInfo();

//        n.getHiddenLayer(0).printWeights();

//        double[] outp = n.eval(new double[] {0.05, 0.1});
//        for(double x: outp)  {
//            System.out.println(x);
//        }

        Backpropagation bp = new Backpropagation(n);
        bp.setTrainingData(new double[][][]{
                {
                        {0.05, 0.10},
                        {0.01, 0.99}
                },
                {
                        {0.05, 0.10},
                        {0.01, 0.99}
                }
       });
        bp.learn(false);
//        bp.learn(true);
    }

    public static void testNNXORReLu() {
        NeuralNet n = new NeuralNet();
        n.addInputLayer(2);
        n.addHiddenLayer(new ActivationfunctionReLu(), 2);
        n.addOutputLayer(new ActivationfunctionReLu(), 1);

        n.setWeightsHidden(0, 0, 0, 1);
        n.setWeightsHidden(0, 0, 1, -1);
        n.setWeightsHidden(0, 1, 0, -1);
        n.setWeightsHidden(0, 1, 1, 1);

        n.setWeightOutp(0, 0, 1);
        n.setWeightOutp(1, 0, 1);


        for(double x: n.eval(new double[]{0,0})) {
            System.out.println(x);
        }
        for(double x: n.eval(new double[]{0,1})) {
            System.out.println(x);
        }
        for(double x: n.eval(new double[]{1,0})) {
            System.out.println(x);
        }
        for(double x: n.eval(new double[]{1,1})) {
            System.out.println(x);
        }
    }

}
