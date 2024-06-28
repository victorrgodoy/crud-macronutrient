package cli;

import service.NutriServiceImp;
import java.util.Scanner;

public class NutriCLI {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        NutriServiceImp nutriServiceImp = NutriServiceImp.getInstance();
        UserInterface userInterface = new UserInterface(scanner);

        while (true) {
            UserInterface.displayMenu();
            int choice;

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 0:
                    System.out.println("Encerrando o programa.");
                    scanner.close();
                    return;
                case 1:
                    userInterface.createUser();
                    break;
                case 4:
                    userInterface.deleteUser();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }











//    private static void calculateMacronutrients(){
//        System.out.print("Digite o peso (kg): ");
//        double weight = SCANNER.nextDouble();
//
//        System.out.print("Digite a altura (m): ");
//        double height = SCANNER.nextDouble();
//
//        System.out.print("Digite a idade: ");
//        int age = SCANNER.nextInt();
//
//        System.out.print("Digite o gênero (MALE ou FEMALE): ");
//        SCANNER.nextLine();
//        Gender gender = Gender.valueOf(SCANNER.nextLine().trim().toUpperCase());
//
//        System.out.print("Digite o nível de atividade diária ( LOW, MEDIUM, HIGH ): ");
//        ActivityLevel activityLevel = ActivityLevel.valueOf(SCANNER.nextLine().trim().toUpperCase());
//
//        double imc = NUTRI_SERVICE_IMP.calculateIMC(weight, height);
//        String typeImc = NUTRI_SERVICE_IMP.typeIMC(imc);
//        double basalRate = NUTRI_SERVICE_IMP.calculateBasalRate(gender, weight, height, age);
//        double activityFactor = NUTRI_SERVICE_IMP.calculateActivityFactor(gender, activityLevel);
//
//        System.out.println("Bulking ou Cutting? ");
//        String choice = SCANNER.nextLine().trim().toUpperCase();
//        double bulkingCalories = 0;
//        double cuttingCalories = 0;
//        double proteins = NUTRI_SERVICE_IMP.calculateProtein(weight);
//        double fats = NUTRI_SERVICE_IMP.calculateFat(weight);
//        double carbs = 0;
//
//        if (choice.equals("BULKING")){
//            bulkingCalories = NUTRI_SERVICE_IMP.calculateBulking(basalRate, activityFactor);
//            carbs = NUTRI_SERVICE_IMP.calculateCarb(bulkingCalories, proteins,fats);
//        } else if (choice.equals("CUTTING")) {
//            cuttingCalories = NUTRI_SERVICE_IMP.calculateCutting(basalRate, activityFactor);
//            carbs = NUTRI_SERVICE_IMP.calculateCarb(cuttingCalories, proteins,fats);
//        }
//
//        System.out.println("Seu IMC: " + imc);
//        System.out.println("Nível IMC: " + typeImc);
//        System.out.println("Sua taxa metabólica basal é " + basalRate);
//        System.out.println("Fator de atividade: " + activityFactor);
//        System.out.println("Calorias bulking: " + bulkingCalories);
//        System.out.println("Calorias cutting: " + cuttingCalories);
//        System.out.println("Proteínas: " + proteins);
//        System.out.println("Gorduras: " + fats);
//        System.out.println("Carboidratos: " + carbs);
//
//    }


}

