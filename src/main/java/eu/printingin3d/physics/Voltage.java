package eu.printingin3d.physics;

import eu.printingin3d.utils.DoubleFormat;

public class Voltage extends BasicOperations<Voltage> {

    public Voltage(double value) {
        super(value);
    }

    @Override
    protected Voltage convert(double value) {
        return new Voltage(value);
    }
    
    @Override
    public String toString() {
        return DoubleFormat.formatWithSiPrefixes(value)+'V';
    }
}
