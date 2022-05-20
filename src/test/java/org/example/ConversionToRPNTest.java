package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConversionToRPNTest {

    private ConversionToRPN conv;
    private Input input = mock(Input.class);

    @BeforeEach
    void setUp() {
        conv = new ConversionToRPN();
    }

    @Test
    void isNum_giveFive_true() {
        when(input.retrieveRawExpression()).thenReturn("5");
        assertTrue(conv.isNumber('5'));
    }

    @Test
    void isOperator_givePlus_true() {
        conv = new ConversionToRPN();
        when(input.retrieveRawExpression()).thenReturn("c");
        assertTrue(conv.isOperator('+'));
    }

    @Test
    void convert_giveEquation_equals() {
        conv = new ConversionToRPN();
        when(input.retrieveRawExpression()).thenReturn("1+2");
        assertEquals("1 2 +", conv.convert(input.retrieveRawExpression()));
    }

    @Test
    void convert_giveEquationWithNegative_equals() {
        conv = new ConversionToRPN();
        when(input.retrieveRawExpression()).thenReturn("(-1)+2");
        assertEquals("-1 2 +", conv.convert(input.retrieveRawExpression()));
    }
    @Test
    void convert_inputIsEmpty_throwException() {
        //given
        when(input.retrieveRawExpression()).thenThrow(new NullPointerException("Try again and enter an expression"));
        //when
        Throwable throwable = Assertions.catchThrowable(() -> conv.convert(input.retrieveRawExpression()));
        //then
        assertThat(throwable);
    }


}