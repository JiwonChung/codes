package regressionReshearch.Glossary;

public class PolynomialLinearRegression {
    public static void main(String[] args) {

    }



    // test passed

    /**
     * this method return normalized array
     * @param features : 오리지널 데이터
     * @param range_min : minimum range of normalized data
     * @param range_max : maximum range of normalized data
     * @return normalize feature
     */
    static double[][] normalize(double[][] features, double range_min, double range_max) {

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


    static double findMaxValue(double[] xs) {
        double max = 0;
        for (double x : xs) {
            if (x > max) {
                max = x;
            }
        }
        return max;
    }

    static double findMinValue(double[] xs) {
        double min = xs[0];
        for (int i = 1; i < xs.length; i++) {
            if (xs[i] < min) {
                min = xs[i];
            }
        }
        return min;
    }

    static double[][] enhanced_MakeLinear(double weight1, double weight2, double weight3, double bias, int numberOfData) {
        double[][] returnValue = new double[3][numberOfData];

    }
}
