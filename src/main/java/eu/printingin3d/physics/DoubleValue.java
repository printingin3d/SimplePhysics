package eu.printingin3d.physics;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class DoubleValue implements Comparable<DoubleValue> {
	protected final BigDecimal value;

	public static BigDecimal readValue(DoubleValue dv) {
		return dv.getValue();
	}
	
	public DoubleValue(BigDecimal value) {
		this.value = Objects.requireNonNull(value);
	}
	
	protected BigDecimal getValue() {
		return value;
	}

	@Override
	public int compareTo(DoubleValue o) {
		return this.value.compareTo(o.value);
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
        DoubleValue other = (DoubleValue) obj;
        return value.equals(other.value);
    }
}
