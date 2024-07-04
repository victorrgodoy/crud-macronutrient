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

    @Override
    public String toString(){
        return "Histórico do usuário: " + "\n" +
                "Data: " + this.date+ ", Peso: " + this.weight + ", IMC: " + this.imc + ", Tipo IMC: " + this.type_imc + "\n";
    }
}

