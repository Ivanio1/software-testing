package se.ifmo.task3;

import lombok.AllArgsConstructor;
import lombok.Data;
import se.ifmo.task3.enums.Mood;
import se.ifmo.task3.enums.Pose;
import se.ifmo.task3.enums.Race;

import java.util.Set;

@Data
@AllArgsConstructor
public abstract class Human {
    private String name;
    private Integer age;
    private Pose pose;
    private Race race;
    private Place place;
    private Mood mood;
    private Set<Clothes> clothes;
    private boolean isSpeaking = false;

    public void speak() {
        isSpeaking = true;
    }

    public void silent() {
        isSpeaking = false;
    }

    public abstract void listenToApologies(Human human);

    public abstract void listenAboutMommy(Human human);
}
