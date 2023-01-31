package dataAccess;

import businessLogic.DeliveryService;


import java.io.*;


public class DeliveryServiceDAO {
    public DeliveryService listDelivery(){
        try
        {
            FileInputStream file = new FileInputStream("delivery.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            DeliveryService deliveryService = (DeliveryService) in.readObject();

            in.close();
            file.close();

            return deliveryService;
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
        return new DeliveryService();
    }

    public void addDelivery(DeliveryService deliveryService){
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("delivery.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(deliveryService);

            out.close();
            file.close();
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

    }
}
