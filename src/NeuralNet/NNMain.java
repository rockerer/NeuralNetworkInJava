package NeuralNet;

import NeuralNet.Activationfunction.*;


public class NNMain {
    public static void main(String[] args) {
        System.out.println("Main started");
        testNN1();
    }

    public static void testNN1() {
        NeuralNet n = new NeuralNet();
        // we input 10 values in range [-1, 1]
        n.addLayer(Layer.LayerTyp.inputLayer, 10, new ActivationfunctionTanh());
        // hidden Layer, don't know, what it will do
        n.addLayer(Layer.LayerTyp.hiddenLayer, 25, new ActivationfunctionTanh());
        // hidden Layer, don't know, what it will do, here too
        n.addLayer(Layer.LayerTyp.hiddenLayer, 25, new ActivationfunctionTanh());
        // output 1, if Signal is 100Hz, else 0
        n.addLayer(Layer.LayerTyp.outputLayer, 1, new ActivationfunctionTanh());

        // just for testing purpose
        n.addLayer(Layer.LayerTyp.hiddenLayer, 10, new ActivationfunctionTanh());

        System.out.println(n.run()[0]);
    }

}
