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

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Commander extends Human {
    private Set<Cruiser> cruisers = new HashSet<>();
    private List<Human> angryFor;

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
        while(!enemies.isEmpty() || !this.cruisers.isEmpty()) {
            for(int i = 0; i < Math.max(enemies.size(), cruisers.size()); i++) {
                if(i < cruisersList.size()) {
                    cruisersList.get(i).attack(enemiesList.get(i % enemiesList.size()), enemiesList);
                }
                if(i < enemiesList.size()) {
                    enemiesList.get(i).attack(cruisersList.get(i % cruisersList.size()), cruisersList);
                }
            }
        }
        if(cruisersList.isEmpty()) {
            return commander;
        }
        return this;
    }

    @Override
    public void listenToApologies(Human human) {
        angryFor.remove(human);
    }

    @Override
    public void listenAboutMommy(Human human) {
        angryFor.add(human);
    }
}
