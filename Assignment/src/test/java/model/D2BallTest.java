package model;

import model.D2.D2JPoint;
import model.D2.D2LinearFunction;
import org.junit.jupiter.api.Test;

class D2BallTest {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";



    @Test
    void 계산기() {
        // 기준
        System.out.println(2 * 7.554564545465);
    }
    /**
     * 입사 식:
     * 		y = ix + inter
     * 	벽 식:
     * 		y = iwx + interw
     * 	점 두 개 :
     * 		(a, a * i + inter)
     * 		(b, b * i + inter)
     *
     * 	ka = (a + ((a * i + inter) – interw) * iw) / (1 + iw * iw)
     * 	kb = (b + ((b * i + inter) – interw) * iw) / (1 + iw * iw)
     *
     * 	샘플 점 두 개:
     * 		(2 * ka – a, 2 * ka * iw – a * i + inter + 2 * interw)
     * 		(2 * kb – b, 2 * kb * iw – b * i + inter + 2 * interw)
     *
     *
     * 반사 식:
     *
     * 	inclinationR =
     * {(2 * ka * iw – a * i + inter + 2 * interw) - (2 * kb * iw – b * i + inter + 2 * interw)} /
     * (2 * ka – a) - (2 * kb – b)}
     * 	y_interceptR =
     * (2 * ka * iw – a * i + inter + 2 * interw) – inclinationR * (2 * ka – a)
     *
     * 	y =  inclinationR * x + y_interceptR
     *
     * 입력: 입사식, 벽식
     * return : inclinationR, y_interceptR
     */
    D2LinearFunction returnFunctionOfReflection(D2LinearFunction incidence, D2LinearFunction wall) {

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

        double ka = (a.getY() - wall.getY_intercept()) * wall.getInclination()
                / (1 + Math.pow(wall.getInclination(), 2));
        double kb = (b.getX() + (b.getY() - wall.getY_intercept()) * wall.getInclination())
                / (1 + Math.pow(wall.getInclination(), 2));

        D2JPoint aSample = new D2JPoint();
        D2JPoint bSample = new D2JPoint();
        aSample.setX(2 * ka);
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

    @Test
    void returnFunctionOfReflectionTest() {
        D2LinearFunction incidence = new D2LinearFunction();
        D2LinearFunction wall = new D2LinearFunction();
        wall.setInclination(1/4f);
        wall.setY_intercept(0);
        incidence.setInclination(1/2f);
        incidence.setY_intercept(0);
        D2LinearFunction d2LinearFunction = returnFunctionOfReflection(incidence, wall);
        System.out.println("inclination : " + d2LinearFunction.getInclination());
        System.out.println("y_intercept : " + d2LinearFunction.getY_intercept());
    }

    @Test
    void 각도실험() {
        int degreeWithInclination = getDegreeWithInclination(3);
        System.out.println(degreeWithInclination);
        System.out.println(getInclinationWithDegree(degreeWithInclination * 2));
    }



    @Test
    void 영번벽 () {
        // 0번 벽의 기울기가
        // 기울기는 -5이라고 가정
        double 영번벽WallsInclination = -0.1;
        double a = getDegreeWithInclination(-1 / 영번벽WallsInclination) + 180;
        System.out.println("a = " + a);
        System.out.println();
    }

    @Test
    void 일번벽 () {
        // 0번 벽의 기울기가
        // 기울기는 1/2이라고 가정
        double 일번벽WallsInclination = 1/2.0;
        int middle = getDegreeWithInclination(-1 / 일번벽WallsInclination);
        System.out.println(middle);
        int zone1 = middle - 45 > 0 ? middle - 45 : 360 + middle - 45;
        int zone2 = middle + 45 < 360 ? middle + 45 : middle + 45 - 360;
        System.out.println(zone1 + " < x < " + zone2);
    }

    @Test
    void 이번벽 () {
        // 0번 벽의 기울기가
        // 기울기는 -5이라고 가정
        double 이번벽WallsInclination = -10;
        int middle = getDegreeWithInclination(-1 / 이번벽WallsInclination);
        System.out.println(middle);
        int zone1 = middle - 45 > 0 ? middle - 45 : 360 + middle - 45;
        int zone2 = middle + 45 < 360 ? middle + 45 : middle + 45 - 360;
        System.out.println(zone1 + " < x < " + zone2);
    }

    @Test
    void 삼번벽 () {
        // 3번 벽의 기울기가
        // 기울기는 5이라고 가정
        double 삼번벽Inclination = 1;
        int middle = getDegreeWithInclination(-1 / 삼번벽Inclination) + 180;
        System.out.println(middle);
        int zone1 = middle - 45 > 0 ? middle - 45 : 360 + middle - 45;
        int zone2 = middle + 45 < 360 ? middle + 45 : middle + 45 - 360;
        System.out.println(zone1 + " < x < " + zone2);
    }

    private int getDegreeWithInclination(double inclination) {
        int degree = 90 - (int) Math.round(Math.atan(inclination) * 57.2958);
        if (degree == 360) {
            return 0;
        }
        if (true) {
            return degree;
        } else {
            return degree + 180;
        }
    }

    private double getInclinationWithDegree(int degree) {
        return Math.tan((90 - degree) / 57.2958);
    }
}