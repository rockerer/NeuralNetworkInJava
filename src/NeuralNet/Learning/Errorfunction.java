package NeuralNet.Learning;

class Errorfunction {
    static double[] calculateError(double[] target, double[] output) {
        if(target.length != output.length) {
            return null;
        }
        double[] res = new double[target.length];
        for(int i = 0; i < target.length; i++) {
            res[i] = 0.5 * Math.pow(target[i] - output[i], 2);
        }
        return res;
    }

    static double calculateTotalError(double[] singleErrors) {
        double res = 0;
        for (double x: singleErrors) {
            res += x;
        }
        return res;
    }


    static double calculateTotalError(double[] target, double[] output) {
        if (target.length != output.length) {
            System.err.println("Arrays don't match!");
            return Double.MAX_VALUE;
        }
        double res = 0.0;
        for(int i = target.length-1; i >= 0; i--) {
            res += 0.5 * Math.pow(target[i] - output[i], 2);
        }
        return res;
    }
}
