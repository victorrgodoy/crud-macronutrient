package controller;

import dao.DietDAO;
import model.Diet;
import model.Objective;
import model.User;
import service.NutriService;
import service.NutriServiceImp;

import java.time.LocalDate;

public class DietController {
    private final DietDAO dietDAO;
    private final NutriService nutriService;

    public DietController() {
        this.dietDAO = DietDAO.getInstance();
        this.nutriService = NutriServiceImp.getInstance();
    }

    public void createDiet(User user) {
        try {
            String cpf = user.getCpf();
            Diet diet = buildDiet(user);
            dietDAO.create(diet, cpf);
            System.out.println("Dieta criada com sucesso para o usuário.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao criar a dieta para o usuário.");
        }
    }

    public void readDiet(String cpf) {
        try {
            Diet diet = dietDAO.read(cpf);
            if (diet == null) {
                System.out.println("Nenhuma dieta encontrada para o usuário.");
                return;
            }
            displayDietData(diet);
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao ler a dieta do usuário. Por favor, tente novamente.");
        }
    }

    public void updateDiet(User user) {
        try {
            String cpf = user.getCpf();
            Diet updatedDiet = buildDiet(user);
            dietDAO.update(cpf, updatedDiet);
            System.out.println("Dieta atualizada com sucesso para o usuário.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao atualizar a dieta para o usuário.");
        }
    }

    private Diet buildDiet(User user) {
        int age = user.getAge();
        double weight = user.getWeight();
        double height = user.getHeight();
        Objective objective = user.getObjective();

        double proteins = nutriService.calculateProtein(weight);
        double fats = nutriService.calculateFat(weight);
        double carbs = 0;
        double cals = 0;

        double basalRate = nutriService.calculateBasalRate(user.getGender(), weight, height, age);
        double activityFactor = nutriService.calculateActivityFactor(user.getGender(), user.getActivityLevel());

        if (objective == Objective.BULKING) {
            cals = nutriService.calculateBulking(basalRate, activityFactor);
            carbs = nutriService.calculateCarb(cals, proteins, fats);
        } else if (objective == Objective.CUTTING) {
            cals = nutriService.calculateCutting(basalRate, activityFactor);
            carbs = nutriService.calculateCarb(cals, proteins, fats);
        }

        return Diet.builder()
                .cals(cals)
                .proteins(proteins)
                .fats(fats)
                .carbs(carbs)
                .date(LocalDate.now())
                .build();
    }

    private void displayDietData(Diet diet) {
        System.out.println("=== Dieta do usuário ===");
        System.out.println("Data: " + diet.getDate());
        System.out.println("Calorias diárias: " + diet.getCals());
        System.out.println("Proteínas: " + diet.getProteins() + " g");
        System.out.println("Carboidratos: " + diet.getCarbs() + " g");
        System.out.println("Gorduras: " + diet.getFats() + " g");
    }
}

