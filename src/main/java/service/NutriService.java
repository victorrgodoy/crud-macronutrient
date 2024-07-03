package service;
import model.ActivityLevel;
import model.Gender;

import java.text.DecimalFormat;

public interface NutriService {
    DecimalFormat df = new DecimalFormat("#.##");

    default double calculateIMC(double weight, double height) {
        double imc = weight / (height * height);
        return Double.parseDouble(df.format(imc));
    }

    default String typeIMC(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc >= 18.5 && imc < 24.9) {
            return "Peso normal";
        } else if (imc >= 24.9 && imc < 29.9) {
            return "Sobrepeso";
        } else if (imc >= 29.9 && imc < 34.9) {
            return "Obesidade grau I";
        } else if (imc >= 34.9 && imc < 39.9) {
            return "Obesidade grau II";
        } else {
            return "Obesidade grau III";
        }
    }

    default double calculateBasalRate(Gender gender, double weight, double height, int age) {
        double baseRate = (10 * weight) + (6.25 * (height * 100)) - (5 * age);
        if (gender == Gender.MALE) {
            baseRate += 5;
        }
        if (gender == Gender.FEMALE) {
            baseRate -= 161;
        }
        return baseRate;
    }

    default double calculateActivityFactor(Gender gender, ActivityLevel activityLevel) {
        return switch (activityLevel) {
            case LOW -> (gender == Gender.MALE) ? 1.55 : 1.56;
            case MEDIUM -> (gender == Gender.MALE) ? 1.78 : 1.64;
            case HIGH -> (gender == Gender.MALE) ? 2.10 : 1.82;
        };
    }

    default double calculateBulking(double basalRate, double activityFactor) {
        double dailyCaloric = basalRate * activityFactor;
        double bulking =  dailyCaloric * 1.15;
        return Double.parseDouble(df.format(bulking));
    }

    default double calculateCutting(double basalRate, double activityFactor) {
        double dailyCaloric = basalRate * activityFactor;
        double cutting =  dailyCaloric * 0.85;
        return Double.parseDouble(df.format(cutting));
    }

    default double calculateProtein(double weight) {
        double protein =  2.0 * weight;
        return Double.parseDouble(df.format(protein));
    }

    default double calculateFat(double weight) {
        double fat = 0.8 * weight;
        return Double.parseDouble(df.format(fat));
    }

    default double calculateCarb(double cals, double protein, double fat) {
        double proteinCalories = protein * 4;
        double fatCalories = fat * 9;
        double remainingCalories = cals - (proteinCalories + fatCalories);
        double carb = remainingCalories / 4;
        return Double.parseDouble(df.format(carb));
    }

}
