package dataAccessLayer;

import model.Product;
import model.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO {
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO product (idProduct,nameProduct,priceProduct)"
            + " VALUES (?,?,?)";
    private static final String editStatementString = "UPDATE product SET nameProduct=?,priceProduct=? WHERE idProduct=?";
    private static final String removeStatementString = "DELETE FROM product WHERE idProduct=?";
    private static final String showStatementString = "SELECT * FROM product";
    private static final String priceStatementString = "SELECT priceProduct FROM product WHERE idProduct=?";

    public static int insert(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, product.getId());
            insertStatement.setString(2, product.getName());
            insertStatement.setInt(3, product.getPrice());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static int edit(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(editStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, product.getName());
            insertStatement.setInt(2, product.getPrice());
            insertStatement.setInt(3, product.getId());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:edit " + e.getMessage());
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
            LOGGER.log(Level.WARNING, "ProductDAO:remove " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static List<Product> showP() {
        Connection dbConnection = ConnectionFactory.getConnection();
        List<Product> products = new ArrayList<>();
        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(showStatementString, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = insertStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3));
                products.add(product);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:show " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return products;
    }

    public static int price(int id) {
        Connection dbConnection = ConnectionFactory.getConnection();
        int pret = 0;
        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(priceStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, id);
            //insertStatement.executeQuery();

            ResultSet rs = insertStatement.executeQuery();
            if (rs.next()) {
                pret = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:price " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return pret;
    }
}
