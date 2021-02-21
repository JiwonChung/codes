import java.util.Random;

/**
 * LinearRegression
 */
public class LinearRegression {

    public static void main(String[] args) {
        int[] xs = {1, 2, 3};
        int[] ys = {4, 5, 6};

        LinearFunction a = linearRegression(xs, ys);
        System.out.println(a.getBasis());
    }

    static LinearFunction linearRegression(int[] xs, int[] ys) {
        



        LinearFunction returnValue = new LinearFunction();
        returnValue.setWeight(10);
        returnValue.setBasis(100);
        return returnValue;

    }


}

