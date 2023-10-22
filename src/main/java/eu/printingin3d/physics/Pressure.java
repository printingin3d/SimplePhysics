package eu.printingin3d.physics;

import java.math.BigDecimal;

public class Pressure extends DoubleValue {
    public Pressure(BigDecimal value) {
        super(value);
    }

    @Override
    public String toString() {
        return getValue() + " hPa";
    }
}
