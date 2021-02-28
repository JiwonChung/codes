import java.util.Arrays;

public class Calculator {
    public static void main(String[] args) {
        
        double[] xs = new double[500];
        double[] ys = new double[500];
        for (int i = 0; i < 500; i++) {
            xs[i] = i;
            ys[i] = i * 10 + 4;
        }
        System.out.println(Arrays.toString(xs));
        System.out.println(Arrays.toString(ys));

        // double weight = 10;
        // double basis = 4;
        
        // System.out.println(returnDifferentialCoefficient_basis(xs, ys, weight, basis));
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
