package util;

import dao.DietDAO;
import dao.HistoricDAO;
import dao.UserDAO;
import model.Diet;
import model.Historic;
import model.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileGenerator {

    public static void writeUserToFile(String cpf) {
        User user = UserDAO.getInstance().read(cpf);
        Diet diet = DietDAO.getInstance().read(cpf);
        Historic historic = HistoricDAO.getInstance().read(cpf);

        String directoryPath = "src/main/resources/historicUsers";
        String fileName = user.getName() + ".txt";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, fileName);

        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(user.toString());
            bufferedWriter.write(diet.toString());
            bufferedWriter.write(historic.toString());
            bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println("Arquivo criado com sucesso: " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar arquivo", e);
        }
    }

    public static boolean deleteUserFile(String cpf) {
        User user = UserDAO.getInstance().read(cpf);
        String directoryPath = "src/main/resources/historicUsers";
        String fileName = user.getName() + ".txt";
        File file = new File(directoryPath, fileName);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Arquivo deletado com sucesso: " + file.getAbsolutePath());
                return true;
            } else {
                System.out.println("Erro ao deletar o arquivo: " + file.getAbsolutePath());
                return false;
            }
        } else {
            System.out.println("Arquivo não encontrado: " + file.getAbsolutePath());
            return false;
        }
    }

}
