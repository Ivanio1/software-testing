package se.ifmo.task3;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import se.ifmo.task3.enums.ClothesAttribute;
import se.ifmo.task3.enums.Color;
import se.ifmo.task3.enums.Mood;
import se.ifmo.task3.enums.Pose;
import se.ifmo.task3.enums.Race;
import se.ifmo.task3.enums.Size;
import se.ifmo.task3.exceptions.ClothesAlreadyPutException;
import se.ifmo.task3.exceptions.StartingBattleException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Commander extends Human {
    private Set<Cruiser> cruisers = new HashSet<>();
    private List<Human> angryFor = new ArrayList<>();

    public Commander(String name, Integer age, Pose pose, Race race, Place place, Mood mood, Set<Clothes> clothes) {
        super(name, age, pose, race, place, mood, clothes, false);
    }

    @SneakyThrows
    public void putOnShorts() {
        if (this.getClothes().stream().anyMatch(i -> i instanceof Shorts)) {
            throw new ClothesAlreadyPutException("double shorts is cringe! try again");
        }
        getClothes().add(new Shorts(Color.GREEN, Size.XL, Set.of(ClothesAttribute.BRILLIANT)));
    }

    public void takeOffShorts() {
        getClothes().stream().filter(i -> i instanceof Shorts).toList().forEach(getClothes()::remove);
    }


    //Командир говорит слово и миллион сверкающих чудовищным вооружением звездных крейсеров разражается электрической смертью
    @SneakyThrows
    public Human startBattle(Commander commander) {
        var enemies = commander.getCruisers();
        if (this.cruisers.isEmpty()) throw new StartingBattleException("No cruisers for attack!");
        if (enemies.isEmpty()) throw new StartingBattleException("No cruisers to attack!");
        var enemiesList = enemies.stream().toList();
        var cruisersList = cruisers.stream().toList();
        boolean enemiesAlive = enemiesList.stream().anyMatch(i -> i.getHealth() > 0);
        boolean cruisersAlive = cruisersList.stream().anyMatch(i -> i.getHealth() > 0);
        int i = 0;
        int j = 0;
        while (enemiesAlive && cruisersAlive) {
            while(cruisersList.get(i).getHealth() <= 0) {
                i++;
                i = i >= cruisersList.size() ? 0 : i;
            }
            while(enemiesList.get(j).getHealth() <= 0) {
                j++;
                j = j >= enemiesList.size() ? 0 : j;
            };
            cruisersList.get(i).attack(enemiesList.get(j));
            if(enemiesList.get(j).getHealth() > 0) {
                enemiesList.get(j).attack(cruisersList.get(i));
            }
            i++;
            j++;
            i = i >= cruisersList.size() ? 0 : i;
            j = j >= enemiesList.size() ? 0 : j;
            enemiesAlive = enemiesList.stream().anyMatch(cr -> cr.getHealth() > 0);
            cruisersAlive = cruisersList.stream().anyMatch(cr -> cr.getHealth() > 0);
        }
        if (cruisersList.stream().anyMatch(cr -> cr.getHealth() > 0)) {
            return commander;
        }
        return this;
    }

    @Override
    public void listenToApologies(Human human) {
        angryFor.remove(human);
        if (angryFor.isEmpty()) {
            setMood(Mood.HAPPY);
        }
    }

    @Override
    public void listenAboutMommy(Human human) {
        angryFor.add(human);
        setMood(Mood.ANGRY);
    }
}
