package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class User {
    private final String cpf;
    private final String name;
    private final int age;
    private final double weight;
    private final double height;
    private final Gender gender;
    private final Objective objective;
    private final ActivityLevel activityLevel;
}
