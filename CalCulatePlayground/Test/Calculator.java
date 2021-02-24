package Test;

public class Calculator {
    public static void main(String[] args) {
        double basis = 4;
        double[] xs = {1, 2, 3, 4, 5, 9, 10};
        double[] ys = {5, 6, 7, 8, 9, 13, 14};
        double weight = 1;

        for (int i = 0; i < xs.length; i++) {
            System.out.println(2 * basis + 2 * xs[i] * weight - 2 * ys[i]);
        }
    }
}
