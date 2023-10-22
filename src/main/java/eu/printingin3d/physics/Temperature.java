package eu.printingin3d.physics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class Temperature extends DoubleValue {
    private static final BigDecimal CELSIUS_TO_KELVIN = new BigDecimal("273.14");

	public static final Temperature CELSIUS_ZERO = fromCelsius(BigDecimal.ZERO);
	
	private static final Pattern CELSIUS_PATTERN = Pattern.compile("([0-9]+(\\.[0-9]+)?)C");
	private static final Pattern KELVIN_PATTERN = Pattern.compile("([0-9]+(\\.[0-9]+)?)K");
	
	private Temperature(BigDecimal kelvin) {
		super(kelvin);
	}
	
	public static Temperature weightedMean(Temperature temp1, BigDecimal weight1, 
	                                       Temperature temp2, BigDecimal weight2) {
		return new Temperature(temp1.value.multiply(weight1).add(temp2.value.multiply(weight2))
		        .divide(weight1.add(weight2), 4, RoundingMode.HALF_DOWN));
	}
	
	public static Temperature mean(Temperature... temps) {
	    BigDecimal sum = BigDecimal.ZERO;
		for (Temperature temp : temps) {
		    sum = sum.add(temp.value);
		}
		return new Temperature(sum.divide(new BigDecimal(temps.length), 4, RoundingMode.HALF_DOWN));
	}
	
	public static Temperature fromCelsius(BigDecimal celsius) {
		return new Temperature(convertTemperatureToKelvin(celsius));
	}
	
	public static Temperature fromKelvin(BigDecimal kelvin) {
		return new Temperature(kelvin);
	}
	
	public static Temperature fromString(String value) {
		Matcher m = CELSIUS_PATTERN.matcher(value);
		if (m.matches()) {
			return fromCelsius(new BigDecimal(m.group(1)));
		}
		m = KELVIN_PATTERN.matcher(value);
		if (m.matches()) {
			return fromKelvin(new BigDecimal(m.group(1)));
		}
		return fromCelsius(new BigDecimal(value));
	}
	
	public BigDecimal getKelvin() {
		return value;
	}
	
	public BigDecimal getCelsius() {
		return convertKelvinToCelsius(value);
	}
	
	public BigDecimal difference(Temperature t) {
		return value.subtract(t.value);
	}
	
	@Override
	public String toString() {
		return String.format("%.2fC", convertKelvinToCelsius(value));
	}

	private static BigDecimal convertTemperatureToKelvin(BigDecimal celsius) {
		return celsius.add(CELSIUS_TO_KELVIN);
	}
	
	private static BigDecimal convertKelvinToCelsius(BigDecimal kelvin) {
		return kelvin.subtract(CELSIUS_TO_KELVIN);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Temperature other = (Temperature) obj;
		return value.compareTo(other.value)==0;
	}
	
	@Override
	protected BigDecimal getValue() {
		return getCelsius();
	}
}
