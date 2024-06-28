package controller;

import dao.UserDAO;
import model.*;
import java.util.Scanner;

public class UserController {
    private final Scanner scanner;
    private final UserDAO userDAO;

    public UserController(Scanner scanner){
        this.scanner = scanner;
        this.userDAO = UserDAO.getInstance();
    }

    public void createUser() {
        DietController dietController = new DietController();
        HistoricController historicController = new HistoricController();

        try {
            System.out.println("=== Novo usuário ===");
            String name = readName();
            String cpf = readCpf();
            int age = readAge();
            double weight = readWeight();
            double height = readHeight();
            Gender gender = readGender();
            Objective objective = readObjective();
            ActivityLevel activityLevel = readActivityLevel();

            User user = User.builder()
                    .cpf(cpf)
                    .name(name)
                    .age(age)
                    .weight(weight)
                    .height(height)
                    .gender(gender)
                    .objective(objective)
                    .activityLevel(activityLevel)
                    .build();

            userDAO.create(user);
            historicController.createHistoric(user);
            dietController.createDiet(user);
            System.out.println("Usuário criado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao criar o usuário. Por favor, tente novamente.");
        }
    }



    public void deleteUser() {
        try {
            System.out.println("=== Deletando usuário ===");

            System.out.print("Digite o CPF: ");
            String cpf = scanner.next().trim();

            if (!cpf.matches("[0-9]+") || cpf.length() != 11) {
                throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos numéricos.");
            }

            System.out.print("Você tem certeza que deseja deletar o usuário (sim / nao): ");
            String choice = scanner.next().trim().toUpperCase();

            if (choice.equals("SIM")) {
                userDAO.delete(cpf);
                System.out.println("Usuário deletado com sucesso!");
            } else if (choice.equals("NAO")) {
                System.out.println("Operação de exclusão cancelada.");
            } else {
                throw new IllegalArgumentException("Opção inválida. Use 'sim' para confirmar ou 'nao' para cancelar.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao deletar o usuário. Por favor, tente novamente.");
        }
    }





    //------------------------------------------------------//
    private String readName() {
        while (true) {
            System.out.print("Digite o nome: ");
            String name = scanner.nextLine().trim();
            if (!name.matches("[a-zA-Z ]+")) {
                System.out.println("Nome inválido. Use apenas letras.");
            } else {
                return name;
            }
        }
    }

    private String readCpf() {
        while (true) {
            System.out.print("Digite o CPF: ");
            String cpf = scanner.nextLine().trim();
            if (!cpf.matches("[0-9]+") || cpf.length() != 11) {
                System.out.println("CPF inválido. Deve conter exatamente 11 dígitos numéricos.");
            } else {
                return cpf;
            }
        }
    }

    private int readAge() {
        while (true) {
            System.out.print("Digite a idade: ");
            String input = scanner.nextLine().trim();
            try {
                int age = Integer.parseInt(input);
                if (age <= 0 || age >= 150) {
                    System.out.println("Idade inválida. Insira um valor maior que 0 e menor que 150.");
                    continue;
                }
                return age;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Formato de idade inválido. Por favor, insira um número inteiro.");
            }
        }
    }

    private double readWeight() {
        while (true) {
            System.out.print("Digite o peso (kg): ");
            String input = scanner.nextLine().trim();
            try {
                double weight = Double.parseDouble(input);
                if (weight <= 0) {
                    System.out.println("Erro: Peso deve ser maior que zero. Tente novamente.");
                    continue;
                }
                return weight;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Formato de peso inválido. Tente novamente.");
            }
        }
    }

    private double readHeight() {
        while (true) {
            System.out.print("Digite a altura (cm): ");
            String input = scanner.nextLine().trim();
            try {
                double height = Double.parseDouble(input);
                if (height <= 0) {
                    System.out.println("Erro: Altura deve ser maior que zero. Tente novamente.");
                    continue;
                }
                return height;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Formato de altura inválido. Tente novamente.");
            }
        }
    }

    private Gender readGender() {
        while (true) {
            System.out.print("Digite o gênero (MALE ou FEMALE): ");
            try {
                return Gender.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: Gênero inválido. Tente novamente.");
            }
        }
    }

    private Objective readObjective() {
        while (true) {
            System.out.print("Digite o objetivo (BULKING ou CUTTING): ");
            try {
                return Objective.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: Objetivo inválido. Tente novamente.");
            }
        }
    }

    private ActivityLevel readActivityLevel() {
        while (true) {
            System.out.print("Digite o nível de atividade diária (LOW, MEDIUM, HIGH): ");
            try {
                return ActivityLevel.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: Nível de atividade inválido. Tente novamente.");
            }
        }
    }

}
