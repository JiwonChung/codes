import java.util.Random;

public class Cal {
    static Random random = new Random();
    public static void main(String[] args) {

        double[] xs = new double[500];
        double[] ys = new double[500];


        for (int i = 0; i < 500; i++) {
            xs[i] = i;
            ys[i] = i * 4 + 10;
        }
        double weight = random.nextInt(200) - 100;
        double basis = random.nextInt(200) - 100;



        for (int q = 0; q < 1000; q++) {
            for (int ppp = 0; ppp < 5000; ppp++) {
                double differentialCoefficient_2 = returnDifferentialCoefficient_weight(xs, ys, weight, basis);

                if (Math.round(differentialCoefficient_2 * 1000) / 1000.0 == 0) {
                    break;
                }

                if (differentialCoefficient_2 > 0) {
                    weight -= 0.01;
                } else if (differentialCoefficient_2 < 0) {
                    weight += 0.01;
                }
            }

            for (int ppp = 0; ppp < 5000; ppp++) {
                double differentialCoefficient = returnDifferentialCoefficient_basis(xs, ys, weight, basis);
                
                if (Math.round(differentialCoefficient * 1000) / 1000.0 == 0) {
                    break;
                }

                if (differentialCoefficient > 0) {
                    basis -= 0.01;
                } else if (differentialCoefficient < 0) {
                    basis += 0.01;
                }
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
