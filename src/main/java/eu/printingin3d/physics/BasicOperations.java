package eu.printingin3d.physics;

import java.math.BigDecimal;

public abstract class BasicOperations<T extends BasicOperations<T>> extends DoubleValue {
	protected BasicOperations(BigDecimal value) {
		super(value);
	}

	protected abstract T convert(BigDecimal value); 
	
	public final T add(T value) {
		return convert(this.value.add(value.value));
	}
	public final T substract(T value) {
		return convert(this.value.subtract(value.value));
	}
	
	public final T multiply(BigDecimal d) {
		return convert(this.value.multiply(d));
	}
	public final T multiply(Percent d) {
		return multiply(d.getValue());
	}
	
	public final T abs() {
		return convert(value.abs());
	}
	
	public final T negate() {
	    return convert(value.negate());
	}
	
	public final boolean isPositive() {
		return value.signum()>0;
	}
	
	public final boolean isNegative() {
		return value.signum()<0;
	}
	
	public final boolean isZero() {
	    return value.signum() == 0;
	}
}
