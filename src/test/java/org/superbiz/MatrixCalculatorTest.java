package org.superbiz;

import org.junit.Test;
import org.la4j.Matrix;

import static org.assertj.core.api.Assertions.assertThat;

public class MatrixCalculatorTest {

    @Test
    public void should_sum_two_matrix() {

        // given
        final Matrix a = Matrix.from2DArray(new double[][] {{1d, 1d}, {1d, 1d}});
        final Matrix b = Matrix.from2DArray(new double[][] {{1d, 1d}, {1d, 1d}});
        MatrixCalculator matrixCalculator = new MatrixCalculator();

        // when
        final Matrix sum = matrixCalculator.sum(a, b);

        // then
        final Matrix expectedSum = Matrix.from2DArray(new double[][] {{2d, 2d}, {2d, 2d}});
        assertThat(sum).isEqualTo(expectedSum);
    }

}
