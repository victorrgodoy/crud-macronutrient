package controller;

import dao.DietDAO;
import model.*;
import service.NutriServiceImp;

import java.time.LocalDate;

public class DietController {
    private final DietDAO dietDAO;

    public DietController(){
        this.dietDAO = DietDAO.getInstance();
    }

    public void createDiet(User user) {
        try {
            String cpf = user.getCpf();
            Diet diet = builderDiet(user);
            dietDAO.create(diet, cpf);
            System.out.println("Dieta criada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao criar a dieta. Por favor, tente novamente.");
        }
    }

    public void readDiet(String cpf) {
        try {
            Diet diet = dietDAO.read(cpf);
            System.out.println("=== Dieta do usuário ===");
            System.out.println("Data: " + diet.getDate());
            System.out.println("Calorias diária: " + diet.getCals());
            System.out.println("Proteínas: " +  diet.getProteins() + " g");
            System.out.println("Carboidratos: " + diet.getCarbs() + " g");
            System.out.println("Gorduras: " + diet.getFats() + " g");
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao ler a dieta. Por favor, tente novamente.");
        }
    }

    public void updateDiet(User user) {
        try {
            String cpf = user.getCpf();
            Diet diet = builderDiet(user);
            dietDAO.update(cpf,diet);
            System.out.println("Dieta atualizada com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao atualizar a dieta. Por favor, tente novamente.");
        }
    }

    private Diet builderDiet(User user) {
        NutriServiceImp nutriServiceImp = NutriServiceImp.getInstance();
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

        return Diet.builder()
                .cals(cals)
                .proteins(proteins)
                .fats(fats)
                .carbs(carbs)
                .date(LocalDate.now())
                .build();
    }
}

