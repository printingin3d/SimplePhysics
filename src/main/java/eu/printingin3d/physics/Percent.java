package eu.printingin3d.physics;

public class Percent {
	public static final Percent ZERO = new Percent(0);
	public static final Percent HALF = new Percent(5000);
	public static final Percent MAX = new Percent(10000);

	public static Percent fromPercentValue(int percent) {
	    if (percent<0 || percent>100) {
            throw new IllegalArgumentException("Value of a percent should be between 0 and 100, "
                    + "but "+percent+" was given");
	    }
	    return new Percent(percent*100);
	}
	
	public static Percent fromPercentValue(double percent) {
	    if (percent<0.0 || percent>100.0) {
	        throw new IllegalArgumentException("Value of a percent should be between 0.0 and 100.0, "
	                + "but "+percent+" was given");
	    }
	    return new Percent((int)Math.round(percent*100.0));
	}
	
	public static Percent fromPermyriad(int permyriad) {
	    if (permyriad<0 || permyriad>10000) {
	        throw new IllegalArgumentException("Value of a percent should be between 0 and 100, "
	                + "but "+permyriad+" was given");
	    }
	    return new Percent(permyriad);
	}
	
	public static Percent fromValue(double value) {
        if (value<0.0 || value>1.0) {
            throw new IllegalArgumentException("Value of a percent should be between 0 and 1");
        }
        
        return new Percent((int)Math.round(value*10000.0));
	}

	// 0 means 0.00%, 10000 means 100.00%
	private final int value;

	private Percent(int value) {
	    this.value = value;
	}
	
	@Deprecated
	public Percent(double value) {
		if (value<0.0 || value>1.0) {
			throw new IllegalArgumentException("Value of a percent should be between 0 and 1");
		}
		
		this.value = (int)Math.round(value*10000.0);
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
	
	public double getValue() {
		return value*0.0001;
	}
	
	@Deprecated
	public double getComplementerValue() {
		return 1.0-getValue();
	}
	
	public Percent getComplementer() {
	    return new Percent(10000-value);
	}

	@Override
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(value/100);
        int v = value%100;
        sb.append(',');
        if (v<10) {
            sb.append('0');
        }
        return sb.append(v).append('%').toString();
    }
}
