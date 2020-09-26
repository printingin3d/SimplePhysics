package eu.printingin3d.physics;

public class Pressure extends DoubleValue {
    public Pressure(double value) {
        super(value);
    }

    @Override
    public String toString() {
        return getValue() + " hPa";
    }
}
