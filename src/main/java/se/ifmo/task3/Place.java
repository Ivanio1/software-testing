package se.ifmo.task3;

import lombok.Getter;
import lombok.Value;
import se.ifmo.task3.enums.Mood;

import java.util.List;

@Value
@Getter
public class Place {
    List<Human> humans;

    public Silence getSilenceStatus() {
        if(humans.stream().anyMatch(Human::isSpeaking)) {
            return null;
        }
        boolean scary = humans.stream().map(Human::getMood).allMatch(m -> m == Mood.ANGRY);
        return new Silence(scary ? Mood.SCARY : Mood.HAPPY);
    }

    public void enterPlace(Human human) {
        human.setPlace(this);
        humans.add(human);
    }
}
