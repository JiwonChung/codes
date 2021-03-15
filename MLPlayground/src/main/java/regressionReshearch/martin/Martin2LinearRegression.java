package regressionReshearch.martin;

import domain.LinearFunction;

import java.util.Random;

public class Martin2LinearRegression {

    // 작동하지 않음
    public static void main(String[] args) {
        double[][] tmp = make_linear(0.3, 0.6);
        double[] xs = tmp[0];
        double[] ys = tmp[1];
        LinearFunction model = new LinearFunction(0, 0);

        double correctionValue = 0.01;

        // 아무 의미없는 알파벳으로 p를 고름
        for (int p = 1; p < 10; p++) {
            double coefficient_weight = returnDifferentialCoefficient_weight(xs, ys, model.getWeight(), model.getBasis());
            model.setWeight(model.getWeight() - correctionValue * coefficient_weight);
            double coefficient_basis = returnDifferentialCoefficient_basis(xs, ys, model.getBasis(), model.getWeight());
            model.setBasis(model.getBasis() - correctionValue * coefficient_basis);
            System.out.println("for debug");
        }
        System.out.println("weight : " + model.getWeight());
        System.out.println("basis : " + model.getBasis());

    }

    static double returnDifferentialCoefficient_weight(double[] xs, double[] ys, double weight, double basis) {
        int m = xs.length;
        double tangentLinesInclination = 0;

        // 도함수
        for (int i = 0; i < m; i++) {
            tangentLinesInclination += 2 * Math.pow(xs[i], 2.0) * weight + 2 * xs[i] * (basis - ys[i]);
        }
        return tangentLinesInclination / m;
    }

    // 도함수
    static double returnDifferentialCoefficient_basis(double[] xs, double[] ys, double weight, double basis) {
        int m = xs.length;
        double tangentLinesInclination = 0;

        for (int i = 0; i < m; i++) {
            tangentLinesInclination += 2 * basis + 2 * xs[i] * weight - 2 * ys[i];
        }

        return tangentLinesInclination / m;
    }

    static double[][] make_linear(double weight, double basis) {
        Random random = new Random();
        double[][] returnValue = new double[2][50];
        double[] noise = new double[51];
        for (int i = 0; i < noise.length; i++) {
            noise[i] = random.nextDouble() - 0.5;
        }

        for (int i = 0; i < returnValue[0].length; i++) {
            returnValue[0][i] = random.nextDouble();
            returnValue[1][i] = returnValue[0][i] * weight+ basis + noise[i];
        }
        return returnValue;
    }
}
