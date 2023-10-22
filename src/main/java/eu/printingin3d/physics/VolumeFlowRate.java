package eu.printingin3d.physics;

import java.math.BigDecimal;

public class VolumeFlowRate extends DoubleValue {
	/**
	 * 
	 * @param volume m3/h
	 */
	public VolumeFlowRate(BigDecimal volume) {
		super(volume);
	}
	
	public Volume getVolumeOfTime(Time time) {
		return new Volume(value.multiply(time.div(Time.HOUR)));
	}
	
	public VolumeFlowRate multiply(Percent percent) {
		return new VolumeFlowRate(value.multiply(percent.getValue()));
	}

	@Override
	public String toString() {
		return value+"m3/h";
	}
}
