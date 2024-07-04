package controller;

import dao.UserDAO;
import model.*;
import util.FileGenerator;
import util.InputReader;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserController {
    private final Scanner scanner;
    private final UserDAO userDAO;
    private final InputReader inputReader;
    private final DietController dietController;
    private final HistoricController historicController;
    private final Logger logger;

    public UserController(Scanner scanner) {
        this.scanner = scanner;
        this.userDAO = UserDAO.getInstance();
        this.inputReader = new InputReader(scanner);
        this.dietController = new DietController();
        this.historicController = new HistoricController();
        this.logger = Logger.getLogger(UserController.class.getName());
    }

    public void createUser() {
        try {
            User user = buildUser();
            boolean isUserCreated = userDAO.create(user);
            if (isUserCreated) {
                dietController.createDiet(user);
                historicController.createHistoric(user);
                FileGenerator.writeUserToFile(user.getCpf());
                System.out.println("Usuário criado com sucesso!");
            } else {
                System.out.println("Erro: Não foi possível criar o usuário. CPF já cadastrado ou outro erro ocorreu.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao criar o usuário. Por favor, tente novamente.");
            logger.log(Level.SEVERE, "Erro ao criar usuário", e);
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
            displayUserData(user);
            dietController.readDiet(cpf);
            historicController.readHistoric(cpf);
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao ler o usuário. Por favor, tente novamente.");
            logger.log(Level.SEVERE, "Erro ao ler usuário", e);
        }
    }

    public void updateUser() {
        try {
            System.out.println("=== Atualização de usuário ===");
            String cpf = inputReader.readCpf();

            User existingUser = userDAO.read(cpf);
            if (existingUser == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            User updatedUser = buildUser();
            boolean isUserUpdated = userDAO.update(cpf, updatedUser);

            if (isUserUpdated) {
                dietController.updateDiet(updatedUser);
                FileGenerator.writeUserToFile(updatedUser.getCpf());
                System.out.println("Usuário atualizado com sucesso!");
            } else {
                System.out.println("Erro: Não foi possível atualizar o usuário.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao atualizar o usuário. Por favor, tente novamente.");
            logger.log(Level.SEVERE, "Erro ao atualizar usuário", e);
        }
    }

    public void deleteUser() {
        try {
            String cpf = inputReader.readCpf();

            User user = userDAO.read(cpf);
            if (user == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            if (confirmUserDeletion()) {
                boolean isFileDeleted = FileGenerator.deleteUserFile(cpf);
                boolean isUserDeleted = userDAO.delete(cpf);

                if (isUserDeleted && isFileDeleted) {
                    System.out.println("Usuário e arquivo deletados com sucesso!");
                } else {
                    System.out.println("Erro: Não foi possível deletar o usuário.");
                }
            } else {
                System.out.println("Operação de exclusão cancelada.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
            logger.log(Level.WARNING, "Erro ao deletar usuário", e);
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao deletar o usuário. Por favor, tente novamente.");
            logger.log(Level.SEVERE, "Erro ao deletar usuário", e);
        }
    }

    private User buildUser() {
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

    private void displayUserData(User user) {
        System.out.println("Nome: " + user.getName());
        System.out.println("Idade: " + user.getAge() + " anos");
        System.out.println("Peso: " + user.getWeight() + " kg");
        System.out.println("Altura: " + user.getHeight() + " m");
        System.out.println("Gênero: " + user.getGender());
        System.out.println("Objetivo: " + user.getObjective());
        System.out.println("Nível de Atividade: " + user.getActivityLevel());
        System.out.println("------------------------------");
    }

    private boolean confirmUserDeletion() {
        System.out.print("Você tem certeza que deseja deletar o usuário (sim / nao): ");
        String choice = scanner.next().trim().toUpperCase();
        return choice.equals("SIM");
    }
}

