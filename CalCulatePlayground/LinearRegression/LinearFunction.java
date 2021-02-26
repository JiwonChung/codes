
public class LinearFunction {
    private double weight;
    private double basis;

    public LinearFunction(double weight, double basis) {
        this.weight = weight;
        this.basis = basis;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setBasis(double basis) {
        this.basis = basis;
    }

    public double getWeight() {
        return weight;
    }

    public double getBasis() {
        return basis;
    }
}