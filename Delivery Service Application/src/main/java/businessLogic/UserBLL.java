package businessLogic;

import dataAccess.UserDAO;
import model.Type;
import model.User;

import java.util.List;

public class UserBLL {
    public int login(String userName, String userPass){
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.listUsers();

        for(User user : users){
            if(user.getName().equals(userName) && user.getPassword().equals(userPass)){
                if(user.getType() == Type.ADMIN){
                    return 1;
                }
                if(user.getType() == Type.CLIENT){
                    return 2;
                }
                if(user.getType() == Type.EMPLOYEE){
                    return 3;
                }
            }
        }
        return 0;
    }

    public int register(String userName, String userPass, Type userType){
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.listUsers();

        for(User user : users){
            if(user.getName().equals(userName)){
                return 1; // exista deja un cont
            }
        }
        User user = new User(userName, userPass, userType);
        users.add(user);
        userDAO.addUser(users);

        return 0;
    }
}
