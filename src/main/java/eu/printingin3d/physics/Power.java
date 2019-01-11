package eu.printingin3d.physics;

import eu.printingin3d.utils.DoubleFormat;


public class Power extends BasicOperations<Power> {
 // W/m2 - 1kW/m2 on the surface of the Earth, but at least 20% is lost because of the window
	private static final double SUN_ENERGY = 800;  

	public static final Power ZERO = new Power(0.0);
		
	public static Power sunPower(double area) {
		return new Power(SUN_ENERGY * area);
	}
	
	public static Power heatingRequired(Temperature outside, Temperature inside, HeatingParam heatingParam) {
		 return new Power((outside.getKelvin()-inside.getKelvin())*heatingParam.getValue());
	}
	
	public static Power getHeatingPowerOfAirFlow(Temperature temperature, VolumeFlowRate volume) {
		return new Energy(temperature, HeatInertia.airInertia(volume.getVolumeOfTime(Time.HOUR))).getPower(Time.HOUR);
	}

	@Override
	protected Power convert(double value) {
		return new Power(value);
	}

	public Power(double power) {
		super(power);
	}
	
	@Override
	public String toString() {
		return DoubleFormat.formatWithSiPrefixes(value)+'W';
	}
}
