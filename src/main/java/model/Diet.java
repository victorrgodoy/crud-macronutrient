package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class Diet {
    private double cals;
    private double proteins;
    private double carbs;
    private double fats;
    private LocalDate date;

}
