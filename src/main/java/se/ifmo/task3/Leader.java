package se.ifmo.task3;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import se.ifmo.task3.enums.Pose;

@Getter
@Setter
public class Leader extends Human {

    private Pose pose;

    public Leader(String name, Integer age, Pose pose) {
        super(name, age);
        this.pose = pose;
    }

    public void changePose() {
        if (this.pose == Pose.SIT) pose = Pose.STAND;
        else pose = Pose.SIT;
    }

    @SneakyThrows
    public void saySorryToCommander(Commander commander) {
        if (!commander.isLookingOnLeader()) throw new Exception("Commander is not ready!");
        commander.setLookingOnLeader(false);
        commander.getSilence().setGotHigh(false);
    }
}
