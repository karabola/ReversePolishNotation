package org.example;

import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    private Calculator calc;
    private Input input = mock(Input.class);
    private ConversionToRPN conv = mock(ConversionToRPN.class);

    @BeforeEach
    void setUp() {
        String rpn = conv.convert(input.start());
        calc = new Calculator(rpn);
        when(input.start()).thenReturn("a+b");
    }

    @Test
    void countTest() {
            //given
        String [] elements = {"a", "b", "+"};
//        when(conv).thenReturn("a b +");


////        String[] elements = {"a", "b", "+"};
//            //when
//            Throwable throwable = Assertions.catchThrowable(() -> calc.count(calc.elements));
//            //then
//            assertThat(throwable).hasMessage("Incorrect argument (only numbers and operators can contain the exception)");
//
    }
}