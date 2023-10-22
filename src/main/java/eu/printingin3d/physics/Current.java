package eu.printingin3d.physics;

import java.math.BigDecimal;

import eu.printingin3d.utils.DoubleFormat;

public class Current extends BasicOperations<Current> {

    public Current(BigDecimal value) {
        super(value);
    }

    @Override
    protected Current convert(BigDecimal value) {
        return new Current(value);
    }
    
    @Override
    public String toString() {
        return DoubleFormat.formatWithSiPrefixes(value)+'A';
    }
}
