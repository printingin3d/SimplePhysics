package eu.printingin3d.physics;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PercentTest {
    private static class TestPercentCase {
        private final Percent expected;
        private final int value;
        public TestPercentCase(Percent expected, int value) {
            this.expected = expected;
            this.value = value;
        }
    }
    
    @SuppressWarnings("boxing")
    private static Stream<Integer> equalCases() {
        return Stream.of(0, 10, 20, 30, 60, 75, 100);
    }
    
    private static Stream<TestPercentCase> percentCases() {
        return Stream.of(
                    new TestPercentCase(Percent.ZERO, 0),
                    new TestPercentCase(Percent.HALF, 50),
                    new TestPercentCase(Percent.MAX, 100)
                );
    }
    
    @ParameterizedTest
    @MethodSource("percentCases")
    public void testPercentValue(TestPercentCase c) {
        assertEquals(c.expected, Percent.fromPercentValue(c.value));
        assertEquals(c.expected, Percent.fromPercentValueTrim(c.value));
        assertEquals(c.expected, Percent.fromPercentValue(new BigDecimal(c.value)));
        assertEquals(c.expected, Percent.fromPercentValueTrim(new BigDecimal(c.value)));
    }

    @Test
    public void testfromValue() {
        assertEquals(Percent.ZERO, Percent.fromValue(BigDecimal.ZERO));
        
        assertEquals(Percent.HALF, Percent.fromValue(new BigDecimal("0.5")));
        
        assertEquals(Percent.MAX, Percent.fromValue(BigDecimal.ONE));
    }

    @Test
    public void testBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> Percent.fromPercentValue(-50));
        assertEquals(Percent.ZERO, Percent.fromPercentValueTrim(-50));
        assertThrows(IllegalArgumentException.class, () -> Percent.fromPercentValue(new BigDecimal(-50)));
        assertEquals(Percent.ZERO, Percent.fromPercentValueTrim(new BigDecimal(-50)));
        assertThrows(IllegalArgumentException.class, () -> Percent.fromPermyriad(-5000));
        assertThrows(IllegalArgumentException.class, () -> Percent.fromValue(new BigDecimal("-0.5")));
    }
    
    @Test
    public void testAboveMax() {
        assertThrows(IllegalArgumentException.class, () -> Percent.fromPercentValue(150));
        assertEquals(Percent.MAX, Percent.fromPercentValueTrim(150));
        assertThrows(IllegalArgumentException.class, () -> Percent.fromPercentValue(new BigDecimal(150)));
        assertEquals(Percent.MAX, Percent.fromPercentValueTrim(new BigDecimal(150)));
        assertThrows(IllegalArgumentException.class, () -> Percent.fromPermyriad(15000));
        assertThrows(IllegalArgumentException.class, () -> Percent.fromValue(new BigDecimal("1.5")));
    }

    @Test
    public void testPermyriad() {
        assertEquals(Percent.ZERO, Percent.fromPermyriad(0));
        
        assertEquals(Percent.HALF, Percent.fromPermyriad(5000));
        
        assertEquals(Percent.MAX, Percent.fromPermyriad(10000));
    }
    
    @Test
    public void testPercent() {
        assertEquals(0, Percent.ZERO.getPercent());
        assertEquals(50, Percent.HALF.getPercent());
        assertEquals(100, Percent.MAX.getPercent());
    }
    
    @Test
    public void testPermille() {
        assertEquals(0, Percent.ZERO.getPermille());
        assertEquals(500, Percent.HALF.getPermille());
        assertEquals(1000, Percent.MAX.getPermille());
    }
    
    @Test
    public void testGetPermyriad() {
        assertEquals(0, Percent.ZERO.getPermyriad());
        assertEquals(5000, Percent.HALF.getPermyriad());
        assertEquals(10000, Percent.MAX.getPermyriad());
    }
    
    @Test
    public void testGetValue() {
        assertThat(Percent.ZERO.getValue(), comparesEqualTo(BigDecimal.ZERO));
        assertThat(Percent.HALF.getValue(), comparesEqualTo(new BigDecimal("0.5")));
        assertThat(Percent.MAX.getValue(), comparesEqualTo(BigDecimal.ONE));
    }
    
    @Test
    public void testComplementer() {
        assertEquals(Percent.MAX, Percent.ZERO.getComplementer());
        assertEquals(Percent.fromPercentValue(75), Percent.fromPercentValue(25).getComplementer());
        assertEquals(Percent.HALF, Percent.HALF.getComplementer());
        assertEquals(Percent.ZERO, Percent.MAX.getComplementer());
    }
    
    @Test
    public void testToString() {
        assertEquals("0.00%", Percent.ZERO.toString());
        assertEquals("5.00%", Percent.fromPermyriad(500).toString());
        assertEquals("7.50%", Percent.fromPermyriad(750).toString());
        assertEquals("12.55%", Percent.fromPermyriad(1255).toString());
        assertEquals("50.00%", Percent.HALF.toString());
        assertEquals("100.00%", Percent.MAX.toString());
    }
    
    @ParameterizedTest
    @MethodSource("equalCases")
    public void testBasicEquals(int v) {
        Percent p = Percent.fromPercentValue(v);
        assertEquals(p, p);
        assertNotEquals(p, p.getComplementer());
        assertEquals(p.hashCode(), p.hashCode());
    }
    
    @ParameterizedTest
    @MethodSource("equalCases")
    public void testEqualsNull(int v) {
        Percent p = Percent.fromPercentValue(v);
        assertNotEquals(p, null);
    }
    
    @ParameterizedTest
    @MethodSource("equalCases")
    public void testEqualsString(int v) {
        Percent p = Percent.fromPercentValue(v);
        assertNotEquals(p, "asdasd");
    }
    
    @ParameterizedTest
    @MethodSource("equalCases")
    public void testReadValue(int v) {
        Percent p = Percent.fromPercentValue(v);
        assertEquals(p.getPercent(), v);
    }
    
}
