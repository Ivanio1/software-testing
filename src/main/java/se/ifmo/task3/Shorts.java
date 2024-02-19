package se.ifmo.task3;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import se.ifmo.task3.enums.Color;
import se.ifmo.task3.enums.Size;
import se.ifmo.task3.exceptions.BrilliantAddException;

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
}
