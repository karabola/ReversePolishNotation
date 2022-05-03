package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculatorTest {
    private Calculator calc;
    private Input input = mock(Input.class);
    private ConversionToRPN c = mock(ConversionToRPN.class);

    @BeforeEach
    void setUp() {
        calc = new Calculator(c, c.rpn);
//        when(input.start()).thenReturn("a+b");
        when(input.start()).thenReturn("a+b");
    }

    @Test
    void countTest() {
            //given
        String [] elements = {"a", "b", "+"};
        when(c.rpn).thenReturn("a b +");


//        String[] elements = {"a", "b", "+"};
            //when
            Throwable throwable = Assertions.catchThrowable(() -> calc.count(calc.elements));
            //then
            assertThat(throwable).hasMessage("Incorrect argument (only numbers and operators can contain the exception)");

    }
}