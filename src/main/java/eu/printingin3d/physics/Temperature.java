package eu.printingin3d.physics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class Temperature extends DoubleValue {
	public static final Temperature CELSIUS_ZERO = fromCelsius(0.0);
	
	private static final double CELSIUS_TO_KELVIN = 273.14;
	private static final Pattern CELSIUS_PATTERN = Pattern.compile("([0-9]+(\\.[0-9]+)?)C");
	private static final Pattern KELVIN_PATTERN = Pattern.compile("([0-9]+(\\.[0-9]+)?)K");
	
	private Temperature(double kelvin) {
		super(kelvin);
	}
	
	public static Temperature weightedMean(Temperature temp1, double weight1, Temperature temp2, double weight2) {
		return new Temperature((temp1.value*weight1+temp2.value*weight2)/(weight1+weight2));
	}
	
	public static Temperature mean(Temperature... temps) {
		double sum = 0.0;
		for (Temperature temp : temps) {
			sum+= temp.value;
		}
		return new Temperature(sum/temps.length);
	}
	
	public static Temperature fromCelsius(double celsius) {
		return new Temperature(convertTemperatureToKelvin(celsius));
	}
	
	public static Temperature fromKelvin(double kelvin) {
		return new Temperature(kelvin);
	}
	
	public static Temperature fromString(String value) {
		Matcher m = CELSIUS_PATTERN.matcher(value);
		if (m.matches()) {
			return fromCelsius(Double.parseDouble(m.group(1)));
		}
		m = KELVIN_PATTERN.matcher(value);
		if (m.matches()) {
			return fromKelvin(Double.parseDouble(m.group(1)));
		}
		return fromCelsius(Double.parseDouble(value));
	}
	
	public double getKelvin() {
		return value;
	}
	
	public double getCelsius() {
		return convertKelvinToCelsius(value);
	}
	
	public double difference(Temperature t) {
		return value-t.value;
	}
	
	@Override
	public String toString() {
		return String.format("%.2fC", Double.valueOf(convertKelvinToCelsius(value)));
	}

	private static double convertTemperatureToKelvin(double celsius) {
		return celsius + CELSIUS_TO_KELVIN;
	}
	
	private static double convertKelvinToCelsius(double kelvin) {
		return kelvin - CELSIUS_TO_KELVIN;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
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
		if (Double.doubleToLongBits(value) != Double
				.doubleToLongBits(other.value)) {
			return false;
		}
		return true;
	}
	
	@Override
	protected double getValue() {
		return getCelsius();
	}
}
