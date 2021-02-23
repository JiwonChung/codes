
public class cal {
    public static void main(String[] args) {
        int[] xs = { 1, 2, 3 };
        int[] ys = { 2, 4, 6 };

        double weight = 6.75;
        double basis = 0;
        // System.out.println(returnDerivative(xs, ys, weight, basis));


        for (int i = 0; i < 50000; i++) {
            double inclination = returnDerivative(xs, ys, weight, basis);
            if (inclination == 0) {
                break;
            } else if (inclination < 0) {
                weight += 0.001;
            } else {
                weight -= 0.001;
            }

            if (i % 200 == 0) {
                System.out.println(i + " times weight : " + weight);
            }
        }

        System.out.println(weight);

    }

    static double returnDerivative(int[] xs, int[] ys, double weight, double basis) {
        int m = 3;
        double derivativesInclination = 0;
        for (int i = 0; i < m; i++) {
            derivativesInclination += 2.0 * Math.sqrt(xs[i]) * weight + 2.0 * xs[i] * (basis - ys[i]);
        }
        return derivativesInclination / m;
    }
}
