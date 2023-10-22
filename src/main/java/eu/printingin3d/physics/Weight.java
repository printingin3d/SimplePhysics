package eu.printingin3d.physics;

import java.math.BigDecimal;

import eu.printingin3d.utils.DoubleFormat;

public class Weight extends BasicOperations<Weight> {

	public Weight(BigDecimal value) {
		super(value);
	}

	@Override
	protected Weight convert(BigDecimal value) {
		return new Weight(value);
	}

	@Override
	public String toString() {
		return DoubleFormat.formatWithSiPrefixes(value.multiply(new BigDecimal(1000)), 2)+"g";
	}
}
