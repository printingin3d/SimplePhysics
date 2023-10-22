package eu.printingin3d.physics;

import java.math.BigDecimal;

import eu.printingin3d.utils.DoubleFormat;


public class Power extends BasicOperations<Power> {
 // W/m2 - 1kW/m2 on the surface of the Earth, but at least 20% is lost because of the window
	private static final BigDecimal SUN_ENERGY = new BigDecimal(800);  

	public static final Power ZERO = new Power(BigDecimal.ZERO);
		
	public static Power sunPower(BigDecimal area) {
		return new Power(SUN_ENERGY.multiply(area));
	}
	
	public static Power heatingRequired(Temperature outside, Temperature inside, HeatingParam heatingParam) {
		 return new Power((outside.getKelvin().subtract(inside.getKelvin())).multiply(heatingParam.getValue()));
	}
	
	public static Power getHeatingPowerOfAirFlow(Temperature temperature, VolumeFlowRate volume) {
		return new Energy(temperature, HeatInertia.airInertia(volume.getVolumeOfTime(Time.HOUR))).getPower(Time.HOUR);
	}

	@Override
	protected Power convert(BigDecimal value) {
		return new Power(value);
	}

	public Power(BigDecimal power) {
		super(power);
	}
	
	@Override
	public String toString() {
		return DoubleFormat.formatWithSiPrefixes(value)+'W';
	}
}
