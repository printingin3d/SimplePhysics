package eu.printingin3d.physics;

import java.math.BigDecimal;
import java.math.RoundingMode;

import eu.printingin3d.utils.DoubleFormat;

public final class Speed extends BasicOperations<Speed> {
    public static final Speed ZERO = new Speed(BigDecimal.ZERO);

    public static Speed fromMeterPerSec(BigDecimal value) {
        return new Speed(value.setScale(4));
    }
    
    public static Speed fromKmPerHour(BigDecimal value) {
        return fromMeterPerSec(value.divide(new BigDecimal("3.6"), 4, RoundingMode.HALF_DOWN));
    }
    
    private Speed(BigDecimal value) {
        super(value);
    }

    @Override
    protected Speed convert(BigDecimal value) {
        return fromMeterPerSec(value);
    }

    @Override
    public String toString() {
        return DoubleFormat.formatWithSiPrefixes(value, 2)+"m/s";
    }

}
