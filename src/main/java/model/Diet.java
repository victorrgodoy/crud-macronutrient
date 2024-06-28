package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Diet {
    private double cals;
    private double proteins;
    private double carbs;
    private double fats;

}
