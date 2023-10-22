package eu.printingin3d.physics;

import java.math.BigDecimal;

import eu.printingin3d.utils.DoubleFormat;

public class Frequency extends BasicOperations<Frequency> {

    public Frequency(BigDecimal value) {
        super(value);
    }

    @Override
    protected Frequency convert(BigDecimal value) {
        return new Frequency(value);
    }

    @Override
    public String toString() {
        return DoubleFormat.formatWithSiPrefixes(value, 2)+"Hz";
    }
}
