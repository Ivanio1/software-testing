package se.ifmo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import se.ifmo.task3.Shorts;
import se.ifmo.task3.enums.Color;
import se.ifmo.task3.enums.Size;
import se.ifmo.task3.exceptions.BrilliantAddException;
import se.ifmo.task3.exceptions.CutShortsException;

public class WorldTest {

    @Nested
    class ShortsTest {
        private Shorts shorts;

        @BeforeEach
        void init() {
            shorts = new Shorts(Color.BLACK, Size.XL);
        }

        @Test
        @DisplayName("Check ability to addBrilliants")
        public void checkAddBrilliants() {
            shorts.addBrilliants();
            assertTrue(shorts.isBrilliantsFlag());
        }

        @Test
        @DisplayName("Check exception throwing")
        public void checkException() {
            shorts.addBrilliants();
            assertThrows(BrilliantAddException.class, () -> shorts.addBrilliants());
        }

        @ParameterizedTest
        @DisplayName("Check cutting the shorts")
        @EnumSource(Size.class)
        public void checkAbilityToCutShorts(Size size) {
            shorts.setSize(size);
            if (size.equals(Size.S)) {
                assertThrows(CutShortsException.class, () -> shorts.cutShorts());
            } else {
                shorts.cutShorts();
                assertEquals(size.ordinal() - 1, shorts.getSize().ordinal());
            }
        }

        @ParameterizedTest
        @DisplayName("Check lengthening the shorts")
        @EnumSource(Size.class)
        public void checkAbilityToLengthenShorts(Size size) {
            shorts.setSize(size);
            if (size.equals(Size.XXL)) {
                assertThrows(CutShortsException.class, () -> shorts.lengthenShorts());
            } else {
                shorts.lengthenShorts();
                assertEquals(size.ordinal() + 1, shorts.getSize().ordinal());
            }
        }
    }
}
