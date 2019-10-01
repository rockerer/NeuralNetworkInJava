package NeuralNet.Learning;

class ErrorTest {
    public static void main(String[] args) {

        System.out.println("Hello ERROR");
        Errorfunction ef = new Errorfunction();
        System.out.println(ef.calculateTotalError(new double[]{1,2,3}, new double[]{3,2,1,0}));
        System.out.println(ef.calculateTotalError(new double[]{1,2,3}, new double[]{3,2,1}));
        System.out.println(ef.calculateTotalError(new double[]{1,2,3}, new double[]{1,2,3}));
    }
}
