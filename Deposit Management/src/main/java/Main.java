import dataAccessLayer.ConnectionFactory;
import presentation.*;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        MainView mainView = new MainView();
        ViewC viewC = new ViewC();
        ViewO viewO = new ViewO();
        ViewP viewP = new ViewP();
        Controller controller = new Controller(viewP, viewC, viewO, mainView);
        Connection factory = ConnectionFactory.getConnection();
    }
}
