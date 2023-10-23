package eu.printingin3d.physics;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public final class Percent {
    public static final Percent ZERO = new Percent(0);
    public static final Percent HALF = new Percent(5000);
    public static final Percent MAX = new Percent(10000);
    
    private static final BigDecimal MAX_PERCENT = new BigDecimal(100);
    
	public static Percent fromPercentValue(int percent) {
	    if (percent<0 || percent>100) {
            throw new IllegalArgumentException("Value of a percent should be between 0 and 100, "
                    + "but "+percent+" was given");
	    }
	    return new Percent(percent*100);
	}
	
	public static Percent fromPercentValueTrim(int percent) {
	    if (percent<=0) {
            return Percent.ZERO;
        }
	    if (percent>=100) {
            return Percent.MAX;
        }
	    return new Percent(percent*100);
	}
	
	public static Percent fromPercentValue(BigDecimal percent) {
	    if (percent.signum()<0 || percent.compareTo(MAX_PERCENT)>0) {
	        throw new IllegalArgumentException("Value of a percent should be between 0.0 and 100.0, "
	                + "but "+percent+" was given");
	    }
	    return new Percent(percent.movePointRight(2).round(new MathContext(0, RoundingMode.HALF_DOWN)).intValue());
	}
	
	public static Percent fromPercentValueTrim(BigDecimal percent) {
	    if (percent.signum()<=0) {
            return Percent.ZERO;
        }
	    if (percent.compareTo(MAX_PERCENT)>0) {
            return Percent.MAX;
        }
	    return new Percent(percent.movePointRight(2).round(new MathContext(0, RoundingMode.HALF_DOWN)).intValue());
	}
	
	public static Percent fromPermyriad(int permyriad) {
	    if (permyriad<0 || permyriad>10000) {
	        throw new IllegalArgumentException("Value of a percent should be between 0 and 100, "
	                + "but "+permyriad+" was given");
	    }
	    return new Percent(permyriad);
	}
	
	public static Percent fromValue(BigDecimal value) {
        if (value.signum()<0 || value.compareTo(BigDecimal.ONE)>0) {
            throw new IllegalArgumentException("Value of a percent should be between 0 and 1");
        }
        
        return new Percent(value.movePointRight(4).round(new MathContext(0, RoundingMode.HALF_DOWN)).intValue());
	}

	// 0 means 0.00%, 10000 means 100.00%
	private final int value;

	private Percent(int value) {
	    this.value = value;
	}

	/**
	 * Return the int value of this Percent object between 0 and 100
	 * @return an int representation of Percent
	 */
	public int getPercent() {
	    return (value+5)/100;
	}
	
    /**
     * Return the int value of this Percent object between 0 and 1000
     * @return an int representation of Percent
     */
	public int getPermille() {
	    return (value+5)/10;
	}
	
	/**
	 * Return the int value of this Percent object between 0 and 10000
	 * @return an int representation of Percent
	 */
	public int getPermyriad() {
	    return value;
	}
	
	public BigDecimal getValue() {
	    return BigDecimal.valueOf(value, 4);
	}

	public Percent getComplementer() {
	    return new Percent(10000-value);
	}

	@Override
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(value/100);
        int v = value%100;
        sb.append('.');
        if (v<10) {
            sb.append('0');
        }
        return sb.append(v).append('%').toString();
    }

    @Override
    public int hashCode() {
        return value;
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
        Percent other = (Percent) obj;
        return value == other.value;
    }
}
