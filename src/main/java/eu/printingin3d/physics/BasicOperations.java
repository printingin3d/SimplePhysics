package eu.printingin3d.physics;

public abstract class BasicOperations<T extends BasicOperations<T>> extends DoubleValue {
	protected BasicOperations(double value) {
		super(value);
	}

	protected abstract T convert(double value); 
	
	public final T add(T value) {
		return convert(this.value+value.value);
	}
	public final T substract(T value) {
		return convert(this.value-value.value);
	}
	
	public final T multiply(double d) {
		return convert(this.value*d);
	}
	public final T multiply(Percent d) {
		return multiply(d.getValue());
	}
	
	public final T abs() {
		return convert(Math.abs(value));
	}
	
	public final boolean isPositive() {
		return value>0.0;
	}
	
	public final boolean isNegative() {
		return value<0.0;
	}
	
	public final boolean isZero() {
	    return value == 0.0;
	}
}
