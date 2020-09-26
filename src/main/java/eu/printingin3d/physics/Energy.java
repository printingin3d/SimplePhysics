package eu.printingin3d.physics;

import eu.printingin3d.utils.DoubleFormat;


public class Energy extends BasicOperations<Energy> {
	public static final Energy ZERO = new Energy(0.0);

	public static Energy getEnergyDifference(Temperature from, Temperature to, HeatInertia heatInertia) {
		return fromJoule((to.getKelvin()-from.getKelvin())*heatInertia.getValue());
	}
	
	public static Energy fromJoule(double value) {
	    return Math.abs(value)<1e-5 ? ZERO : new Energy(value);
	}
	
	public static Energy fromWattHour(double value) {
	    return fromJoule(value*3600.0);
	}
	
	private Energy(double energy) {
		super(energy);
	}

	@Override
	protected Energy convert(double value) {
		return fromJoule(value);
	}
	
	public Energy(Temperature temp, HeatInertia heatInertia) {
		super(temp.getKelvin()*heatInertia.getValue());
	}
	
	public Energy(Power power, Time time) {
		super(power.getValue()*time.div(Time.SECOND));
	}
	
	public Power getPower(Time time) {
		return new Power(this.value / time.div(Time.SECOND));
	}
	
	public Temperature toTemperature(HeatInertia heatInertia) {
		return Temperature.fromKelvin(value/heatInertia.getValue());
	}
	
	@Override
	public String toString() {
		return DoubleFormat.formatWithSiPrefixes(value/3600.0)+"Wh";
//		return DoubleFormat.formatWithSiPrefixes(value)+'J';
	}
}
