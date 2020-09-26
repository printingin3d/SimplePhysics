package eu.printingin3d.physics;

import eu.printingin3d.utils.DoubleFormat;

public final class Speed extends BasicOperations<Speed> {
    public static final Speed ZERO = new Speed(0.0);

    public static Speed fromMeterPerSec(double value) {
        return Math.abs(value)<1e-5 ? ZERO : new Speed(value);
    }
    
    public static Speed fromKmPerHour(double value) {
        return fromMeterPerSec(value/3.6);
    }
    
    private Speed(double value) {
        super(value);
    }

    @Override
    protected Speed convert(double value) {
        return fromMeterPerSec(value);
    }

    @Override
    public String toString() {
        return DoubleFormat.formatWithSiPrefixes(value, 2)+"m/s";
    }

}
