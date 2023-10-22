package eu.printingin3d.utils;

import java.math.BigDecimal;
import java.util.Locale;

public final class DoubleFormat {
    private static final BigDecimal BILLION = new BigDecimal(1000000000);
    private static final BigDecimal MILLION = new BigDecimal(1000000);
    private static final BigDecimal THOUSAND = new BigDecimal(1000);
    
    private DoubleFormat() {}
    
    public static String formatWithSiPrefixes(BigDecimal value) {
        return formatWithSiPrefixes(value, 4);
    }
    
    public static String formatWithSiPrefixes(BigDecimal value, int precision) {
        String formatStr = "%."+precision+"f";
        
        if (value.abs().compareTo(BILLION)>0) {
            return String.format(Locale.ENGLISH, formatStr+"G", value.movePointLeft(9));
        }
        if (value.abs().compareTo(MILLION)>0) {
            return String.format(Locale.ENGLISH, formatStr+"M", value.movePointLeft(6));
        }
        if (value.abs().compareTo(THOUSAND)>0) {
            return String.format(Locale.ENGLISH, formatStr+"k", value.movePointLeft(3));
        }
        if (value.abs().compareTo(BigDecimal.ONE)<0) {
            return String.format(Locale.ENGLISH, formatStr+"m", value.movePointRight(3));
        }
        return String.format(Locale.ENGLISH, formatStr, value);
    }
}
