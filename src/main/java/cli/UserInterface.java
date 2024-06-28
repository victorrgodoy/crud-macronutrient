package cli;

import controller.UserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final UserController userController;

    public UserInterface(Scanner scanner){
        this.scanner = scanner;
        this.userController = new UserController(scanner);
    }

    public void start() {
        while (true) {
            displayMenu();
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 0:
                    scanner.close();
                    System.out.println("Encerrando o programa.");
                    return;
                case 1:
                    userController.createUser();
                    break;
                case 4:
                    userController.deleteUser();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }


    public static void displayMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Criar novo usuário");
        System.out.println("2. Lêr informações do usuário");
        System.out.println("3. Atualizar dados do usuário");
        System.out.println("4. Deleter usuário");
        System.out.println("0. Sair");
    }
}
