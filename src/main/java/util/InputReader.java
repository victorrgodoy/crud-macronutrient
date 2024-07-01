package util;

import model.ActivityLevel;
import model.Gender;
import model.Objective;

import java.util.Scanner;

public class InputReader {
    private final Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readName() {
        while (true) {
            System.out.print("Digite o nome: ");
            String name = scanner.nextLine().trim();
            if (!name.matches("[\\p{L} ]+")) {
                System.out.println("Nome inválido. Use apenas letras e espaços.");
            } else {
                return name;
            }
        }
    }


    public String readCpf() {
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

    public int readAge() {
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

    public double readWeight() {
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

    public double readHeight() {
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

    public Gender readGender() {
        while (true) {
            System.out.print("Digite o gênero (MALE ou FEMALE): ");
            try {
                return Gender.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: Gênero inválido. Tente novamente.");
            }
        }
    }

    public Objective readObjective() {
        while (true) {
            System.out.print("Digite o objetivo (BULKING ou CUTTING): ");
            try {
                return Objective.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: Objetivo inválido. Tente novamente.");
            }
        }
    }

    public ActivityLevel readActivityLevel() {
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
