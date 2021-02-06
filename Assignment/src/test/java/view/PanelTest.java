package view;

import model.Ball;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

class PanelTest {
    Ball ball = new Ball();
    float[] inclinations = new float[4];
    int[] y_intercepts = new int[4];
    int[] xPoints = new int[4];
    int[] yPoints = new int[4];
    @Test
    void 아크탄젠트실험() {
        float inclination = -99999f;
        boolean directionFlag = false;
        int tmp = (int) Math.round(90 - Math.atan(inclination) * 57.2958);
        if (directionFlag)
            System.out.println(tmp);
        else {
            tmp += 180;
        }
        if (tmp == 360) {
            tmp = 0;
        }
        System.out.println(tmp);
    }

    @Test
    void 테스트() {

        for (int i = 0; i < 10000; i++) {
            Assertions.assertThat(detected(30)).isFalse();
        }
//        System.out.println(detected(30));

    }
    private boolean detected(int direction) {

        Random random = new Random();
        // Frame.WIDTH / 2 - 200 <= x1 <= Frame.WIDTH / 2 + 200
        // y1 = Frame.HEIGHT / 2 - 250
        xPoints[0] = random.nextInt(400) + Frame.WIDTH / 2 - 200;
        yPoints[0] = Frame.HEIGHT / 2 - 250;

        // x2 = Frame.WIDTH / 2 - 250
        // Frame.HEIGHT / 2 - 200 <= y2 <= Frame.HEIGHT / 2 + 200
        xPoints[1] = Frame.WIDTH / 2 - 250;
        yPoints[1] = random.nextInt(400) + Frame.HEIGHT / 2 - 200;

        // Frame.WIDTH / 2 - 200 <= x3 <= FRAME.WIDTH / 2 + 200
        // y3 = Frame.HEIGHT / 2 + 250
        xPoints[2] = random.nextInt(400) + Frame.WIDTH / 2 - 200;
        yPoints[2] = Frame.HEIGHT / 2 + 250;

        // x4 = Frame.WIDTH / 2 + 250
        // Frame.HEIGHT / 2 - 200 <= y4 <= Frame.HEIGHT / 2 + 200
        xPoints[3] = Frame.WIDTH / 2 + 250;
        yPoints[3] = random.nextInt(400) + Frame.HEIGHT / 2 - 200;

        ball.setFake_x(250);
        ball.setFake_y(250);
        ball.setInclination(1);
        ball.setDirectionFlag(true);
        inclinations[0] = - (float) (yPoints[0] - yPoints[3]) / (xPoints[0] - xPoints[3]);
        inclinations[1] = - (float) (yPoints[0] - yPoints[1]) / (xPoints[0] - xPoints[1]);
        inclinations[2] = - (float) (yPoints[1] - yPoints[2]) / (xPoints[1] - xPoints[2]);
        inclinations[3] = - (float) (yPoints[2] - yPoints[3]) / (xPoints[2] - xPoints[3]);
        y_intercepts[0] = (int) ((Frame.HEIGHT / 2 + 250 - yPoints[0]) - inclinations[0] * (xPoints[0] - Frame.WIDTH / 2 + 250));
        y_intercepts[1] = (int) ((Frame.HEIGHT / 2 + 250 - yPoints[1]) - inclinations[1] * (xPoints[1] - Frame.WIDTH / 2 + 250));
        y_intercepts[2] = (int) ((Frame.HEIGHT / 2 + 250 - yPoints[2]) - inclinations[2] * (xPoints[2] - Frame.WIDTH / 2 + 250));
        y_intercepts[3] = (int) ((Frame.HEIGHT / 2 + 250 - yPoints[3]) - inclinations[3] * (xPoints[3] - Frame.WIDTH / 2 + 250));

        /*
        this._x = Frame.WIDTH / 2 - 250 + fake_x;
        this._y = Frame.HEIGHT / 2 + 250 - fake_y;
         */

        Point[] points = createDetectPoint(direction);

        System.out.println("inclinations: " + Arrays.toString(inclinations));
        System.out.println("y intercept: " + Arrays.toString(y_intercepts));

        for (Point point : points) {
            if (point.x <= (point.y - y_intercepts[1]) / inclinations[1]) {
                System.out.println();
                System.out.println("detected x point: " + (point.y - y_intercepts[1]) / inclinations[1]);
                System.out.println("detected y point: " + point.y);
                System.out.println(1);
                System.out.println(point.x+ " 여기입니다. " + point.y);
                return true;
            } else if (point.x <= (point.y - y_intercepts[2]) / inclinations[2]) {
                System.out.println();
                System.out.println("detected x point: " + (point.y - y_intercepts[2]) / inclinations[2]);
                System.out.println("detected y point: " + point.y);
                System.out.println(2);
                System.out.println(point.x+ " 여기입니다. " + point.y);
                return true;
            }
        }
        for (Point point : points) {
            if (point.x >= (point.y - y_intercepts[0]) / inclinations[0]) {
                System.out.println();
                System.out.println("detected x point: " + (point.y - y_intercepts[0]) / inclinations[0]);
                System.out.println("detected y point: " + point.y);
                System.out.println("0");
                System.out.println(point.x+ " 여기입니다. " + point.y);
                return true;
            } else if (point.x >= (point.y - y_intercepts[3]) / inclinations[3]) {
                System.out.println();
                System.out.println("detected x point: " + (point.y - y_intercepts[3]) / inclinations[3]);
                System.out.println("detected y point: " + point.y);
                System.out.println("3");
                System.out.println(point.x+ " 여기입니다. " + point.y);
                return true;
            }
        }

        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////");
        return false;
    }

    /**
     * will return detect Point with degree
     * the ball _x, _y are auto inject
     * @param direction Enter degree
     */
    private Point[] createDetectPoint(int direction) {
        int[] directions = new int[7];
        int tmp = direction + 90;
        Point[] returnValues = new Point[7];
        for (int i = 0; i < directions.length; i++) {
            directions[i] = tmp - 30 * i;
            if (directions[i] < 0) {
                directions[i] = 360 + directions[i];
            } else if (directions[i] == 360) {
                directions[i] = 0;
            }
            returnValues[i] = returnDetectPointOfCircle(new Point(ball.getFake_x(), ball.getFake_y()), directions[i], 10);
        }
        return returnValues;
    }


    /**
     * 원의 중심좌표와 각도와 지름이 주어졌을때 좌표 리턴
     * 0 <= direction < 360
     */
    public Point returnDetectPointOfCircle(Point centerOfCircle, int direction, int radius) {
        // 식: radius^2 = (x - centerOfCircle.getX)^2 + (y - centerOfCircle.getY)^2
        if (direction < 90) {
            direction = direction + 270;
        } else {
            direction = direction - 90;
        }

        int x = centerOfCircle.x + (int) Math.round(radius * Math.cos(direction / 57.2958));
        int y = centerOfCircle.y + (int) Math.round(radius * Math.sin(direction / 57.2958));

        return new Point(x, y);
    }





}