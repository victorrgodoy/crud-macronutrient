package controller;

import dao.HistoricDAO;
import model.*;
import service.NutriServiceImp;

import java.time.LocalDate;

public class HistoricController {

    public HistoricController(){}

    public void createHistoric(User user) {
        NutriServiceImp nutriServiceImp = NutriServiceImp.getInstance();
        HistoricDAO historicDAO = HistoricDAO.getInstance();

        double weight = user.getWeight();
        double height = user.getHeight();
        String cpf = user.getCpf();
        double imc = nutriServiceImp.calculateIMC(weight, height);
        String levelIMC = nutriServiceImp.typeIMC(imc);

        Historic historic = Historic.builder()
                .imc(imc)
                .levelIMC(levelIMC)
                .weight(weight)
                .date(LocalDate.now())
                .build();

        historicDAO.create(historic, cpf);
        System.out.println("Histórico criado com sucesso!");
    }
}
