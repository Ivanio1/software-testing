package se.ifmo.task3;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Silence {
    private Integer volume;
    private boolean gotHigh; // Висит в воздухе над столом переговоров

    public void getHigh() {
        gotHigh = true;
    }

}
