package eu.printingin3d.physics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public final class Time extends BasicOperations<Time> {
	public static final Time ZERO      = new Time(BigDecimal.ZERO);
	public static final Time SECOND    = new Time(BigDecimal.ONE);
	public static final Time MINUTE    = new Time(new BigDecimal(60));
	public static final Time HALF_HOUR = new Time(new BigDecimal(1800));
	public static final Time HOUR      = new Time(new BigDecimal(3600));
	
	public static Time difference(LocalDateTime d1, LocalDateTime d2) {
	    long epochSecond1 = d1.toInstant(ZoneOffset.UTC).getEpochSecond();
		long epochSecond2 = d2.toInstant(ZoneOffset.UTC).getEpochSecond();
        return new Time(BigDecimal.valueOf(Math.abs(epochSecond2-epochSecond1)));
	}

	public static Time ofSecond(BigDecimal sec) {
	    return new Time(sec);
	}
	
	/**
	 * 
	 * @param time in seconds
	 */
	private Time(BigDecimal time) {
		super(time);
	}
	
	public BigDecimal div(Time t) {
		return value.divide(t.value, 4, RoundingMode.HALF_DOWN);
	}

	@Override
	public String toString() {
		long time = value.longValue();
		if (time<60) {
			return time+" sec";
		}
		if (time<3600) {
			return String.format("%.2f min", div(MINUTE));
		}
		return String.format("%.2f hour", div(HOUR));
	}

	@Override
	protected Time convert(BigDecimal value) {
		return new Time(value);
	}
	
	@Override
	protected BigDecimal getValue() {
		return div(Time.HOUR);
	}
}
