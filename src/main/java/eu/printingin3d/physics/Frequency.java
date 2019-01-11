package eu.printingin3d.physics;

import eu.printingin3d.utils.DoubleFormat;

public class Frequency extends BasicOperations<Frequency> {

    public Frequency(double value) {
        super(value);
    }

    @Override
    protected Frequency convert(double value) {
        return new Frequency(value);
    }

    @Override
    public String toString() {
        return DoubleFormat.formatWithSiPrefixes(value, 2)+"Hz";
    }
}
