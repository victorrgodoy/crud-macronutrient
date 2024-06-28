package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class Historic {
    private final double weight;
    private final double imc;
    private final String levelIMC;
    private final LocalDate date;
}
