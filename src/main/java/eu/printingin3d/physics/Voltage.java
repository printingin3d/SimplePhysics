package eu.printingin3d.physics;

import java.math.BigDecimal;

import eu.printingin3d.utils.DoubleFormat;

public class Voltage extends BasicOperations<Voltage> {

    public Voltage(BigDecimal value) {
        super(value);
    }

    @Override
    protected Voltage convert(BigDecimal value) {
        return new Voltage(value);
    }
    
    @Override
    public String toString() {
        return DoubleFormat.formatWithSiPrefixes(value)+'V';
    }
}
