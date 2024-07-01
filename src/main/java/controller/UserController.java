package controller;

import dao.UserDAO;
import model.*;
import util.InputReader;

import java.util.Scanner;

public class UserController {
    private final Scanner scanner;
    private final UserDAO userDAO;
    private final InputReader inputReader;
    private final DietController dietController;

    public UserController(Scanner scanner) {
        this.scanner = scanner;
        this.userDAO = UserDAO.getInstance();
        this.inputReader = new InputReader(scanner);
        this.dietController = new DietController();
    }

    public void createUser() {
        try {
            System.out.println("=== Novo usuário ===");
            User user = builderUser();
            userDAO.create(user);
            System.out.println("Usuário criado com sucesso!");
            dietController.createDiet(user);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao criar o usuário. Por favor, tente novamente.");
        }
    }

    public void readUser() {
        try {
            String cpf = inputReader.readCpf();
            User user = userDAO.read(cpf);

            if (user == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }
            System.out.println("=== Dados do usuário ===");
            System.out.println("Nome: " + user.getName());
            System.out.println("Idade: " + user.getAge());
            System.out.println("Peso: " + user.getWeight() + " kg");
            System.out.println("Altura: " + user.getHeight() + " cm");
            System.out.println("Gênero: " + user.getGender());
            System.out.println("Objetivo: " + user.getObjective());
            System.out.println("Nível de Atividade: " + user.getActivityLevel());
            System.out.println("------------------------------");
            dietController.readDiet(cpf);
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao ler o usuário. Por favor, tente novamente.");
        }
    }

    public void updateUser() {
        try {
            System.out.println("=== Atualização usuário ===");
            String cpf = inputReader.readCpf();

            User existingUser = userDAO.read(cpf);
            if (existingUser == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            User user = builderUser();
            userDAO.update(cpf, user);
            System.out.println("Usuário atualizado com sucesso!");
            dietController.updateDiet(user);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao atualizar o usuário. Por favor, tente novamente.");
        }
    }

    public void deleteUser() {
        try {
            System.out.println("=== Deletando usuário ===");
            String cpf = inputReader.readCpf();

            User user = userDAO.read(cpf);
            if (user == null) {
                System.out.println("Usuário não encontrado.");
                return;
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

    private User builderUser() {
        String name = inputReader.readName();
        String cpf = inputReader.readCpf();
        int age = inputReader.readAge();
        double weight = inputReader.readWeight();
        double height = inputReader.readHeight();
        Gender gender = inputReader.readGender();
        Objective objective = inputReader.readObjective();
        ActivityLevel activityLevel = inputReader.readActivityLevel();

        return User.builder()
                .cpf(cpf)
                .name(name)
                .age(age)
                .weight(weight)
                .height(height)
                .gender(gender)
                .objective(objective)
                .activityLevel(activityLevel)
                .build();
    }
}

