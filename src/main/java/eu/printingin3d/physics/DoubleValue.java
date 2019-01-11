package eu.printingin3d.physics;

public abstract class DoubleValue implements Comparable<DoubleValue> {
	protected final double value;

	public static double readValue(DoubleValue dv) {
		return dv.getValue();
	}
	
	public DoubleValue(double value) {
		if (Double.isNaN(value)) {
			throw new NumberFormatException();
		}
		
		this.value = value;
	}
	
	protected double getValue() {
		return value;
	}

	@Override
	public int compareTo(DoubleValue o) {
		return Double.compare(this.value, o.value);
	}
}
