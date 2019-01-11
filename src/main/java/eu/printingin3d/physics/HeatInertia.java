package eu.printingin3d.physics;

import eu.printingin3d.utils.DoubleFormat;

public class HeatInertia extends DoubleValue {
	private static final double AIR_HEAT_CAPACITY = 1012.0;  // J/kg/K
	private static final double AIR_DENSITY =  1.1843;      // kg/m3
	private static final double AIR_VOL_HEAT_CAPACITY = AIR_HEAT_CAPACITY * AIR_DENSITY;   // J/m3/K

	/**
	 * 
	 * @param inertia the heat inertia in J/K
	 */
	public HeatInertia(double inertia) {
		super(inertia);
	}
	
	public static HeatInertia airInertia(Volume volume) {
		return new HeatInertia(volume.getValue()*AIR_VOL_HEAT_CAPACITY);
	}
	
	@Override
	public String toString() {
		return DoubleFormat.formatWithSiPrefixes(value)+"J/K";
	}
}
