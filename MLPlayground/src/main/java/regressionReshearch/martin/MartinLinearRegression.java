package regressionReshearch.martin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class MartinLinearRegression {
    static Random random = new Random();
    public static void main(String[] args) {

        double[] xs = new double[500];
        double[] ys = new double[500];


        for (int i = 0; i < 500; i++) {
            xs[i] = random.nextInt();
            ys[i] = xs[i] * 4 + 10;
        }

        double weight = 0;
        double basis = 50;
        double changeValue = 0.01;



        Map<domain.LinearFunction, Double> min = new HashMap<>();

        for (int q = 0; q < 5000; q++) {
            basis -= 0.1;
            for (int ppp = 0; ppp < 5000; ppp++) {
                double differentialCoefficient_2 = returnDifferentialCoefficient_weight(xs, ys, weight, basis);

                double tmp = 0;
                for (int i = 0; i < xs.length; i++) {
                    tmp += Math.pow(weight * xs[i] + basis - ys[i], 2);
                }
                domain.LinearFunction functionTmp = new domain.LinearFunction(weight, basis);
                min.put(functionTmp, tmp / xs.length);


                if (Math.round(differentialCoefficient_2 * 1000) / 1000.0 == 0) {
                    break;
                } else if (differentialCoefficient_2 > 0) {
                    weight -= changeValue;
                } else if (differentialCoefficient_2 < 0) {
                    weight += changeValue;
                }
            }
        }




        double findTheMinimum_cost = 1000000000;
        Iterator<Map.Entry<domain.LinearFunction, Double>> itr = min.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<domain.LinearFunction, Double> entry = itr.next();
            if (entry.getValue() < findTheMinimum_cost) {
                findTheMinimum_cost = entry.getValue();
                weight = entry.getKey().getWeight();
                basis = entry.getKey().getBasis();
            }
        }



        /*
        for (int i = 0; i < 50000; i++) {
            if (returnDifferentialCoefficient_weight(xs, ys, weight, basis) > 0) {
                weight -= changeValue;
            } else {
                weight += changeValue;
            }

            if (returnDifferentialCoefficient_basis(xs, ys, weight, basis) > 0) {
                basis -= changeValue;
            } else {
                basis += changeValue;
            }
        }

        */
        // target
        // weight : 4
        // basis : 10

        System.out.println("weight : " + weight);
        System.out.println("basis : " + basis);

    }



    // H(x) = x * weight + basis
    // cost(weight, basis) = sigma(xs.length - 1, i = 0)[H(xs[i]) - ys[i]] / xs.length
    // (Derivative)cost(weight, basis) = sigma(xs.length - 1, i = 0)[2 * xs[i] + 2 * xs[i] * (basis - ys[i])]
    //       weight
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

}
