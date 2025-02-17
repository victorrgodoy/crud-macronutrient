package dao;

import factory.ConnectionFactory;
import model.ActivityLevel;
import model.Gender;
import model.Objective;
import model.User;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private final Connection connection;
    private static final UserDAO INSTANCE = new UserDAO();
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

    public UserDAO() {
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    public static UserDAO getInstance(){
        return INSTANCE;
    }

    public boolean create(User user) {
        String sql = "INSERT INTO user (cpf, name, age, weight, height, gender, objective, activity_level) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getCpf());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setDouble(4, user.getWeight());
            preparedStatement.setDouble(5, user.getHeight());
            preparedStatement.setString(6, String.valueOf(user.getGender()));
            preparedStatement.setString(7, String.valueOf(user.getObjective()));
            preparedStatement.setString(8, String.valueOf(user.getActivityLevel()));
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("CPF já cadastrado: " + user.getCpf());
            LOGGER.log(Level.WARNING, "CPF já cadastrado", e);
            return false;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating user", e);
            return false;
        }
    }


    public User read(String cpf) {
        String sql = "SELECT * FROM user WHERE cpf = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cpf);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String cpfResult = resultSet.getString("cpf");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    double weight = resultSet.getDouble("weight");
                    double height = resultSet.getDouble("height");
                    Gender gender = Gender.valueOf(resultSet.getString("gender"));
                    Objective objective = Objective.valueOf(resultSet.getString("objective"));
                    ActivityLevel activityLevel = ActivityLevel.valueOf(resultSet.getString("activity_level"));
                    return new User(cpfResult, name, age, weight, height, gender, objective, activityLevel);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading user", e);
        }
        return null;
    }

    public boolean update(String cpf, User user) {
        String sql = "UPDATE user SET age = ?, weight = ?, height = ?, objective = ?, activity_level = ? WHERE cpf = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getAge());
            preparedStatement.setDouble(2, user.getWeight());
            preparedStatement.setDouble(3, user.getHeight());
            preparedStatement.setString(4, String.valueOf(user.getObjective()));
            preparedStatement.setString(5, String.valueOf(user.getActivityLevel()));
            preparedStatement.setString(6, cpf);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating user", e);
            return false;
        }
    }

    public boolean delete(String cpf) {
        String sql = "DELETE FROM user WHERE cpf = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cpf);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0; // Retorna verdadeiro se pelo menos uma linha foi deletada
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting user", e);
            return false;
        }
    }


}


