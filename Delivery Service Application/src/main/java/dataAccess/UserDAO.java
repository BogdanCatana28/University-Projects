package dataAccess;

import model.User;

import java.io.*;
import java.util.List;

public class UserDAO {
    public List<User> listUsers(){
        try
        {
            FileInputStream file = new FileInputStream("users.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            List<User> userList = (List<User>) in.readObject();

            in.close();
            file.close();

            return userList;
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

    public void addUser(List<User> userList){
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("users.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(userList);

            out.close();
            file.close();
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }
}
