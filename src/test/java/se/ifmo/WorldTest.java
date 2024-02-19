package se.ifmo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import se.ifmo.task3.Shorts;
import se.ifmo.task3.enums.Color;
import se.ifmo.task3.enums.Size;
import se.ifmo.task3.exceptions.BrilliantAddException;

public class WorldTest {

    @Nested
    class ShortsTest{
        private Shorts shorts;

        @BeforeEach
        void init(){
            shorts = new Shorts(Color.BLACK, Size.XL);
        }

        @Test
        @DisplayName("Check ability to addBrilliants")
        public void checkAddBrilliants(){
            shorts.addBrilliants();
            assertTrue(shorts.isBrilliantsFlag());
        }

        @Test
        @DisplayName("Check exception throwing")
        public void checkException(){
            shorts.addBrilliants();
            assertThrows(BrilliantAddException.class,()-> shorts.addBrilliants());
        }
    }
}
