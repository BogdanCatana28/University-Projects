import dataAccess.UserDAO;
import model.Type;
import model.User;
import presentation.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MainView mainView = new MainView();
        AdminView adminView = new AdminView();
        ClientView clientView = new ClientView();
        ReportsView reportsView = new ReportsView();
        Controller controller = new Controller(mainView, adminView, clientView, reportsView);
        UserDAO userDAO = new UserDAO();
        System.out.println(userDAO.listUsers());
    }
}
