package regressionReshearch.Glossary;

import java.util.Random;

public class GlossaryLinearRegression {

    public static void main(String[] args) {
        double[][] returnValue = make_linear(3.5, 7.9);
        double[] xs = returnValue[0];
        double[] ys = returnValue[1];

        double weight = 0;
        double bias = 0;

        for (int i = 1; i < 500001; i++) {
            double[] updatedModel = updateModel(xs, ys, weight, bias, 0.0001);
            weight = updatedModel[0];
            bias = updatedModel[1];
            if (i % 100000 == 0) {
                System.out.println(i + " times error: " + cost_function(xs, ys, weight, bias));
            }
        }
        System.out.println(Math.round(weight * 100) / 100.0 + ", " + Math.round(bias * 100) / 100.0);
    }

    static double cost_function(double[] xs, double[] ys, double weight, double bias) {
        double total_error = 0;
        for (int i = 0; i < xs.length; i++) {
            total_error += Math.pow(xs[i] * weight + bias - ys[i], 2);
        }
        return total_error / xs.length;
    }

    static double[] updateModel(double[] xs, double[] ys, double weight, double bias, double learning_rate) {
        double weight_derivative = 0; // weight differential  coefficient
        double bias_derivative = 0; // bias differential coefficient
        int xLength = xs.length;

        for (int i = 0; i < xLength; i++) {
            weight_derivative += -2 * xs[i] * (ys[i] - (weight * xs[i] + bias));
            bias_derivative += -2 * (ys[i] - (xs[i] * weight + bias));
        }

        weight -= (weight_derivative / xLength) * learning_rate;
        bias -= (bias_derivative / xLength) * learning_rate;

        return new double[]{weight, bias};
    }


    static double[][] make_linear(double weight, double basis) {
        Random random = new Random();
        double[][] returnValue = new double[2][50];
        double[] noise = new double[51];
        for (int i = 0; i < noise.length; i++) {
            noise[i] = (random.nextDouble() - 0.5);
        }

        for (int i = 0; i < returnValue[0].length; i++) {
            returnValue[0][i] = random.nextDouble();
//            returnValue[1][i] = returnValue[0][i] * weight+ basis;
            returnValue[1][i] = returnValue[0][i] * weight+ basis + noise[i];
        }
        return returnValue;
    }
}
