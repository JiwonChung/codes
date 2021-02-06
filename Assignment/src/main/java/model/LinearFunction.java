package model;

public class LinearFunction {
    private double inclination;
    private double y_intercept;
    private boolean direction;

    public LinearFunction() {
        this(0, 0);
    }

    public LinearFunction(double inclination, double y_intercept) {
        this.inclination = inclination;
        this.y_intercept = y_intercept;
    }

    public double getInclination() {
        return inclination;
    }

    public void setInclination(double inclination) {
        this.inclination = inclination;
    }

    public double getY_intercept() {
        return y_intercept;
    }

    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public void setY_intercept(double y_intercept) {
        this.y_intercept = y_intercept;
    }
}
