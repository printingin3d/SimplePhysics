package eu.printingin3d.physics;

public class VolumeFlowRate extends DoubleValue {
	/**
	 * 
	 * @param volume m3/h
	 */
	public VolumeFlowRate(double volume) {
		super(volume);
	}
	
	public Volume getVolumeOfTime(Time time) {
		return new Volume(value*time.div(Time.HOUR));
	}
	
	public VolumeFlowRate multiply(Percent percent) {
		return new VolumeFlowRate(value*percent.getValue());
	}

	@Override
	public String toString() {
		return value+"m3/h";
	}
}
