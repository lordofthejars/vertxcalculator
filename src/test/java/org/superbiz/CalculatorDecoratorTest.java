package org.superbiz;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorDecoratorTest {

    @Test
    public void should_decorate_sum() {

        // given
        CalculatorDecorator calculatorDecorator = new CalculatorDecorator();

        // when
        final String result = calculatorDecorator.add(1, 3);

        // then
        assertThat(result).isEqualTo("1 + 3 = 4");
    }

}
