package eu.printingin3d.physics;

import eu.printingin3d.utils.DoubleFormat;

public class Current extends BasicOperations<Current> {

    public Current(double value) {
        super(value);
    }

    @Override
    protected Current convert(double value) {
        return new Current(value);
    }
    
    @Override
    public String toString() {
        return DoubleFormat.formatWithSiPrefixes(value)+'A';
    }
}
