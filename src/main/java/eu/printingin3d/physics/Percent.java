package eu.printingin3d.physics;

public class Percent {
	public static final Percent ZERO = new Percent(0.0);
	public static final Percent HALF = new Percent(0.5);
	public static final Percent MAX = new Percent(1.0);
	
	private final double value;

	public Percent(double value) {
		if (value<0.0 || value>1.0) {
			throw new IllegalArgumentException("Value of a percent should be between 0 and 1");
		}
		
		this.value = value;
	}

	public double getValue() {
		return value;
	}
	
	public double getComplementerValue() {
		return 1.0-value;
	}

	@Override
	public String toString() {
		return String.format("%.2f", Double.valueOf(100*value))+"%";
	}
}
