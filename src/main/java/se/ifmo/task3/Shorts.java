package se.ifmo.task3;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import se.ifmo.task3.enums.Color;
import se.ifmo.task3.enums.Size;
import se.ifmo.task3.exceptions.BrilliantAddException;
import se.ifmo.task3.exceptions.CutShortsException;

@Getter
@Setter
public class Shorts extends Clothes {

    private boolean brilliantsFlag = false;

    public Shorts(Color color, Size size) {
        super(color, size);
    }

    @SneakyThrows
    public void addBrilliants() {
        if (!brilliantsFlag) {
            brilliantsFlag = true;
        } else throw new BrilliantAddException("Brilliants are already on!");
    }


    public boolean isBrilliantsFlag() {
        return brilliantsFlag;
    }

    //Укоротить шорты
    @SneakyThrows
    public void cutShorts() {
        if (getSize().ordinal() - 1 < 0)
            throw new CutShortsException("Unable to cut shorts, it is already the smallest size!");
        setSize(Size.values()[getSize().ordinal() - 1]);
    }

    //Удлинить шорты
    @SneakyThrows
    public void lengthenShorts() {
        if (getSize().ordinal() + 1 >= Size.values().length)
            throw new CutShortsException("Unable to lengthen shorts, it is already the biggest size!");
        setSize(Size.values()[getSize().ordinal() + 1]);
    }
}
