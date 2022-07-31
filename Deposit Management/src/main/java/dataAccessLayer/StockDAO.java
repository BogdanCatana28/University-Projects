package dataAccessLayer;

import model.Client;
import model.Stock;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockDAO {
    protected static final Logger LOGGER = Logger.getLogger(StockDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO stock (idProductS,quantity)"
            + " VALUES (?,?)";
    private static final String editStatementString = "UPDATE stock SET quantity=? WHERE idProductS=?";
    private static final String removeStatementString = "DELETE FROM stock WHERE idProductS=?";
    private static final String verifStatementString = "SELECT quantity FROM stock WHERE idProductS=?";

    public static int insert(Stock stock) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, stock.getIdProd());
            insertStatement.setInt(2, stock.getQuantity());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StockDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    public static int edit(Stock stock) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(editStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, stock.getQuantity());
            insertStatement.setInt(2, stock.getIdProd());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StockDAO:edit " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    public static int remove(int id) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(removeStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, id);
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StockDAO:remove " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static int verificare(int id) {
        Connection dbConnection = ConnectionFactory.getConnection();
        int stock = 0;
        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(verifStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, id);
            //insertStatement.executeQuery();

            ResultSet rs = insertStatement.executeQuery();
            if (rs.next()) {
                stock = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StockDAO:verificare " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return stock;
    }

}
