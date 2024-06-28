package service;

import model.ActivityLevel;
import model.Gender;

public class NutriServiceImp implements NutriService{
    private static final NutriServiceImp INSTANCE = new NutriServiceImp();

    private NutriServiceImp(){}

    public static NutriServiceImp getInstance(){
        return INSTANCE;
    }

    @Override
    public double calculateIMC(double weight, double height) {
        return NutriService.super.calculateIMC(weight, height);
    }

    @Override
    public String typeIMC(double imc) {
        return NutriService.super.typeIMC(imc);
    }

    @Override
    public double calculateBasalRate(Gender gender, double weight, double height, int age) {
        return NutriService.super.calculateBasalRate(gender, weight, height, age);
    }

    @Override
    public double calculateActivityFactor(Gender gender, ActivityLevel activityLevel) {
        return NutriService.super.calculateActivityFactor(gender, activityLevel);
    }

    @Override
    public double calculateBulking(double basalRate, double activityFactor) {
        return NutriService.super.calculateBulking(basalRate, activityFactor);
    }

    @Override
    public double calculateCutting(double basalRate, double activityFactor) {
        return NutriService.super.calculateCutting(basalRate, activityFactor);
    }

    @Override
    public double calculateProtein(double weight) {
        return NutriService.super.calculateProtein(weight);
    }

    @Override
    public double calculateFat(double weight) {
        return NutriService.super.calculateFat(weight);
    }

    @Override
    public double calculateCarb(double cals, double protein, double fat) {
        return NutriService.super.calculateCarb(cals, protein, fat);
    }
}
