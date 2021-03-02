package regressionReshearch.Glossary;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialLinearRegressionTest {

    @Test
    void normalize() {
        double[][] made_linear = make_linear(3, 6);
        double[][] normalized = normalize(made_linear, -1, 1);
        System.out.println("made_linear = " + Arrays.toString(made_linear[0]));
        System.out.println("normalized_linear = " + Arrays.toString(normalized[0]));

    }

    double[][] normalize(double[][] features, double range_min, double range_max) {

        double[][] returnValue = new double[features.length][features[0].length];
        for (int j = 0; j < features.length; j++) {

            double minValueOfOriginalFeature = findMinValue(features[j]);
            double maxValueOfOriginalFeature = findMaxValue(features[j]);

            for (int i = 0; i < features[j].length; i++) {
                returnValue[j][i] = range_min + (features[j][i] - minValueOfOriginalFeature)
                        * (range_max - range_min) / (maxValueOfOriginalFeature - minValueOfOriginalFeature);
            }

        }

        return returnValue;

    }


    double findMaxValue(double[] xs) {
        double max = 0;
        for (double x : xs) {
            if (x > max) {
                max = x;
            }
        }
        return max;
    }

    double findMinValue(double[] xs) {
        double min = xs[0];
        for (int i = 1; i < xs.length; i++) {
            if (xs[i] < min) {
                min = xs[i];
            }
        }
        return min;
    }

    double[][] make_linear(double weight, double basis) {
        Random random = new Random();
        double[][] returnValue = new double[2][50];
        double[] noise = new double[51];
        for (int i = 0; i < noise.length; i++) {
            noise[i] = (random.nextDouble() - 0.5);
        }

        for (int i = 0; i < returnValue[0].length; i++) {
            returnValue[0][i] = random.nextInt(100) - 50;
//            returnValue[1][i] = returnValue[0][i] * weight+ basis;
            returnValue[1][i] = returnValue[0][i] * weight+ basis + noise[i];
        }
        return returnValue;
    }
}