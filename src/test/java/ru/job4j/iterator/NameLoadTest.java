package ru.job4j.iterator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("collection contains no data");
    }

    @Test
    void checkNotContainSymbol() {
        String[] names = {"city = Yekaterinburg", "street : Tsiolkovskogo", "house = 27"};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain the symbol")
                .hasMessageContaining("street : Tsiolkovskogo");
    }

    @Test
    void checkNotContainKey() {
        String[] names = {"city = Yekaterinburg", " = Tsiolkovskogo", "house = 27"};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key")
                .hasMessageContaining(" = Tsiolkovskogo");
    }

    @Test
    void checkNotContainValue() {
        String[] names = {"city = Yekaterinburg", "street = ", "house = 27"};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value")
                .hasMessageContaining("street = ");
    }

    @Test
    void checkNamesArrayEmpty() {
        String[] names = {};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Names array is empty");
    }
}