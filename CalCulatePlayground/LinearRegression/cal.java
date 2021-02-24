public class cal {
    public static void main(String[] args) {
        double[] xs = {1, 2, 3, 4, 5, 9, 10};
        double[] ys = {5, 6, 7, 8, 9, 13, 14};

        double weight = 1;
        double basis = 4;


        for (int ppp = 0; ppp < 99999999; ppp++) {
            double differentialCoefficient = returnDifferentialCoefficient_basis(xs, ys, weight, basis);

            if (Math.round(differentialCoefficient * 1000) / 1000.0 == 0) {
                System.out.println(ppp);
                System.out.println("break");
                break;
            } else if (differentialCoefficient > 0) {
                basis -= 0.01;
            } else if (differentialCoefficient < 0) {
                basis += 0.01;
            }
        }



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

    static double returnDifferentialCoefficient_basis(double[] xs, double[] ys, double weight, double basis) {
        int m = xs.length;
        double tangentLinesInclination = 0;

        for (int i = 0; i < m; i++) {
            tangentLinesInclination += 2 * basis + 2 * xs[i] * weight - 2 * ys[i];
        }

        return tangentLinesInclination / m;
    }

}
