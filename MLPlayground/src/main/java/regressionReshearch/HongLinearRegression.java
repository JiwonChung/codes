package regressionReshearch;

import domain.LinearFunction;

import java.util.Random;

// 측정할 테이터의 x 입력이 random.nextDouble() 일때만 제기능을 발휘함 뭔가 이상함
public class HongLinearRegression {
    static Random random = new Random();
    static LinearFunction model = new LinearFunction(1, 1);
    public static void main(String[] args) {

        // 알고리즘의 예시 :
        /*
        double[][] madeValue = make_linear();
        double[] xs = madeValue[0];
        double[] ys = madeValue[1];

        double y_hat = hypothesis(xs[0]);

        // define weight_rate
        model.setWeight(model.getWeight() + 0.1);
        double y_hat_2 = hypothesis(xs[0]);
        double weight_rate = (y_hat_2 - y_hat) / 0.1; // weight 변화량 : xs[0]
        System.out.println("weight rate : " + weight_rate);

        // define basis_rate
        y_hat = hypothesis(xs[0]);
        model.setBasis(model.getBasis() + 0.1);
        y_hat_2 = hypothesis(xs[0]);
        double basis_rate = (y_hat_2 - y_hat) / 0.1; // basis 변화량 always: 1
        System.out.println("basis rate = " + basis_rate);

        double error = ys[0] - y_hat;
        double new_weight = model.getWeight() + weight_rate * error;
        double new_basis = model.getBasis() + error;
        System.out.println("updated weight : " + new_weight);
        System.out.println("updated basis : " + new_basis);

        */

        // ㄹㅇ 알고리즘
        double[][] madeValue = make_linear(9, 3);
        double[] xs = madeValue[0];
        double[] ys = madeValue[1];

        for (int i = 0; i < xs.length; i++) {
            double error = ys[i] - hypothesis(xs[i]);
            model.setWeight(model.getWeight() + xs[i] * error);
            model.setBasis(model.getBasis() + error);
            System.out.println("debug");
        }

        System.out.println("weight : " + (Math.round(model.getWeight() * 1000)) / 1000.0);
        System.out.println("basis : " + (Math.round(model.getBasis() * 100)) / 100.0);
    }

    static double hypothesis(double x) {
        return model.getWeight() * x + model.getBasis();
    }

    static double[][] make_linear(double weight, double basis) {

        double[][] returnValue = new double[2][50];
//        double[] noise = new double[51];
//        for (int i = 0; i < noise.length; i++) {
//            noise[i] = random.nextDouble() * 0.1;
//        }

        for (int i = 0; i < returnValue[0].length; i++) {
//            returnValue[0][i] = random.nextInt(5);
//            returnValue[0][i] = i / 10000.0;
            returnValue[0][i] = random.nextDouble();
            returnValue[1][i] = returnValue[0][i] * weight+ basis;
//            returnValue[1][i] = returnValue[0][i] * weight+ basis + noise[i];
//            returnValue[1][i] = returnValue[0][i] * 0.3 + 5;
        }
        return returnValue;
    }
}
