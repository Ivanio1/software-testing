package se.ifmo.task3;

import lombok.Data;
import lombok.SneakyThrows;

import java.util.Set;

@Data
public class Cruiser {
    private String name;
    private Integer health;
    private Integer speed;
    private Integer fuel;
    private Integer damage;


    public Cruiser(String name, Integer health, Integer speed, Integer fuel, Integer damage) {
        this.name = name;
        this.health = health;
        this.speed = speed;
        this.fuel = fuel;
        this.damage = damage;
    }

    //Атаковать другой крейсер
    @SneakyThrows
    public void attack(Cruiser enemyCruiser) {
        if (this.health <= 0) throw new Exception("Cruiser is dead!");
        if (this.damage <= 0 || this.damage > 100) throw new Exception("Illegal damage value");
        enemyCruiser.health -= this.damage;

    }

    //разразиться электрической смертью
    @SneakyThrows
    public void eruptIntoElectricalDeath(Set<Cruiser> enemies) {
        if (this.health <= 0) throw new Exception("Cruiser is dead!");
        for (Cruiser enemyCruiser : enemies) {
            enemyCruiser.setHealth(0);
        }
        this.health = 0;
    }

    // Совершить полет
    @SneakyThrows
    public void fly(int value) {
        if (value <= 0) throw new Exception("Can't fly negative distance!");
        if (this.fuel - value < 0) throw new Exception("There is not enough fuel for the flight!");
        this.increaseSpeed();
        this.fuel -= value;
        this.decreaseSpeed();
    }

    //Увеличить скорость
    public void increaseSpeed() {
        this.speed += 100;
    }

    //Увеличить скорость
    public void decreaseSpeed() {
        this.speed -= 100;
    }

}
