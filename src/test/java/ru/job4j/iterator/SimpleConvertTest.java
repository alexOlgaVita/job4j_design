package ru.job4j.iterator;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkGeneralArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        String[] expectedArray = {"first", "second", "three", "four", "five"};
        assertThat(array).hasSize(5)
                .isEqualTo(expectedArray)
                .containsOnly("first", "second", "three", "four", "five")
                .containsExactlyInAnyOrder("first", "second", "three", "four", "five")
                .containsAnyOf("zero", "four", "ten")
                .doesNotContain("zero", "ten")
                .containsSequence("second", "three")
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .startsWith("first", "second")
                .endsWith("four", "five");
    }

    @Test
    void checkGeneralList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        List<String> expectedList = List.of("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .isEqualTo(expectedList)
                .containsOnly("first", "second", "three", "four", "five")
                .containsExactlyInAnyOrder("first", "second", "three", "four", "five")
                .containsAnyOf("zero", "four", "ten")
                .doesNotContain("zero", "ten")
                .containsSequence("second", "three")
                .contains("three")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .startsWith("first", "second")
                .endsWith("four", "five");
    }

    @Test
    void checkGeneralSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "second", "five");
        assertThat(set).hasSize(5)
                .containsOnly("first", "second", "three", "four", "five")
                .containsExactlyInAnyOrder("first", "second", "three", "four", "five")
                .containsAnyOf("zero", "four", "ten")
                .doesNotContain("zero", "ten")
                .contains("three")
                .containsAnyOf("zero", "second", "six");
    }

    @Test
    void checkGeneralMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        Map<String, Integer> expectedMap = Map.of("first", 0, "second", 1, "three", 2, "four", 3, "five", 4);
        assertThat(map).hasSize(5)
                .isEqualTo(expectedMap)
                .containsKeys("second", "first", "five")
                .containsValues(4, 0, 1)
                .containsEntry("three", 2)
                .doesNotContainKey("ten")
                .doesNotContainValue(7)
                .doesNotContainEntry("first", 1);
    }

    @Test
    void checkSatisfyArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five", "two");
        assertThat(array).isNotNull()
                .allSatisfy(e -> {
                    assertThat(e).doesNotEndWith("n");
                    assertThat(e).doesNotStartWith("n");
                })
                .anySatisfy(e -> {
                    assertThat(e).endsWith("t");
                    assertThat(e).startsWith("f");
                })
                .allMatch(e -> e.length() < 10)
                .anyMatch(e -> e.length() == 3)
                .noneMatch(e -> e.length() < 1);
    }

    @Test
    void checkSatisfyList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five", "two");
        assertThat(list).isNotNull()
                .allSatisfy(e -> {
                    assertThat(e).doesNotEndWith("n");
                    assertThat(e).doesNotStartWith("n");
                })
                .anySatisfy(e -> {
                    assertThat(e).endsWith("t");
                    assertThat(e).startsWith("f");
                })
                .allMatch(e -> e.length() < 10)
                .anyMatch(e -> e.length() == 3)
                .noneMatch(e -> e.length() < 1);
    }

    @Test
    void checkSatisfySet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five", "two", "first");
        assertThat(set).isNotNull()
                .allSatisfy(e -> {
                    assertThat(e).doesNotEndWith("n");
                    assertThat(e).doesNotStartWith("n");
                })
                .anySatisfy(e -> {
                    assertThat(e).endsWith("t");
                    assertThat(e).startsWith("f");
                })
                .allMatch(e -> e.length() < 10)
                .anyMatch(e -> e.length() == 3)
                .noneMatch(e -> e.length() < 1);
    }

    @Test
    void checkSatisfyMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five", "two", "first");
        assertThat(map).isNotNull()
                .allSatisfy((e1, e2) -> {
                    assertThat(e1).doesNotEndWith("n");
                    assertThat(e1).doesNotStartWith("n");
                    assertThat(e2).isLessThan(10);
                    assertThat(e2).isGreaterThan(-1);
                })
                .anySatisfy((e1, e2) -> {
                    assertThat(e1).endsWith("e");
                    assertThat(e1).startsWith("t");
                    assertThat(e2).isEven();
                    assertThat(e2).isPositive();
                });
    }

    @Test
    void checkFilteredArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five", "two");
        assertThat(array).filteredOn(e -> e.startsWith("th")).singleElement().isEqualTo("three");
        assertThat(array).filteredOnAssertions(e -> assertThat(e).startsWith("tw"))
                .hasSize(1)
                .singleElement()
                .isEqualTo("two");
    }

    @Test
    void checkFilteredList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five", "two");
        assertThat(list).first().isEqualTo("first");
        assertThat(list).element(0).isNotNull()
                .isEqualTo("first");
        assertThat(list).last().isNotNull()
                .isEqualTo("two");
    }

    @Test
    void checkFilteredSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "second", "five", "two");
        assertThat(set).element(0).isNotNull();
        assertThat(set).element(5).isNotNull();
    }

    @Test
    void checkFilteredMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five", "two", "first");
        assertThat(map).extractingByKeys("two").first().isEqualTo(5);
        assertThat(map).extractingByKeys("two").last().isEqualTo(5);
        assertThat(map).extractingByKeys("first").first().isEqualTo(0);
        assertThat(map).extractingByKeys("first").last().isEqualTo(0);
        assertThat(map).extractingFromEntries(Map.Entry::getKey).first().isEqualTo("four");
        assertThat(map).extractingFromEntries(Map.Entry::getKey).last().isEqualTo("second");
        assertThat(map).extractingFromEntries(Map.Entry::getValue).first().isEqualTo(3);
        assertThat(map).extractingFromEntries(Map.Entry::getValue).last().isEqualTo(1);
    }
}