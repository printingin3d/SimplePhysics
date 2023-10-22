package eu.printingin3d.physics;

import java.math.BigDecimal;

import eu.printingin3d.utils.DoubleFormat;

public class HeatInertia extends DoubleValue {
	private static final BigDecimal AIR_HEAT_CAPACITY = new BigDecimal(1012);  // J/kg/K
	private static final BigDecimal AIR_DENSITY = new BigDecimal("1.1843");      // kg/m3
	private static final BigDecimal AIR_VOL_HEAT_CAPACITY = AIR_HEAT_CAPACITY.multiply(AIR_DENSITY);   // J/m3/K

	/**
	 * 
	 * @param inertia the heat inertia in J/K
	 */
	public HeatInertia(BigDecimal inertia) {
		super(inertia);
	}
	
	public static HeatInertia airInertia(Volume volume) {
		return new HeatInertia(volume.getValue().multiply(AIR_VOL_HEAT_CAPACITY));
	}
	
	@Override
	public String toString() {
		return DoubleFormat.formatWithSiPrefixes(value)+"J/K";
	}
}
