package controller;

import dao.DietDAO;
import dao.HistoricDAO;
import dao.UserDAO;
import model.*;
import service.NutriServiceImp;

import java.time.LocalDate;

public class HistoricController {
    private final HistoricDAO historicDAO;

    public HistoricController(){
        this.historicDAO = HistoricDAO.getInstance();
    }

    public void createHistoric(User user) {
        try {
            String cpf = user.getCpf();
            Historic historic = builderHistoric(user);
            historicDAO.create(historic, cpf);
            System.out.println("Histórico criado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao criar o histórico.");
        }
    }

    public void readHistoric(String cpf) {
        try {
            Historic historic = historicDAO.read(cpf);
            System.out.println("=== Histórico mais recente do usuário ===");
            System.out.println("Data: " + historic.getDate());
            System.out.println("Peso: " + historic.getWeight() + " kg");
            System.out.println("IMC: " + historic.getImc());
            System.out.println("Tipo IMC: " + historic.getType_imc());
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao ler o histórico do usuário. Por favor, tente novamente.");
        }
    }

    private Historic builderHistoric(User user) {
        NutriServiceImp nutriServiceImp = NutriServiceImp.getInstance();

        double weight = user.getWeight();
        double height = user.getHeight();

        double imc = nutriServiceImp.calculateIMC(weight,height);
        String type_imc = nutriServiceImp.typeIMC(imc);

        return Historic.builder()
                .weight(weight)
                .imc(imc)
                .type_imc(type_imc)
                .date(LocalDate.now())
                .build();
    }
}
