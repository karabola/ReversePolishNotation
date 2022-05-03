package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConversionToRPNTest {

    private ConversionToRPN c;
    private Input input = mock(Input.class);

    @BeforeEach
    void setUp() {
        c = new ConversionToRPN(expression);
    }

    @Test
    void isNum_giveFive_true() {
        when(input.start()).thenReturn("c");
        assertTrue(c.isNum('5'));
    }

    @Test
    void isOperator_givePlus_true() {
        c = new ConversionToRPN(input);
        when(input.start()).thenReturn("c");
        assertTrue(c.isOperator('+'));
    }

    @Test
    void convert_giveEquation_equals() {
        c = new ConversionToRPN(input);
        when(input.start()/*getExpression()*/).thenReturn("1+2");
        assertEquals("1 2 +", c.convert());
    }

    @Test
    void convert_inputIsEmpty_throwException() {
        //given
        when(input.start()).thenThrow(new NullPointerException("Try again and enter an expression"));
        //when
        Throwable throwable = Assertions.catchThrowable(() -> c.convert());
       //then
        assertThat(throwable);
    }


}