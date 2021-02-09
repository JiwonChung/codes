package view.D2;

import model.*;
import model.D2.D2Ball;
import model.D2.D2JPoint;
import model.D2.D2LinearFunction;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class D2Panel extends JPanel {

    // 계기판 1의 객체
    final private Speedometer speedometer = new Speedometer();
    // 공 객체 ()
    final private D2Ball d2Ball = new D2Ball();

    // 벽의
    int[] xPoints = new int[4];
    int[] yPoints = new int[4];
    double[] inclinations = new double[4];
    int[] y_intercepts = new int[4];

    // 박은 벽
    byte wallNum = -1;
    byte lastTouchedWall = -1;
    /*
    1  0
    2  3
     */

    Map<Integer, Double> map = new HashMap<>();
    int displayedSpeed = 10;

    // 생성자 입니다.
    public D2Panel() {
        setLayout(null);

        for (int i = 10; i <= 1000; i+=10) {
            map.put(i, (Math.log10(1000.0 / i) / Math.log10(2)) + 1);
        }

        speedometer.setX1(WIDTH / 2 - 250);
        speedometer.setY1(HEIGHT / 2 - 260);
        speedometer.setX2(WIDTH / 2 + 250);
        speedometer.setY2(HEIGHT / 2 - 350);
        speedometer.setX3(WIDTH / 2 + 250);
        speedometer.setY3(HEIGHT / 2 - 260);

        d2Ball.setFake_x(250);
        d2Ball.setFake_y(250);
        d2Ball.setSpeed(map.get(10));
        d2Ball.setInclination(1);
        d2Ball.setDirectionFlag(true);

        Random random = new Random();
        // Frame.WIDTH / 2 - 200 <= x1 <= Frame.WIDTH / 2 + 200
        // y1 = Frame.HEIGHT / 2 - 250
        xPoints[0] = random.nextInt(400) + WIDTH / 2 - 200;
        yPoints[0] = HEIGHT / 2 - 250;

        // x2 = Frame.WIDTH / 2 - 250
        // Frame.HEIGHT / 2 - 200 <= y2 <= Frame.HEIGHT / 2 + 200
        xPoints[1] = WIDTH / 2 - 250;
        yPoints[1] = random.nextInt(400) + HEIGHT / 2 - 200;

        // Frame.WIDTH / 2 - 200 <= x3 <= FRAME.WIDTH / 2 + 200
        // y3 = Frame.HEIGHT / 2 + 250
        xPoints[2] = random.nextInt(400) + WIDTH / 2 - 200;
        yPoints[2] = HEIGHT / 2 + 250;

        // x4 = Frame.WIDTH / 2 + 250
        // Frame.HEIGHT / 2 - 200 <= y4 <= Frame.HEIGHT / 2 + 200
        xPoints[3] = WIDTH / 2 + 250;
        yPoints[3] = random.nextInt(400) + HEIGHT / 2 - 200;

        inclinations[0] = - (float) (yPoints[0] - yPoints[3]) / (xPoints[0] - xPoints[3]);
        inclinations[1] = - (float) (yPoints[0] - yPoints[1]) / (xPoints[0] - xPoints[1]);
        inclinations[2] = - (float) (yPoints[1] - yPoints[2]) / (xPoints[1] - xPoints[2]);
        inclinations[3] = - (float) (yPoints[2] - yPoints[3]) / (xPoints[2] - xPoints[3]);

        y_intercepts[0] = (int) ((HEIGHT / 2 + 250 - yPoints[0]) - inclinations[0] * (xPoints[0] - WIDTH / 2 + 250));
        y_intercepts[1] = (int) ((HEIGHT / 2 + 250 - yPoints[1]) - inclinations[1] * (xPoints[1] - WIDTH / 2 + 250));
        y_intercepts[2] = (int) ((HEIGHT / 2 + 250 - yPoints[2]) - inclinations[2] * (xPoints[2] - WIDTH / 2 + 250));
        y_intercepts[3] = (int) ((HEIGHT / 2 + 250 - yPoints[3]) - inclinations[3] * (xPoints[3] - WIDTH / 2 + 250));

        new SpeedAdjuster().start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        g.setColor(new Color(49, 51, 53));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.ORANGE);
        g.setFont(new Font("Times New Roman", Font.BOLD, 35));

        g.drawString(d2Ball.getFake_x() + "", WIDTH / 2, HEIGHT / 2 + 300);
        g.drawString(d2Ball.getFake_y() + "", WIDTH / 2 - 400, HEIGHT / 2);

        // 게이지는 ball.speed 값(1 ~ 100)을 0~500 사이의 정수로 만드는 것입니다.
        int gauge = displayedSpeed / 2;
        g.drawString("Speed : " + gauge * 2 + " [cm/sec]", speedometer.getX1() - 200, speedometer.getY2());

        g.setColor(new Color(255, 255 - gauge / 2, 0));
        g.drawLine(speedometer.getX1(), speedometer.getY1(), speedometer.getX2(), speedometer.getY2());
        g.drawLine(speedometer.getX2(), speedometer.getY2(), speedometer.getX3(), speedometer.getY3());
        g.drawLine(speedometer.getX3(), speedometer.getY3(), speedometer.getX1(), speedometer.getY1());



        int[] xs = new int[3];
        int[] ys = new int[3];
        xs[0] = speedometer.getX1();
        ys[0] = speedometer.getY1();
        xs[1] = speedometer.getX1() + gauge;
        ys[1] = speedometer.getY1();
        xs[2] = xs[1];
        ys[2] = Math.round((-9 / 50f) * gauge) + speedometer.getY2() + 90;
        g.fillPolygon(new Polygon(xs, ys, 3));

        g.drawOval(speedometer.getX2() + 50, speedometer.getY2() - 30, 180, 180);
        int degreeForRealSpeedometer = (gauge / 2f) >= 125.0 ? gauge / 2 - 125 : 235 + gauge / 2;
        Point point = returnCirclePoint(new Point(speedometer.getX2() + 50 + 90, speedometer.getY2() - 30 + 90),
                degreeForRealSpeedometer, 90);
        g.drawLine(point.x, point.y, speedometer.getX2() + 50 + 90, speedometer.getY2() - 30 + 90);

        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g.drawString(gauge * 2 + "", speedometer.getX2() + 50 + 90 - 15, speedometer.getY2() - 30 + 150);

        g.setFont(new Font("Times New Roman", Font.ITALIC, 12));
        g.setColor(new Color(225, 0, 0));
        g.drawString(1000 + "", speedometer.getX3() + 200, speedometer.getY3() + 50);
        g.setColor(new Color(225, 225, 0));
        g.drawString(10 + "", speedometer.getX3() + 50, speedometer.getY3() + 50);



        g.setColor(Color.WHITE);

        g.drawPolygon(xPoints, yPoints, 4);
        g.setColor(Color.GREEN);
        g.fillOval(d2Ball.get_x() - 10, d2Ball.get_y() - 10, 20, 20);
        staticThings();
    }

    public Point returnCirclePoint(Point centerOfCircle, int direction, int radius) {
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

    private void frameRateAdjuster() {
        try {
            Thread.sleep((int) d2Ball.getSpeed());
        } catch (Exception ignored) {}
        repaint();
    }

    private double getDegreeWithInclinationForWall(double inclination) {
        double degree = 90 - Math.round(Math.atan(inclination) * 57.2958);
        if (degree == 360) {
            return 0;
        }
        return degree;

    }

    private double getInclinationWithDegree(double degree) {
        return Math.tan((90 - degree) / 57.2958);
    }


    private boolean detected() {
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 4; i++) {
                int fake_x = d2Ball.getFake_x();
                int fake_y = d2Ball.getFake_y();
                double d = Math.abs((-inclinations[i]) * fake_x + fake_y - y_intercepts[i]) /
                        Math.sqrt(Math.pow(inclinations[i], 2) + 1);
                if (d <= 10) {
                    wallNum = (byte) i;
                    return true;
                }
            }
        }
        return false;
    }


    /**
     *      *    입사 식:
     *      * 		y = ix + inter
     *
     *      * 	벽 식:
     *      * 		y = iwx + interw
     *
     *      * 	점 두 개 :
     *      * 		(a, a * i + inter)
     *      * 		(b, b * i + inter)
     *      *
     *      * 	ka = (a + ((a * i + inter) – interw) * iw) / (1 + iw * iw)
     *      * 	kb = (b + ((b * i + inter) – interw) * iw) / (1 + iw * iw)
     *      *
     *      * 	샘플 점 두 개:
     *      * 		(2 * ka – a, 2 * ka * iw – a * i + inter + 2 * interw)
     *      * 		(2 * kb – b, 2 * kb * iw – b * i + inter + 2 * interw)
     *      *
     *      *
     *      * 반사 식:
     *      *
     *      * 	inclinationR =
     *      * {(2 * ka * iw – a * i + inter + 2 * interw) - (2 * kb * iw – b * i + inter + 2 * interw)} /
     *      * (2 * ka – a) - (2 * kb – b)}
     *      * 	y_interceptR =
     *      * (2 * ka * iw – a * i + inter + 2 * interw) – inclinationR * (2 * ka – a)
     *      *
     *      * 	y =  inclinationR * x + y_interceptR
     *      *
     *      * 입력: 입사식, 벽식
     *      * return : inclinationR, y_interceptR
     */
    public D2LinearFunction returnFunctionOfReflection(D2LinearFunction incidence, D2LinearFunction wall) {
        wall.setY_intercept(d2Ball.getFake_y() - wall.getInclination() * d2Ball.getFake_x());
        if (incidence.getInclination() == 0) {
            incidence.setInclination(0.00001);
        }
        if (wall.getInclination() == 0) {
            wall.setInclination(0.00001);
        }
        D2JPoint a = new D2JPoint();
        D2JPoint b = new D2JPoint();
        a.setX(0);
        a.setY(incidence.getY_intercept());
        b.setX(5);
        b.setY(5 * incidence.getInclination() + incidence.getY_intercept());

        double ka = (a.getX() + (a.getY() - wall.getY_intercept()) * wall.getInclination())
                / (1 + Math.pow(wall.getInclination(), 2));
        double kb = (b.getX() + (b.getY() - wall.getY_intercept()) * wall.getInclination())
                / (1 + Math.pow(wall.getInclination(), 2));

        D2JPoint aSample = new D2JPoint();
        D2JPoint bSample = new D2JPoint();
        aSample.setX(2 * ka - a.getX());
        aSample.setY(2 * ka * wall.getInclination() - a.getY() + 2 * wall.getY_intercept());
        bSample.setX(2 * kb - b.getX());
        bSample.setY(2 * kb * wall.getInclination() - b.getY() + 2 * wall.getY_intercept());

        double inclination = (aSample.getY() - bSample.getY()) / (aSample.getX() - bSample.getX());
        if (Math.abs(inclination) > 1000) {
            if (inclination > 0) {
                inclination = 1000;
            } else {
                inclination = -1000;
            }
        }
        if (Math.abs(inclination) < 0.01) {
            inclination = 0;
        }

        D2LinearFunction returnValue = new D2LinearFunction();
        returnValue.setInclination(inclination);
        returnValue.setY_intercept(aSample.getY() - returnValue.getInclination() * aSample.getX());
        return returnValue;
    }

    // 벽에 터치했을 때의 로직
    private void wallTouch(int wallNum) {
        D2LinearFunction d2LinearFunction;
        switch (wallNum) {
            case 0:
                if (lastTouchedWall == 0) {
                    break;
                }
                d2LinearFunction = returnFunctionOfReflection(new D2LinearFunction(d2Ball.getInclination(), d2Ball.getY_intercept()),
                        new D2LinearFunction(inclinations[0], y_intercepts[0]));
                if (inclinations[0] < -1.0) {
                    D2LinearFunction tmp = returnFunctionOfReflection(new D2LinearFunction(1000, 0),
                            new D2LinearFunction(inclinations[0], 0));
                    double tt;
                    if ((tt = tmp.getInclination()) > 1000) {
                        tt = 999;
                    }
                    if (!(d2Ball.getInclination() <= tt)) {
                        d2Ball.setDirectionFlag(!d2Ball.isDirectionFlag());
                    }
                } else {
                    double at = getDegreeWithInclinationForWall(-1.0 / inclinations[0]) * 2;
                    if (getInclinationWithDegree(at) < d2Ball.getInclination()) {
                        d2Ball.setDirectionFlag(!d2Ball.isDirectionFlag());
                    }
                }
                d2Ball.setInclination(d2LinearFunction.getInclination());
                d2Ball.setY_intercept(d2LinearFunction.getY_intercept());
                break;
            case 1:
                if (lastTouchedWall == 1) {
                    break;
                }
                d2LinearFunction = returnFunctionOfReflection(new D2LinearFunction(d2Ball.getInclination(), d2Ball.getY_intercept()),
                        new D2LinearFunction(inclinations[1], y_intercepts[1]));
                if (inclinations[1] > 1.0) {
                    D2LinearFunction tmp = returnFunctionOfReflection(new D2LinearFunction(1000, 0),
                            new D2LinearFunction(inclinations[1], 0));
                    double tt;
                    if ((tt = tmp.getInclination()) > 1000) {
                        tt = 999;
                    }
                    if (!((d2Ball.getInclination() > tt))) {
                        d2Ball.setDirectionFlag(!d2Ball.isDirectionFlag());
                    }
                } else {
                    double at = getDegreeWithInclinationForWall(-1.0 / inclinations[1]);
                    at = at - (180 - at);
                    if (getInclinationWithDegree(at) > d2Ball.getInclination()) {
                        d2Ball.setDirectionFlag(!d2Ball.isDirectionFlag());
                    }
                }
                d2Ball.setInclination(d2LinearFunction.getInclination());
                d2Ball.setY_intercept(d2LinearFunction.getY_intercept());
                break;
            case 2:
                if (lastTouchedWall == 2) {
                    break;
                }
                d2LinearFunction = returnFunctionOfReflection(new D2LinearFunction(d2Ball.getInclination(), d2Ball.getY_intercept()),
                        new D2LinearFunction(inclinations[2], y_intercepts[2]));
                if (inclinations[2] < -1.0) {
                    D2LinearFunction tmp = returnFunctionOfReflection(new D2LinearFunction(1000, 0),
                            new D2LinearFunction(inclinations[2], 0));
                    double tt;
                    if ((tt = tmp.getInclination()) > 100) {
                        tt = 999;
                    }
                    if (!(tt > d2Ball.getInclination())) {
                        d2Ball.setDirectionFlag(!d2Ball.isDirectionFlag());
                    }
                } else {
                    double at = getDegreeWithInclinationForWall(-1.0 / inclinations[2]) * 2;
                    if ((getInclinationWithDegree(at) < d2Ball.getInclination())) {
                        d2Ball.setDirectionFlag(!d2Ball.isDirectionFlag());
                    }
                }
                d2Ball.setInclination(d2LinearFunction.getInclination());
                d2Ball.setY_intercept(d2LinearFunction.getY_intercept());
                break;
            case 3:
                if (lastTouchedWall == 3) {
                    break;
                }
                d2LinearFunction = returnFunctionOfReflection(new D2LinearFunction(d2Ball.getInclination(), d2Ball.getY_intercept()),
                        new D2LinearFunction(inclinations[3], y_intercepts[3]));
                if (inclinations[3] > 1.0) {
                    D2LinearFunction tmp = returnFunctionOfReflection(new D2LinearFunction(1000, 0),
                            new D2LinearFunction(inclinations[3], 0));
                    double tt;
                    if ((tt = tmp.getInclination()) > 100) {
                        tt = 999;
                    }
                    if (!(d2Ball.getInclination() > tt)) {
                        d2Ball.setDirectionFlag(!d2Ball.isDirectionFlag());
                    }
                } else {
                    double at = getDegreeWithInclinationForWall(-1.0 / inclinations[3]);
                    at = at - (180 - at);
                    if ((getInclinationWithDegree(at) > d2Ball.getInclination())) {
                        d2Ball.setDirectionFlag(!d2Ball.isDirectionFlag());
                    }
                }
                d2Ball.setInclination(d2LinearFunction.getInclination());
                d2Ball.setY_intercept(d2LinearFunction.getY_intercept());
                break;
        }
    }

    // 정적인 것들의 holdings
    private void staticThings() {
        if (detected()) {
            wallTouch(wallNum);
            lastTouchedWall = wallNum;
        }
        d2Ball.move_x();
        frameRateAdjuster();
    }

    private class SpeedAdjuster extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5000);
                    for (int i = 10; i < 200; i += 10) {
                        displayedSpeed = i;
                        d2Ball.setSpeed(map.get(i));
                        Thread.sleep(30);
                    }
                    for (int i = 200; i < 400; i += 10) {
                        displayedSpeed = i;
                        d2Ball.setSpeed(map.get(i));
                        Thread.sleep(50);
                    }
                    for (int i = 400; i < 700; i += 10) {
                        displayedSpeed = i;
                        d2Ball.setSpeed(map.get(i));
                        Thread.sleep(100);
                    }
                    for (int i = 700; i <= 1000; i += 10) {
                        displayedSpeed = i;
                        d2Ball.setSpeed(map.get(i));
                        Thread.sleep(200);
                    }
                    Thread.sleep(5000);
                    for (int i = 1000; i >= 10; i -= 10) {
                        displayedSpeed = i;
                        d2Ball.setSpeed(map.get(i));
                        Thread.sleep(50);
                    }
                } catch (InterruptedException e) {}
            }
        }
    }
}
