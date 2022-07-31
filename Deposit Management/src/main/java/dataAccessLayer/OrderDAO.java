package dataAccessLayer;

import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO {
    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO `db-tp`.order VALUES (?,?,?,?,?)";
    private static final String showStatementString = "SELECT * FROM `db-tp`.order";

    public static int insert(Order order) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, order.getId());
            insertStatement.setInt(2, order.getIdClient());
            insertStatement.setInt(3, order.getIdProduct());
            insertStatement.setInt(4, order.getQuantity());
            insertStatement.setInt(5, order.getPrice());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static List<Order> showO() {
        Connection dbConnection = ConnectionFactory.getConnection();
        List<Order> orders = new ArrayList<>();
        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(showStatementString, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = insertStatement.executeQuery();
            while(rs.next()){
                Order order = new Order(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5));
                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StudentDAO:show " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return orders;
    }
}
