package eu.printingin3d.physics;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class BasicOperationsBatchTest {
    private static class TestCase<T extends BasicOperations<T>> {
        private final T a;
        private final T b;
        private final T c;
        
        public TestCase(T a, T b, T c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        @Override
        public String toString() {
            return a.getClass().getSimpleName();
        }
    }

    private static Stream<TestCase<?>> testCases() {
        return Stream.<TestCase<?>>of(
                new TestCase<>(new Power(new BigDecimal(1)), new Power(new BigDecimal(10)), new Power(new BigDecimal(11))),
                new TestCase<>(new Current(new BigDecimal(9)), new Current(new BigDecimal(10)), new Current(new BigDecimal(19))),
                new TestCase<>(Energy.fromWattHour(new BigDecimal(9)), Energy.fromWattHour(new BigDecimal(10)), Energy.fromWattHour(new BigDecimal(19))),
                new TestCase<>(new Frequency(new BigDecimal(9)), new Frequency(new BigDecimal(10)), new Frequency(new BigDecimal(19))),
                new TestCase<>(Speed.fromMeterPerSec(new BigDecimal(9)), Speed.fromMeterPerSec(new BigDecimal(10)), Speed.fromMeterPerSec(new BigDecimal(19))),
                new TestCase<>(Time.ofSecond(new BigDecimal(9)), Time.ofSecond(new BigDecimal(10)), Time.ofSecond(new BigDecimal(19))),
                new TestCase<>(new Voltage(new BigDecimal(9)), new Voltage(new BigDecimal(10)), new Voltage(new BigDecimal(19))),
                new TestCase<>(new Weight(new BigDecimal(9)), new Weight(new BigDecimal(10)), new Weight(new BigDecimal(19)))
           );
    }
    
    @ParameterizedTest
    @MethodSource("testCases")
    public <T extends BasicOperations<T>> void testAdd(TestCase<T> testCase) {
        T res = testCase.a.add(testCase.b);
        assertEquals(testCase.c.getClass(), res.getClass());
        assertEquals(testCase.c, res);
    }
    
    @ParameterizedTest
    @MethodSource("testCases")
    public <T extends BasicOperations<T>> void testSubstract(TestCase<T> testCase) {
        T res = testCase.c.substract(testCase.b);
        assertEquals(testCase.a.getClass(), res.getClass());
        assertEquals(testCase.a, res);
    }
    
    @ParameterizedTest
    @MethodSource("testCases")
    public <T extends BasicOperations<T>> void testMultiplyByOne(TestCase<T> testCase) {
        T res = testCase.a.multiply(BigDecimal.ONE);
        assertEquals(testCase.a.getClass(), res.getClass());
        assertEquals(testCase.a.toString(), res.toString());
    }
    
    @ParameterizedTest
    @MethodSource("testCases")
    public <T extends BasicOperations<T>> void testMultiplyByTen(TestCase<T> testCase) {
        T res = testCase.a.multiply(BigDecimal.TEN);
        assertEquals(testCase.a.getClass(), res.getClass());
        assertThat(res.value, comparesEqualTo(testCase.a.value.multiply(BigDecimal.TEN)));
    }
    
    @ParameterizedTest
    @MethodSource("testCases")
    public <T extends BasicOperations<T>> void testMultiplyByPercent(TestCase<T> testCase) {
        T res = testCase.a.multiply(Percent.HALF);
        assertEquals(testCase.a.getClass(), res.getClass());
        assertThat(res.value, comparesEqualTo(testCase.a.value.multiply(new BigDecimal("0.5"))));
    }
    
    @ParameterizedTest
    @MethodSource("testCases")
    public <T extends BasicOperations<T>> void testNegate(TestCase<T> testCase) {
        T res = testCase.a.negate();
        assertEquals(testCase.a.getClass(), res.getClass());
        assertTrue(testCase.a.isPositive());
        assertFalse(testCase.a.isNegative());
        assertFalse(res.isZero());
        assertFalse(res.isPositive());
        assertTrue(res.isNegative());
        assertEquals(res.abs(), testCase.a);
    }
    
    @ParameterizedTest
    @MethodSource("testCases")
    public <T extends BasicOperations<T>> void testZero(TestCase<T> testCase) {
        T res = testCase.a.substract(testCase.a);
        assertEquals(testCase.a.getClass(), res.getClass());
        assertTrue(res.isZero());
        assertThat(res.value, comparesEqualTo(BigDecimal.ZERO));
    }
}
