package eu.printingin3d.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class DoubleValueTest {
    private static Stream<DoubleValue> testCases() {
        return Stream.<DoubleValue>of(
                new Current(BigDecimal.ONE),
                Energy.fromJoule(BigDecimal.ONE),
                new Frequency(BigDecimal.ONE),
                new HeatInertia(BigDecimal.ONE),
                new HeatingParam(BigDecimal.ONE),
                new Power(BigDecimal.ONE),
                new Pressure(BigDecimal.ONE),
                Speed.fromMeterPerSec(BigDecimal.ONE),
                Temperature.fromKelvin(BigDecimal.ONE),
                Time.ofSecond(BigDecimal.ONE),
                new Voltage(BigDecimal.ONE),
                new Volume(BigDecimal.ONE),
                new VolumeFlowRate(BigDecimal.ONE),
                new Weight(BigDecimal.ONE)
           );
    }
    
    @ParameterizedTest
    @MethodSource("testCases")
    public void testBasicEquals(DoubleValue v) {
        assertEquals(v, v);
    }
    
    @ParameterizedTest
    @MethodSource("testCases")
    public void testEqualsNull(DoubleValue v) {
        assertNotEquals(v, null);
    }
    
    @ParameterizedTest
    @MethodSource("testCases")
    public void testEqualsString(DoubleValue v) {
        assertNotEquals(v, "asdasd");
    }
    
    @ParameterizedTest
    @MethodSource("testCases")
    public void testReadValue(DoubleValue v) {
        assertEquals(v.getValue(), DoubleValue.readValue(v));
    }

}
