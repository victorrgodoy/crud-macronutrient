package controller;

import dao.HistoricDAO;
import model.Historic;
import model.User;
import service.NutriService;
import service.NutriServiceImp;

import java.time.LocalDate;

public class HistoricController {
    private final HistoricDAO historicDAO;
    private final NutriService nutriService;

    public HistoricController() {
        this.historicDAO = HistoricDAO.getInstance();
        this.nutriService = NutriServiceImp.getInstance();
    }

    public void createHistoric(User user) {
        try {
            Historic historic = buildHistoric(user);
            historicDAO.create(historic, user.getCpf());
            System.out.println("Histórico criado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao criar o histórico para o usuário.");
        }
    }

    public void readHistoric(String cpf) {
        try {
            Historic historic = historicDAO.read(cpf);
            if (historic == null) {
                System.out.println("Nenhum histórico encontrado para o usuário.");
                return;
            }
            displayHistoricData(historic);
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um problema ao ler o histórico do usuário. Por favor, tente novamente.");
        }
    }

    private Historic buildHistoric(User user) {
        double weight = user.getWeight();
        double height = user.getHeight();

        double imc = nutriService.calculateIMC(weight, height);
        String type_imc = nutriService.typeIMC(imc);

        return Historic.builder()
                .weight(weight)
                .imc(imc)
                .type_imc(type_imc)
                .date(LocalDate.now())
                .build();
    }

    private void displayHistoricData(Historic historic) {
        System.out.println("=== Histórico mais recente do usuário ===");
        System.out.println("Data: " + historic.getDate());
        System.out.println("Peso: " + historic.getWeight() + " kg");
        System.out.println("IMC: " + historic.getImc());
        System.out.println("Tipo IMC: " + historic.getType_imc());
    }
}

