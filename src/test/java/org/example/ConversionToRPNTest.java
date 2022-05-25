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
        when(input.retrieveRawExpression()).thenReturn("+");
        assertTrue(conv.isOperator('+'));
    }

    @Test
    void convert_giveTwoMinusOne_equalsTwoOneMinus() {
        conv = new ConversionToRPN();
        when(input.retrieveRawExpression()).thenReturn("2-1");
        assertEquals("2 1 -", conv.convert(input.retrieveRawExpression()));
    }

    @Test
    void convert_giveMinusOneInBracketsPlusTwo_equalsNegativeOneTwoPlus() {
        conv = new ConversionToRPN();
        when(input.retrieveRawExpression()).thenReturn("(-1)+2");
        assertEquals("-1 2 +", conv.convert(input.retrieveRawExpression()));
    }
    @Test
    void convert_giveNegativeOnePlusTwo_equalsNegativeOneTwoPlus() {
        conv = new ConversionToRPN();
        when(input.retrieveRawExpression()).thenReturn("-1+2");
        assertEquals("-1 2 +", conv.convert(input.retrieveRawExpression()));
    }
    @Test
    void convert_giveTwoTimesNegativeTwo_equalsTwoNegativeTwoMultiplicationSign() {
        conv = new ConversionToRPN();
        when(input.retrieveRawExpression()).thenReturn("2*(-2)");
        assertEquals("2 -2 *", conv.convert(input.retrieveRawExpression()));
    }
    @Test
    void convert_giveTwoMinusTwoInBrackets_equalsTwoNegativeTwoMultiplicationSign() {
        conv = new ConversionToRPN();
        when(input.retrieveRawExpression()).thenReturn("(2-2)");
        assertEquals("2 2 -", conv.convert(input.retrieveRawExpression()));
    }
    @Test
    void convert_giveTwoTimesFourminusOneInBrackets_equalsTwoFourOneMinusMultiply() {
        conv = new ConversionToRPN();
        when(input.retrieveRawExpression()).thenReturn("2*(4-1)");
        assertEquals("2 4 1 - *", conv.convert(input.retrieveRawExpression()));
    }
    @Test
    void convert_giveFourMinusOneInBracketsMultiplyThreePlusOneInBrackets_equalsTwoFourOneMinusMultiply() {
        conv = new ConversionToRPN();
        when(input.retrieveRawExpression()).thenReturn("(4-1)*(3+1)");
        assertEquals("4 1 - 3 1 + *", conv.convert(input.retrieveRawExpression()));
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