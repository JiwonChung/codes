package model.D3;

public class D3Ball {
    private int fake_x, fake_y, fake_z;
    // if x value is increasing -> then directionFlag[0] is true
    // else false
    // if z value is increasing -> then directionFlag[1] is true
    // else false
    private boolean[] directionFlag = {true, true};
    private double[] inclinations;


    public int getFake_x() {
        return fake_x;
    }

    public void setFake_x(int fake_x) {
        this.fake_x = fake_x;
    }

    public int getFake_y() {
        return fake_y;
    }

    public void setFake_y(int fake_y) {
        this.fake_y = fake_y;
    }

    public int getFake_z() {
        return fake_z;
    }

    public void setFake_z(int fake_z) {
        this.fake_z = fake_z;
    }

    public void changeXDirection() {
        directionFlag[0] = !directionFlag[0];
    }

    public void ceilingTouch() {
        directionFlag[1] = !directionFlag[1];
    }

    public double getXYInclination() {
        return inclinations[0];
    }

    public double getXZInclination() {
        return inclinations[1];
    }

    public void setXYInclination(double xyInclination) {
        this.inclinations[0] = xyInclination;
    }

    public void setXZInclination(double xzInclination) {
        this.inclinations[1] = xzInclination;
    }

    public void setInclinations(double[] inclinations) {
        this.inclinations = inclinations;
    }

    public void move() {
        if (directionFlag[0]) {
            if (0.0 < inclinations[0] && inclinations[0] < 1.0) {
                fake_x++;
            } else if (1.0 < inclinations[0]) {
                fake_y++;
            } else if (-1.0 < inclinations[0] && inclinations[0] < 0) {
                fake_x++;
            } else if (inclinations[0] < -1.0) {
                fake_y--;
            }
        } else {
            if (0.0 < inclinations[0] && inclinations[0] < 1.0) {
                fake_x--;
            } else if (1.0 < inclinations[0]) {
                fake_y--;
            } else if (-1.0 < inclinations[0] && inclinations[0] < 0) {
                fake_x--;
            } else if (inclinations[0] < -1.0) {
                fake_y++;
            }
        }



    }


}
