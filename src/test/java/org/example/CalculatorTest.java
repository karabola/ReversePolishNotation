package org.example;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculatorTest {
    private Calculator calc;
    private final Input input = mock(Input.class);
    private final ConversionToRPN conv = mock(ConversionToRPN.class);

    @BeforeEach
    void setUp() {
//        String rpn = conv.convert(input.start());
//        calc = new Calculator(rpn);

    }

    @Test
    void count_addTwoAndTwo_getSix() {
        calc = new Calculator("2 2 +");
        assertEquals(4, calc.count());
    }

    @Test
    void count_takeExpression_getSix() {
        calc = new Calculator("3 2 + 8 4 * -");
        assertEquals(-27, calc.count());
    }

    @Test
    void count_divideByZero_throwException() {
        //given
        when(conv.convert(input.start())).thenThrow(new ArithmeticException("Divided by zero!"));
        //when
        Throwable throwable = Assertions.catchThrowable(() -> calc.count());
        //then
        AssertionsForClassTypes.assertThat(throwable);
    }

    @Test
    void count_lettersUsing_throwException() {
        //given
        when(conv.convert(input.start())).thenThrow(new IllegalArgumentException("Incorrect argument (only numbers and operators can contain the exception)"));
        //when
        Throwable throwable = Assertions.catchThrowable(() -> calc.count());
        //then
        AssertionsForClassTypes.assertThat(throwable);
    }
}