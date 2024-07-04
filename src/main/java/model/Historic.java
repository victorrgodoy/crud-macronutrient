package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor

public class Historic {
    private final double weight;
    private final double imc;
    private final String type_imc;
    private final LocalDate date;
}

