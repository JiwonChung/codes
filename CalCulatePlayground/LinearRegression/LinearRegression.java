import java.util.ArrayList;
import java.util.List;
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
        Random random = new Random();
        
        double weight = random.nextInt(200) - 100;
        double basis = random.nextInt(200) - 100;
        
        // this is value that change at once
        double valueToChange = 0.01;

        LinearFunction returnValue = new LinearFunction();
        returnValue.setWeight(weight);
        returnValue.setBasis(basis);
        return returnValue;

    }


}

