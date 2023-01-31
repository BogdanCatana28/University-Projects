package dataAccess;

import model.BaseProduct;

import java.io.*;
import java.util.List;

public class ProductDAO {
    public List<BaseProduct> listProducts(){
        try
        {
            FileInputStream file = new FileInputStream("products.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            List<BaseProduct> productList = (List<BaseProduct>) in.readObject();

            in.close();
            file.close();

            return productList;
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

    public void addProduct(List<BaseProduct> productList){
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("products.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(productList);

            out.close();
            file.close();
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }
}
