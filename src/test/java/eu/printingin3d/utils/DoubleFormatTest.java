package eu.printingin3d.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class DoubleFormatTest {
    @Test
    public void testMilli() {
        String res = DoubleFormat.formatWithSiPrefixes(new BigDecimal("0.1"));
        assertEquals("100.0000m", res);
    }
    @Test
    public void testMilliWithPrecision() {
        String res = DoubleFormat.formatWithSiPrefixes(new BigDecimal("0.1"), 2);
        assertEquals("100.00m", res);
    }
    
    @Test
    public void testNormal() {
        String res = DoubleFormat.formatWithSiPrefixes(new BigDecimal(10));
        assertEquals("10.0000", res);
    }
    
    @Test
    public void testNormalWithPrecision() {
        String res = DoubleFormat.formatWithSiPrefixes(new BigDecimal(10), 2);
        assertEquals("10.00", res);
    }
    
    @Test
    public void testKilo() {
        String res = DoubleFormat.formatWithSiPrefixes(new BigDecimal(1893));
        assertEquals("1.8930k", res);
    }
    
    @Test
    public void testKiloWithPrecision() {
        String res = DoubleFormat.formatWithSiPrefixes(new BigDecimal(1893), 2);
        assertEquals("1.89k", res);
    }
    
    @Test
    public void testMega() {
        String res = DoubleFormat.formatWithSiPrefixes(new BigDecimal(2345678));
        assertEquals("2.3457M", res);
    }
    
    @Test
    public void testMegaWithPrecision() {
        String res = DoubleFormat.formatWithSiPrefixes(new BigDecimal(2345678), 2);
        assertEquals("2.35M", res);
    }
    
    @Test
    public void testGiga() {
        String res = DoubleFormat.formatWithSiPrefixes(new BigDecimal(3456789012L));
        assertEquals("3.4568G", res);
    }
    
    @Test
    public void testGigaWithPrecision() {
        String res = DoubleFormat.formatWithSiPrefixes(new BigDecimal(3456789012L), 2);
        assertEquals("3.46G", res);
    }
}
