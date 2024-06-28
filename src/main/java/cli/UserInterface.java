package cli;

import dao.UserDAO;
import model.Gender;
import model.Objective;
import model.User;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final UserDAO userDAO = UserDAO.getInstance();

    public UserInterface(Scanner scanner){
        this.scanner = scanner;
    }

    public static void displayMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Criar novo usuário");
        System.out.println("2. Lêr informações do usuário");
        System.out.println("3. Atualizar dados do usuário");
        System.out.println("4. Deleter usuário");
        System.out.println("0. Sair");
    }

    public void createUser() {
        try {
            System.out.println("=== Criando novo usuário ===");

            System.out.print("Digite o nome: ");
            String name = scanner.next().trim();

            if (!name.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Nome inválido. Use apenas letras.");
            }

            System.out.print("Digite o cpf: ");
            String cpf = scanner.next().trim();

            if (!cpf.matches("[0-9]+") || cpf.length() != 11) {
                throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos numéricos.");
            }

            System.out.print("Digite a idade: ");
            int age = Integer.parseInt(scanner.next().trim());

            if (age <= 0 || age >= 150) {
                throw new IllegalArgumentException("Idade inválida. Insira um valor maior que 0 e menor que 150.");
            }

            System.out.print("Digite o peso (kg): ");
            double weight = Double.parseDouble(scanner.next().trim());

            System.out.print("Digite a altura (cm): ");
            double height = Double.parseDouble(scanner.next().trim());

            System.out.print("Digite o gênero (MALE ou FEMALE): ");
            Gender gender = Gender.valueOf(scanner.next().trim().toUpperCase());

            System.out.print("Digite o objetivo (BULKING ou CUTTING): ");
            Objective objective = Objective.valueOf(scanner.next().trim().toUpperCase());

            User user = User.builder()
                    .cpf(cpf)
                    .name(name)
                    .age(age)
                    .weight(weight)
                    .height(height)
                    .gender(gender)
                    .objective(objective)
                    .build();

            userDAO.create(user);
            System.out.println("Usuário criado com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("Erro: Formato numérico inválido. Por favor, tente novamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());  // Exibe a mensagem de erro específica
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





}
