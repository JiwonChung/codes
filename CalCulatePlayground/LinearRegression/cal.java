public class cal {
    public static void main(String[] args) {
        int[] xs = {1, 2, 3};
        int[] ys = {2, 3, 4};

        double weight = 0;
        double basis = 1;

        System.out.println("inclination : " + returnDifferentialCoefficient(xs, ys, weight, basis));

        double tmp = 0;
        for (int i = 1; i < 10001; i++) {
            double inclination = returnDifferentialCoefficient(xs, ys, weight, basis);
            tmp = inclination;
            if (inclination < 0) {
                weight += 0.001;
            } else {
                weight -= 0.001;
            }
            if (i % 1000 == 0) {
                System.out.println(i + " times weight : " + weight);
            }
        }
        System.out.println("inclination : " + tmp);
        
    }

    // H(x) = x * weight + basis
    // cost(weight, basis) = sigma(xs.length - 1, i = 0)[H(xs[i]) - ys[i]] / xs.length
    // (Derivative)cost(weight, basis) = sigma(xs.length - 1, i = 0)[2 * xs[i] + 2 * xs[i] * (basis - ys[i])]
    //       weight
    static double returnDifferentialCoefficient(int[] xs, int[] ys, double weight, double basis) {
        int m = xs.length;
        double tangentLinesInclination = 0;

        // 도함수
        for (int i = 0; i < m; i++) {
            tangentLinesInclination += 2 * Math.sqrt(xs[i]) * weight + 2 * xs[i] * (basis - ys[i]);
        }
        return tangentLinesInclination / m;
    }
}
