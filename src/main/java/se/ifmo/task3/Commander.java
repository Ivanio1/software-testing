package se.ifmo.task3;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import se.ifmo.task3.enums.Color;
import se.ifmo.task3.enums.Size;
import se.ifmo.task3.exceptions.StartingBattleException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Getter
@Setter
public class Commander extends Human {
    private Shorts shorts;
    private Set<Cruiser> cruisers = new HashSet<>();
    Silence silence = new Silence(100, false);

    private boolean isLookingOnLeader;

    public Commander(String name, Integer age) {
        super(name, age);
        isLookingOnLeader = false;
    }

    public void putOnShorts() {
        shorts = new Shorts(Color.BLACK, Size.XL);
    }

    public void takeOffShorts() {
        shorts = null;
    }

    @SneakyThrows
    public void lookAtLeader() {
        this.isLookingOnLeader = true;
        silence.getHigh();

    }

    //Командир говорит слово и миллион сверкающих чудовищным вооружением звездных крейсеров разражается электрической смертью
    @SneakyThrows
    public void startBattle(Set<Cruiser> enemies) {
        if (!this.isLookingOnLeader) throw new StartingBattleException("Commander is not looking on leader!");
        if (this.cruisers.isEmpty()) throw new StartingBattleException("No cruisers for attack!");
        if (enemies.isEmpty()) throw new StartingBattleException("No cruisers to attack!");
        Iterator<Cruiser> iterator = this.cruisers.iterator();
        while (iterator.hasNext()) {
            Cruiser cruiser = iterator.next();
            cruiser.eruptIntoElectricalDeath(enemies);
            iterator.remove(); // Удалить текущий крейсер из списка
        }
    }


}
