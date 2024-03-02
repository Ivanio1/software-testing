package se.ifmo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import se.ifmo.task3.Commander;
import se.ifmo.task3.Cruiser;
import se.ifmo.task3.Leader;
import se.ifmo.task3.Shorts;
import se.ifmo.task3.enums.Color;
import se.ifmo.task3.enums.Pose;
import se.ifmo.task3.enums.Race;
import se.ifmo.task3.enums.Size;
import se.ifmo.task3.exceptions.BrilliantAddException;
import se.ifmo.task3.exceptions.CutShortsException;
import se.ifmo.task3.exceptions.StartingBattleException;

import java.util.HashSet;
import java.util.Set;

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
    class LeaderTest {

        Leader leader;
        Commander commander;

        @BeforeEach
        void init() {
            leader = new Leader("Leader of г'гувнуттов", 45, Pose.STAND, Race.GUVNUTT);
            commander = new Commander("Commander of вл'хургов", 60);
        }

        @Test
        @DisplayName("Check changing pose")
        public void checkPose() {
            assertEquals(Pose.STAND, leader.getPose());
            leader.changePose();
            assertEquals(Pose.SIT, leader.getPose());

        }

        @Test
        @DisplayName("Check commander not ready")
        public void checkCommander() {
            assertThrows(Exception.class, () -> leader.saySorryToCommander(commander));
        }

        @Test
        @DisplayName("Check commander is ready")
        public void checkCommanderIsReady() {
            commander.lookAtLeader();
            leader.saySorryToCommander(commander);
            assertAll(
                    () -> assertFalse(commander.isLookingOnLeader()),
                    () -> assertFalse(commander.getSilence().isGotHigh())
            );
        }

    }

    @Nested
    class CommanderTest {

        Commander commander;
        Set<Cruiser> enemies;

        @BeforeEach
        void init() {
            commander = new Commander("Commander of вл'хургов", 60);
            enemies = new HashSet<>();
        }

        @Test
        @DisplayName("Check putting on shorts")
        public void checkPuttingShorts() {
            commander.putOnShorts();
            Shorts shorts = new Shorts(Color.BLACK, Size.XL);
            assertEquals(shorts, commander.getShorts());
        }

        @Test
        @DisplayName("Check taking off shorts")
        public void checkTakingOffShorts() {
            commander.takeOffShorts();
            assertNull(commander.getShorts());
        }

        @Test
        @DisplayName("Check looking at leader")
        public void checkLooking() {
            commander.lookAtLeader();
            assertAll(
                    () -> assertTrue(commander.isLookingOnLeader()),
                    () -> assertTrue(commander.getSilence().isGotHigh())
            );
        }

        @Test
        @DisplayName("Check starting battle without looking on leader")
        public void checkStartingWithoutLooking() {
            Throwable exception = assertThrows(StartingBattleException.class, () -> commander.startBattle(enemies));
            assertEquals("Commander is not looking on leader!", exception.getMessage());
        }

        @Test
        @DisplayName("Check starting battle without cruisers")
        public void checkStartingWithoutCruisers() {
            commander.lookAtLeader();
            enemies.add(new Cruiser("1", 100, 90, 90, 55));
            Throwable exception = assertThrows(StartingBattleException.class, () -> commander.startBattle(enemies));
            assertEquals("No cruisers for attack!", exception.getMessage());
        }

        @Test
        @DisplayName("Check starting battle without enemies")
        public void checkStartingWithoutEnemies() {
            commander.lookAtLeader();
            commander.getCruisers().add(new Cruiser("1", 100, 90, 90, 55));
            Throwable exception = assertThrows(StartingBattleException.class, () -> commander.startBattle(enemies));
            assertEquals("No cruisers to attack!", exception.getMessage());
        }


        @Test
        @DisplayName("Check starting battle")
        public void checkEStartingBattle() {
            commander.lookAtLeader();
            Set<Cruiser> enemies = new HashSet<>();
            enemies.add(new Cruiser("1", 100, 90, 90, 55));
            enemies.add(new Cruiser("2", 100, 95, 60, 50));
            enemies.add(new Cruiser("3", 100, 100, 100, 45));

            commander.getCruisers().add(new Cruiser("1", 100, 90, 90, 55));
            commander.getCruisers().add(new Cruiser("2", 100, 95, 60, 50));
            commander.getCruisers().add(new Cruiser("3", 100, 100, 100, 45));
            commander.startBattle(enemies);

            for (Cruiser enemyCruiser : enemies) {
                assertEquals(0, enemyCruiser.getHealth());
            }

            assertTrue(commander.getCruisers().isEmpty());

        }
    }
}
