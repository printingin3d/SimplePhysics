package eu.printingin3d.utils;

public final class DoubleFormat {
    private DoubleFormat() {}
    
    public static String formatWithSiPrefixes(double value) {
        return formatWithSiPrefixes(value, 4);
    }
    
    public static String formatWithSiPrefixes(double value, int precision) {
        String formatStr = "%."+precision+"f";
        
        if (Math.abs(value)>1E9) {
            return String.format(formatStr+"G", Double.valueOf(value/1E9));
        }
        if (Math.abs(value)>1E6) {
            return String.format(formatStr+"M", Double.valueOf(value/1E6));
        }
        if (Math.abs(value)>1E3) {
            return String.format(formatStr+"k", Double.valueOf(value/1E3));
        }
        return String.format(formatStr, Double.valueOf(value));
    }
}
