//package dao;
//
//import factory.ConnectionFactory;
//import model.Diet;
//
//import java.sql.*;
//import java.time.LocalDate;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class DietDAO {
//    private final Connection connection;
//    private static final DietDAO INSTANCE = new DietDAO();
//    private static final Logger LOGGER = Logger.getLogger(DietDAO.class.getName());
//
//    public DietDAO() {
//        this.connection = ConnectionFactory.getInstance().getConnection();
//    }
//
//    public static DietDAO getInstance(){
//        return INSTANCE;
//    }
//
//    public int getIdUser(String cpf) {
//        String sql = "SELECT user_id FROM user WHERE cpf = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setString(1, cpf);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    return resultSet.getInt("user_id");
//                }
//            }
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Error getting user ID", e);
//        }
//        return -1;
//    }
//
//    public void create(Diet diet, String cpf) {
//        String sql = "INSERT INTO diet (user_id, cals, proteins, carbs, fats, date) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?)";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            int userId = getIdUser(cpf);
//            if (userId != -1) {
//                preparedStatement.setInt(1, userId);
//                preparedStatement.setDouble(2, diet.getCals());
//                preparedStatement.setDouble(3, diet.getProteins());
//                preparedStatement.setDouble(4, diet.getCarbs());
//                preparedStatement.setDouble(5, diet.getFats());
//                preparedStatement.setDate(6, Date.valueOf(LocalDate.now()));
//                preparedStatement.executeUpdate();
//            } else {
//                LOGGER.log(Level.WARNING, "User ID not found for CPF: {0}", cpf);
//            }
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Error creating diet", e);
//        }
//    }
//
//    public Diet read(String cpf) {
//        String sql = "SELECT * FROM diet WHERE user_id = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            int userId = getIdUser(cpf);
//            preparedStatement.setInt(1, userId);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    double imc = resultSet.getDouble("imc");
//                    double cals = resultSet.getDouble("cals");
//                    double proteins = resultSet.getDouble("proteins");
//                    double carbs = resultSet.getDouble("carbs");
//                    double fats = resultSet.getDouble("fats");
//                    return new Diet(imc, cals, proteins, carbs, fats);
//                }
//            }
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Error reading user", e);
//        }
//        return null;
//    }
//
//    public void update(String cpf, Diet diet) {
//        String sql = "UPDATE diet SET imc = ?, kcals = ?, proteins = ?, carbs = ?, fats = ? WHERE user_id = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setDouble(1, diet.getImc());
//            preparedStatement.setDouble(2, diet.getKcals());
//            preparedStatement.setDouble(3, diet.getProteins());
//            preparedStatement.setDouble(4, diet.getCarbs());
//            preparedStatement.setDouble(5, diet.getFats());
//            preparedStatement.setInt(6, getIdUser(cpf));
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Error updating diet", e);
//        }
//    }
//}
