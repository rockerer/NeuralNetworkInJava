package NeuralNet;

import NeuralNet.Activationfunction.*;


public class NNMain {
    public static void main(String[] args) {
        System.out.println("Main started");
        testNN1();
    }

    public static void testNN1() {
        NeuralNet n = new NeuralNet();
        n.addLayer(Layer.LayerTyp.inputLayer, 3, new ActivationfunctionExp());
        n.addLayer(Layer.LayerTyp.hiddenLayer, 5, new ActivationfunctionLin());
    }

}
