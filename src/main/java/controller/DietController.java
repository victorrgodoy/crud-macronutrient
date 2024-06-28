package controller;

import dao.DietDAO;
import model.*;
import service.NutriServiceImp;

public class DietController {

    public DietController(){
    }

    public void createDiet(User user) {
        NutriServiceImp nutriServiceImp = NutriServiceImp.getInstance();
        DietDAO dietDAO = DietDAO.getInstance();

        try {
            String cpf = user.getCpf();
            int age = user.getAge();
            double weight = user.getWeight();
            double height = user.getHeight();
            Gender gender = user.getGender();
            ActivityLevel activityLevel = user.getActivityLevel();
            Objective objective = user.getObjective();

            double proteins = nutriServiceImp.calculateProtein(weight);
            double fats = nutriServiceImp.calculateFat(weight);
            double carbs = 0;
            double cals = 0;

            double basalRate = nutriServiceImp.calculateBasalRate(gender, weight, height, age);
            double activityFactor = nutriServiceImp.calculateActivityFactor(gender, activityLevel);

            if (objective == Objective.BULKING) {
                cals = nutriServiceImp.calculateBulking(basalRate, activityFactor);
                carbs = nutriServiceImp.calculateCarb(cals, proteins, fats);
            } else if (objective == Objective.CUTTING) {
                cals = nutriServiceImp.calculateCutting(basalRate, activityFactor);
                carbs = nutriServiceImp.calculateCarb(cals, proteins, fats);
            }

            Diet diet = Diet.builder()
                    .cals(cals)
                    .proteins(proteins)
                    .fats(fats)
                    .carbs(carbs)
                    .build();

            dietDAO.create(diet, cpf);
            System.out.println("Dieta criada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao criar a dieta. Por favor, tente novamente.");
        }
    }

}

