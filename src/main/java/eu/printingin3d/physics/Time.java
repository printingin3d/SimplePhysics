package eu.printingin3d.physics;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public final class Time extends BasicOperations<Time> {
	public static final Time ZERO      = new Time(0.0);
	public static final Time SECOND    = new Time(1.0);
	public static final Time HALF_HOUR = new Time(1800.0);
	public static final Time HOUR      = new Time(3600.0);
	
	public static Time difference(LocalDateTime d1, LocalDateTime d2) {
	    long epochSecond1 = d1.toInstant(ZoneOffset.UTC).getEpochSecond();
		long epochSecond2 = d2.toInstant(ZoneOffset.UTC).getEpochSecond();
        return new Time(Math.abs(epochSecond2-epochSecond1));
	}

	public static Time ofSecond(double sec) {
	    return new Time(sec);
	}
	
	/**
	 * 
	 * @param time in seconds
	 */
	private Time(double time) {
		super(time);
	}
	
	public double div(Time t) {
		return value /t.value;
	}

	@Override
	public String toString() {
		long time = Math.round(value);
		if (time<60) {
			return time+" sec";
		}
		if (time<3600) {
			return String.format("%.2f min", Double.valueOf(value/60));
		}
		return String.format("%.2f hour", Double.valueOf(value/3600));
	}

	@Override
	protected Time convert(double value) {
		return new Time(value);
	}
	
	@Override
	protected double getValue() {
		return div(Time.HOUR);
	}
}
