package eu.printingin3d.physics;

public class Weight extends BasicOperations<Weight> {

	public Weight(double value) {
		super(value);
	}

	@Override
	protected Weight convert(double value) {
		return new Weight(value);
	}

	@Override
	public String toString() {
		return String.format("%.2f", Double.valueOf(100*value))+"kg";
	}
}
