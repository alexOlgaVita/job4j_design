package ru.job4j.iterator;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .contains("ph")
                .containsAnyOf("e", "zxc")
                .containsPattern("[a-s]")
                .containsOnlyOnce("ph");
    }

    @Test
    void isThisUnknownWhenEdgePositive() {
        Box box = new Box(9, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("UNK")
                .contains("own")
                .doesNotContain("objects")
                .containsAnyOf("Unknown", "object")
                .containsPattern("[a-s ]")
                .containsOnlyOnce("known");
    }

    @Test
    void isThisUnknownWhenEdgeNegative() {
        Box box = new Box(1, -1);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("UNK")
                .contains("own")
                .doesNotContain("objects")
                .containsAnyOf("Unknown", "object")
                .containsPattern("[a-s ]")
                .containsOnlyOnce("known");
    }

    @Test
    void getAreaWhenVertex0() {
        Box box = new Box(0, 2);
        double area = box.getArea();
        assertThat(area).isEqualTo(50.26d, withPrecision(0.006d));
    }

    @Test
    void getAreaWhenVertex4() {
        Box box = new Box(4, 2);
        double area = box.getArea();
        assertThat(area).isEqualTo(6.92d, withPrecision(0.009d))
                .isCloseTo(6.91d, withPrecision(0.019d))
                .isCloseTo(6.91d, Percentage.withPercentage(1.0d))
                .isGreaterThan(6.91d)
                .isLessThan(6.93d);
    }

    @Test
    void getAreaWhenVertex8() {
        Box box = new Box(8, 2);
        double area = box.getArea();
        assertThat(area).isEqualTo(24d, withPrecision(0d))
                .isCloseTo(23.99d, withPrecision(0.1d))
                .isCloseTo(23.99d, Percentage.withPercentage(10d))
                .isGreaterThan(23.99d)
                .isLessThan(25.99d);
    }

    @Test
    void getAreaWhenVertex9() {
        Box box = new Box(9, 2);
        double area = box.getArea();
        assertThat(area).isEqualTo(0d, withPrecision(0.001d))
                .isCloseTo(0d, withPrecision(0.1d))
                .isCloseTo(0d, Percentage.withPercentage(100d))
                .isGreaterThan(-0.09d)
                .isLessThan(0.1d);
    }

    @Test
    void getNumberOfVerticesWhenVertex8() {
        Box box = new Box(8, 2);
        assertThat(box.getNumberOfVertices()).isEqualTo(8)
                .isEven()
                .isGreaterThan(7)
                .isLessThan(9)
                .isBetween(6, 10)
                .isPositive();
    }

    @Test
    void getNumberOfVerticesWhenVertex0() {
        Box box = new Box(0, 2);
        assertThat(box.getNumberOfVertices()).isEqualTo(0)
                .isEven()
                .isGreaterThan(-1)
                .isLessThan(1)
                .isBetween(-1, 1)
                .isNotPositive();
    }

    @Test
    void existWhenEdge0() {
        Box box = new Box(0, 2);
        assertThat(box.isExist()).isTrue().isNotNull();
    }

    @Test
    void notExistWhenWhenEdgeMinus1() {
        Box box = new Box(0, -1);
        assertThat(box.isExist()).isFalse().isNotNull();
    }
}