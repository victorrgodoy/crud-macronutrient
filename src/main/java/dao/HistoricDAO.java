package dao;

import factory.ConnectionFactory;
import model.Historic;
import model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoricDAO {
    private final Connection connection;
    private static final HistoricDAO INSTANCE = new HistoricDAO();
    private static final Logger LOGGER = Logger.getLogger(HistoricDAO.class.getName());

    public HistoricDAO(){
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    public static HistoricDAO getInstance(){
        return INSTANCE;
    }

    public int getIdUser(String cpf) {
        String sql = "SELECT user_id FROM user WHERE cpf = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cpf);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("user_id");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting user ID", e);
        }
        return -1;
    }

    public void create(Historic historic, String cpf) {
        String sql = "INSERT INTO weight_history (user_id, weight, imc, level_imc, date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int userId = getIdUser(cpf);
            if (userId != -1) {
                preparedStatement.setInt(1, userId);
                preparedStatement.setDouble(2, historic.getWeight());
                preparedStatement.setDouble(3, historic.getImc());
                preparedStatement.setString(4, historic.getLevelIMC());
                preparedStatement.setDate(5, Date.valueOf(historic.getDate()));
                preparedStatement.executeUpdate();
            } else {
                LOGGER.log(Level.WARNING, "User ID not found for CPF: {0}", cpf);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating Historic Weight", e);
        }
    }

}


