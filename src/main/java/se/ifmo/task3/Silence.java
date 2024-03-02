package se.ifmo.task3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import se.ifmo.task3.enums.Mood;

@Data
@AllArgsConstructor
@Builder
public class Silence {
    private Mood mood;
}
