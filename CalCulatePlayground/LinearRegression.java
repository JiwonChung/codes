import java.util.Random;

/**
 * LinearRegression
 */
public class LinearRegression {

    public static void main(String[] args) {
        int[] xs = {1, 2, 3};
        int[] ys = {4, 5, 6};

        linearRegression(xs, ys);

        
    }

    static LinearFunction linearRegression(int[] xs, int[] ys) {
        



        LinearFunction returnValue = new LinearFunction();
        return LinearFunction;

    }

    class LinearFunction {
        double weight;
        double basis;

        private void setWeight(double weight) {
            this.weight = weight;
        }

        private void setBasis(double basis) {
            this.basis = basis;
        }

        private double getWeight() {
            return weight;
        }

        private double getBasis() {
            return basis;
        }
    }
}

