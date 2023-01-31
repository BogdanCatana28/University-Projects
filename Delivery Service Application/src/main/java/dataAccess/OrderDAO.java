package dataAccess;

import model.BaseProduct;
import model.Order;

import java.io.*;
import java.util.List;
import java.util.Map;

public class OrderDAO {
    public Map<Order, List<BaseProduct>> listOrders(){
        try
        {
            FileInputStream file = new FileInputStream("orders.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            Map<Order, List<BaseProduct>> orderMap = (Map<Order, List<BaseProduct>>) in.readObject();

            in.close();
            file.close();

            return orderMap;
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

        return null;
    }

    public void addOrder(Map<Order, List<BaseProduct>> orderMap){
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("orders.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(orderMap);

            out.close();
            file.close();
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }
}
