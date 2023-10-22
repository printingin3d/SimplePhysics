package eu.printingin3d.physics;

import java.math.BigDecimal;
import java.math.RoundingMode;

import eu.printingin3d.utils.DoubleFormat;


public class Energy extends BasicOperations<Energy> {
	public static final Energy ZERO = new Energy(BigDecimal.ZERO);

	public static Energy getEnergyDifference(Temperature from, Temperature to, HeatInertia heatInertia) {
		return fromJoule((to.getKelvin().subtract(from.getKelvin())).multiply(heatInertia.getValue()));
	}
	
	public static Energy fromJoule(BigDecimal value) {
	    return new Energy(value.setScale(4));
	}
	
	public static Energy fromWattHour(BigDecimal value) {
	    return fromJoule(value.multiply(Time.HOUR.getValue()));
	}
	
	private Energy(BigDecimal energy) {
		super(energy);
	}

	@Override
	protected Energy convert(BigDecimal value) {
		return fromJoule(value);
	}
	
	public Energy(Temperature temp, HeatInertia heatInertia) {
		super(temp.getKelvin().multiply(heatInertia.getValue()));
	}
	
	public Energy(Power power, Time time) {
		super(power.getValue().multiply(time.div(Time.SECOND)));
	}
	
	public Power getPower(Time time) {
		return new Power(this.value.divide(time.div(Time.SECOND), 4, RoundingMode.HALF_DOWN));
	}
	
	public Temperature toTemperature(HeatInertia heatInertia) {
		return Temperature.fromKelvin(value.divide(heatInertia.getValue(), 4, RoundingMode.HALF_DOWN));
	}
	
	public BigDecimal getValueAsWattHour() {
	    return value.divide(Time.HOUR.getValue(), 4, RoundingMode.HALF_DOWN);
	}
	
	public BigDecimal getValueAsJoule() {
	    return value;
	}
	
	@Override
	public String toString() {
		return DoubleFormat.formatWithSiPrefixes(getValueAsWattHour())+"Wh";
//		return DoubleFormat.formatWithSiPrefixes(value)+'J';
	}
}
