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
        
        double cost = 100;
        List<Double> hypothesisDoubles = new ArrayList<Double>();
        while(cost < 0.01) {
            for (int i = 0; i < xs.length; i++) {
                // hypothesis
                hypothesisDoubles.add(weight * xs[i] + basis);
            }
            cost = 0;
            for (int i = 0; i < hypothesisDoubles.size(); i++) {
                // use "cost" temporally
                cost += Math.sqrt(hypothesisDoubles.get(i) - ys[i]);
            }

            cost = cost / hypothesisDoubles.size();
            
            // Use Derivative
            
            
            
        }



        LinearFunction returnValue = new LinearFunction();
        returnValue.setWeight(weight);
        returnValue.setBasis(basis);
        return returnValue;

    }


}

