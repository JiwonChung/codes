package regressionReshearch;

import java.util.Random;

/**
 * java.LinearRegression
 */
public class TeddyLinearRegression {
    static Random random = new Random();
    public static void main(String[] args) {

        double[][] madeValue = make_linear();
        double[] xs = madeValue[0];
        double[] ys = madeValue[1];
        double weight = random.nextDouble() * 2.0 - 1.0,
                basis = random.nextDouble() * 2.0 - 1.0;
        
        for (int j = 0; j < 100; j++) {
            double[] hypothesis = new double[50];
            for (int i = 0; i < hypothesis.length; i++) {
                hypothesis[i] = xs[i] * weight + basis;
            }

            weight -= 0.5 * dontKnowWhatItIs1(hypothesis, xs, ys);
            basis -= 0.5 * dontKnowWhatItIs2(hypothesis, ys);
        }

        System.out.println(weight + ", " + basis);



    }

    /**
     * for weight
     */
    static double dontKnowWhatItIs1(double[] y_hat, double[] x, double[] y) {
        double tmp = 0;
        for (int i = 0; i < y_hat.length; i++) {
            tmp += (y_hat[i] - y[i]) * x[i];
        }
        System.out.println(tmp / 50);
        return tmp / 50;
    }

    /**
     * for basis
     */
    static double dontKnowWhatItIs2(double[] y_hat, double[] y) {
        double tmp = 0;
        for (int i = 0; i < y_hat.length; i++) {
            tmp += (y_hat[i] - y[i]);
        }
        return tmp / 50;
        
    }



    static double[][] make_linear() {
        // weight = 3
        // basis = 5

        double[][] returnValue = new double[2][50];
        double[] noise = new double[50];
        for (double n : noise) {
            n = random.nextDouble();
        }

        for (int i = 0; i < returnValue[0].length; i++) {
            returnValue[0][i] = random.nextDouble();
            returnValue[1][i] = returnValue[0][i] * 3 + 5 - noise[i];
        }
        return returnValue;
    }

    


}

