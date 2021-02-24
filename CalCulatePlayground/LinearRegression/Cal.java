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

        xs[10] = 80;
        ys[5] = 9;

        double weight = random.nextInt(50) - 25;
        double basis = 10;
        
        double min = 1000;
        boolean kill_weight = false;

        for (int q = 0; q < 5000; q++) {
            boolean a = true, b = false;
            
            
            if (!kill_weight) {
                a = false;
                for (int ppp = 0; ppp < 5000; ppp++) {
                    double differentialCoefficient_2 = returnDifferentialCoefficient_weight(xs, ys, weight, basis);

                    if (Math.round(differentialCoefficient_2 * 100) / 100.0 == 0) {
                        double tmpMin = 0;
                        for (int i = 0; i < xs.length; i++) {
                            tmpMin += weight * xs[i] + basis - ys[i];
                        }

                        if (Math.round((min - tmpMin) * 100) / 100.0 == 0) {
                            System.out.println("help");
                            kill_weight = !kill_weight;
                        }                  
                        
                        if (min > tmpMin) {
                            a = !a;
                            min = tmpMin;
                            break;
                        }
                    } else if (differentialCoefficient_2 > 0) {
                        weight -= 0.01;
                    } else if (differentialCoefficient_2 < 0) {
                        weight += 0.01;
                    }
                }
            }

            // for (int ppp = 0; ppp < 5000; ppp++) {
            //     double differentialCoefficient = returnDifferentialCoefficient_basis(xs, ys, weight, basis);
                
            //     if (Math.round(differentialCoefficient * 100) / 100.0 == 0) {
            //         b = !b;
            //         break;
            //     } else if (differentialCoefficient > 0) {
            //         basis -= 0.01;
            //     } else if (differentialCoefficient < 0) {
            //         basis += 0.01;
            //     }
            // }


            if (a && b) {
                break;
            }
        }

        // target
        // weight : 4
        // basis : 10
        
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
