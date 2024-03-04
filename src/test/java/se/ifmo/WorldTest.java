package se.ifmo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import se.ifmo.task3.*;
import se.ifmo.task3.enums.ClothesAttribute;
import se.ifmo.task3.enums.Color;
import se.ifmo.task3.enums.Mood;
import se.ifmo.task3.enums.Pose;
import se.ifmo.task3.enums.Race;
import se.ifmo.task3.enums.Size;
import se.ifmo.task3.exceptions.BrilliantAddException;
import se.ifmo.task3.exceptions.ClothesAlreadyPutException;
import se.ifmo.task3.exceptions.CutShortsException;
import se.ifmo.task3.exceptions.StartingBattleException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class WorldTest {

    @Nested
    class ShortsTest {
        private Shorts shorts;

        @BeforeEach
        void init() {
            shorts = new Shorts(Color.BLACK, Size.XL, new HashSet<>());
        }

        @Test
        @DisplayName("Check attribute")
        public void checkAddBrilliants() {
            shorts.addBrilliants();
            assertTrue(shorts.getClothesAttributes().contains(ClothesAttribute.BRILLIANT));
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

    @Nested
    class CruiserTest {
        Cruiser cruiser;
        Cruiser enemy;

        @BeforeEach
        void init() {
            cruiser = new Cruiser("main", 100, 100, 100, 35);
            enemy = new Cruiser("enemy", 100, 100, 100, 10);
        }

        @Test
        @DisplayName("Check a really long flight")
        void checkTooBigValueToFly() {
            Throwable exception = assertThrows(Exception.class, () -> cruiser.fly(101));
            assertEquals("There is not enough fuel for the flight!", exception.getMessage());
        }

        @Test
        @DisplayName("Check negative value of flight")
        void checkNegativeValueOfFlight() {
            Throwable exception = assertThrows(Exception.class, () -> cruiser.fly(-1));
            assertEquals("Can't fly negative distance!", exception.getMessage());
        }

        @Test
        @DisplayName("Check zero value of flight")
        void checkZeroValueOfFlight() {
            Throwable exception = assertThrows(Exception.class, () -> cruiser.fly(0));
            assertEquals("Can't fly negative distance!", exception.getMessage());
        }

        @Test
        @DisplayName("Check normal values of flight")
        void checkNormalFlight() {
            assertAll(
                    () -> {
                        cruiser.fly(7);
                        assertEquals(93, cruiser.getFuel());
                    },
                    () -> {
                        cruiser.fly(23);
                        assertEquals(70, cruiser.getFuel());
                    },
                    () -> {
                        cruiser.fly(70);
                        assertEquals(0, cruiser.getFuel());
                    }
            );

            Throwable exception = assertThrows(Exception.class, () -> cruiser.fly(0));
            assertEquals("Can't fly negative distance!", exception.getMessage());
        }

        @Test
        @DisplayName("Check attack without health")
        public void checkNoHealth() {
            cruiser.setHealth(0);
            assertThrows(Exception.class, () -> cruiser.attack(enemy));
        }

        @Test
        @DisplayName("Check illegal damage value")
        public void checkWrongDamage() {
            cruiser.setDamage(-100);
            assertThrows(Exception.class, () -> cruiser.attack(enemy));

            cruiser.setDamage(101);
            assertThrows(Exception.class, () -> cruiser.attack(enemy));
        }

        @Test
        @DisplayName("Check attack")
        public void checkNormalAttack() {
            cruiser.attack(enemy);
            assertEquals(65, enemy.getHealth());

            enemy.attack(cruiser);
            assertEquals(90, cruiser.getHealth());
        }

        @Test
        @DisplayName("Check eruptIntoElectricalDeath without health")
        public void checkEruptNoHealth() {
            cruiser.setHealth(0);
            Set<Cruiser> enemies = new HashSet<>();
            enemies.add(new Cruiser("1", 100, 90, 90, 55));
            Throwable exception = assertThrows(Exception.class, () -> cruiser.eruptIntoElectricalDeath(enemies));
            assertEquals("Cruiser is dead!", exception.getMessage());
        }

        @Test
        @DisplayName("Check eruptIntoElectricalDeath without enemies")
        public void checkEruptNoEnemies() {
            Throwable exception = assertThrows(Exception.class, () -> cruiser.eruptIntoElectricalDeath(new HashSet<>()));
            assertEquals("No cruisers to attack!", exception.getMessage());
        }

        @Test
        @DisplayName("Check eruptIntoElectricalDeath")
        public void checkEruptIntoElectricalDeath() {
            Set<Cruiser> enemies = new HashSet<>();
            enemies.add(new Cruiser("1", 100, 90, 90, 55));
            enemies.add(new Cruiser("2", 100, 95, 60, 50));
            enemies.add(new Cruiser("3", 100, 100, 100, 45));
            cruiser.eruptIntoElectricalDeath(enemies);
            for (Cruiser enemyCruiser : enemies) {
                assertEquals(0, enemyCruiser.getHealth());
            }
            assertEquals(0, cruiser.getHealth());
        }


    }

    @Nested
    class CommanderTest {

        Commander commander1;
        Commander commander2;
        Set<Cruiser> enemies;

        @BeforeEach
        void init() {
            commander1 = new Commander(
                    "Commander of вл'хургов",
                    60, Pose.SIT, Race.VLHURR,
                    null,
                    Mood.ANGRY,
                    new HashSet<>()
            );
            commander2 = new Commander(
                    "Commander of other",
                    60, Pose.SIT, Race.VLHURR,
                    null,
                    Mood.ANGRY,
                    new HashSet<>()
            );
            enemies = new HashSet<>();
            commander2.setCruisers(enemies);
        }

        @Test
        @DisplayName("Check putting on shorts on shorts")
        public void checkPuttingShortsOn(){
            commander1.putOnShorts();
            Throwable exception = assertThrows(ClothesAlreadyPutException.class, () -> commander1.putOnShorts());
            assertEquals("double shorts is cringe! try again", exception.getMessage());
        }

        @Test
        @DisplayName("Check putting on shorts")
        public void checkPuttingShorts() {
            commander1.putOnShorts();
            Set<ClothesAttribute> attributes = new HashSet<>();
            attributes.add(ClothesAttribute.BRILLIANT);
            Shorts shorts = new Shorts(Color.GREEN, Size.XL, attributes);
            assertEquals(
                    Optional.of(shorts),
                    commander1.getClothes().stream().filter(i -> i instanceof Shorts).findFirst()
            );
        }

        @Test
        @DisplayName("Check taking off shorts")
        public void checkTakingOffShorts() {
            commander1.takeOffShorts();
            assert (commander1.getClothes().stream().noneMatch(i -> i instanceof Shorts));
        }
        @Test
        @DisplayName("Check starting battle without cruisers")
        public void checkStartingWithoutCruisers() {
            enemies.add(new Cruiser("1", 100, 90, 90, 55));
            Throwable exception = assertThrows(StartingBattleException.class, () -> commander1.startBattle(commander2));
            assertEquals("No cruisers for attack!", exception.getMessage());
        }

        @Test
        @DisplayName("Check starting battle without enemies")
        public void checkStartingWithoutEnemies() {
            commander1.getCruisers().add(new Cruiser("1", 100, 90, 90, 55));
            Throwable exception = assertThrows(StartingBattleException.class, () -> commander1.startBattle(commander2));
            assertEquals("No cruisers to attack!", exception.getMessage());
        }

        @Test
        @DisplayName("Check fight")
        public void checkFight() {
            Set<Cruiser> enemies = new HashSet<>();
            enemies.add(new Cruiser("1", 100, 90, 90, 55));
            enemies.add(new Cruiser("2", 100, 95, 60, 50));
            enemies.add(new Cruiser("3", 100, 100, 100, 45));
            Commander commander1 = new Commander(
                    "c1", 1, Pose.STAND, Race.GUVNUTT, null, Mood.ANGRY, null
            );
            commander1.setCruisers(enemies);
            Set<Cruiser> alies = new HashSet<>();
            alies.add(new Cruiser("1", 100, 90, 90, 65));
            alies.add(new Cruiser("2", 100, 95, 60, 60));
            alies.add(new Cruiser("3", 100, 100, 100, 65));
            Commander commander2 = new Commander(
                    "c1", 1, Pose.STAND, Race.GUVNUTT, null, Mood.ANGRY, null
            );
            commander2.setCruisers(alies);

            assertEquals(commander2.startBattle(commander2), commander1);
        }
    }

    @Nested
    class MoodTest {
        Commander commander1;
        Commander commander2;

        @BeforeEach
        void init() {
            commander1 = new Commander(
                    "Commander of вл'хургов",
                    60, Pose.SIT, Race.VLHURR,
                    null,
                    Mood.HAPPY,
                    new HashSet<>()
            );
            commander2 = new Commander(
                    "Commander of вл'хургов",
                    60, Pose.SIT, Race.VLHURR,
                    null,
                    Mood.HAPPY,
                    new HashSet<>()
            );
        }

        @Test
        @DisplayName("Check listening words about mommy")
        void checkListenToWordsAboutMommy() {
            commander1.listenAboutMommy(commander2);
            assertEquals(commander1.getMood(), Mood.ANGRY);
            assertEquals(commander1.getAngryFor(), List.of(commander2));
        }

        @Test
        @DisplayName("Check listening apologies")
        void checkListenToApologies() {
            commander1.listenAboutMommy(commander2);
            assertEquals(commander1.getMood(), Mood.ANGRY);
            assertEquals(commander1.getAngryFor(), List.of(commander2));

            commander1.listenToApologies(commander2);
            assertEquals(commander1.getMood(), Mood.HAPPY);
            assertEquals(commander1.getAngryFor(), List.of());
        }
    }

    @Nested
    class PlaceTest {
        Commander commander1;
        Commander commander2;
        Commander commander3;

        @BeforeEach
        void init() {
            commander1 = new Commander(
                    "Commander of вл'хургов",
                    60, Pose.SIT, Race.VLHURR,
                    null,
                    Mood.HAPPY,
                    new HashSet<>()
            );
            commander2 = new Commander(
                    "Commander of f1",
                    60, Pose.SIT, Race.VLHURR,
                    null,
                    Mood.HAPPY,
                    new HashSet<>()
            );
            commander3 = new Commander(
                    "Commander of f2",
                    60, Pose.SIT, Race.VLHURR,
                    null,
                    Mood.HAPPY,
                    new HashSet<>()
            );
        }

        @Test
        @DisplayName("Check silence place mood")
        void checkHappyMood() {
            Place bar = new Place();
            bar.enterPlace(commander1);
            bar.enterPlace(commander2);
            bar.enterPlace(commander3);

            assertEquals(bar.getSilenceStatus().getMood(), Mood.HAPPY);
        }

        @Test
        @DisplayName("Check silence place mood")
        void checkScarySilence() {
            Place bar = new Place();
            bar.enterPlace(commander1);
            bar.enterPlace(commander2);
            bar.enterPlace(commander3);
            commander1.setMood(Mood.ANGRY);

            assertEquals(bar.getSilenceStatus().getMood(), Mood.SCARY);
        }


        @Test
        @DisplayName("Check no silence place mood")
        void checkNoSilence() {
            Place bar = new Place();
            bar.enterPlace(commander1);
            bar.enterPlace(commander2);
            bar.enterPlace(commander3);
            commander1.speak();

            assertNull(bar.getSilenceStatus());
        }

        @Test
        @DisplayName("Check turn silent")
        void checkSilent() {
           commander1.silent();
            assertFalse(commander1.isSpeaking());
        }

        @Test
        public void testConstructor() {
            Silence silence = new Silence(Mood.HAPPY);
            assertNotNull(silence);
            assertEquals(Mood.HAPPY, silence.getMood());
        }
        @Test
        public void testGetMood() {
            Silence silence = new Silence(Mood.ANGRY);
            assertEquals(Mood.ANGRY, silence.getMood());
        }

        @Test
        public void testSetMood() {
            Silence silence = new Silence(Mood.SCARY);
            silence.setMood(Mood.ANGRY);
            assertEquals(Mood.ANGRY, silence.getMood());
        }


        @Test
        public void testBuilder() {
            Silence silence = Silence.builder().mood(Mood.SCARY).build();
            assertNotNull(silence);
            assertEquals(Mood.SCARY, silence.getMood());
        }
    }
}
