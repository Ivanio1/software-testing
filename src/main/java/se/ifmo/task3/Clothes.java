package se.ifmo.task3;

import lombok.AllArgsConstructor;
import lombok.Data;
import se.ifmo.task3.enums.ClothesAttribute;
import se.ifmo.task3.enums.Color;
import se.ifmo.task3.enums.Size;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class Clothes {
    private Color color;
    private Size size;
    Set<ClothesAttribute> clothesAttributes;
}
