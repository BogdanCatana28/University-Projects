package presentation;

import businessLogic.DeliveryService;
import businessLogic.UserBLL;
import dataAccess.DeliveryServiceDAO;
import dataAccess.ProductDAO;
import dataAccess.UserDAO;
import model.BaseProduct;
import model.Order;
import model.Type;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Controller {
    private MainView mainView;
    private AdminView adminView;
    private ClientView clientView;
    private ReportsView reportsView;
    private UserBLL userBLL;
    private ProductDAO productDAO;
    private UserDAO userDAO;
    private DeliveryServiceDAO deliveryServiceDAO = new DeliveryServiceDAO();
    private DeliveryService deliveryService;
    private JScrollPane scrollTable;
    private JTable tableProduct;
    private JScrollPane scrollPane;
    private JTable tableProductC;

    public Controller(MainView mainView, AdminView adminView, ClientView clientView, ReportsView reportsView) {
        this.mainView = mainView;
        this.adminView = adminView;
        this.clientView = clientView;
        this.reportsView = reportsView;
        this.userBLL = new UserBLL();
        this.deliveryService = deliveryServiceDAO.listDelivery();
        this.mainView.loginButton(new Login());
        this.mainView.registerButton(new Register());
        this.adminView.ShowProduct(new ShowAllProducts());
        this.productDAO = new ProductDAO();
        this.userDAO = new UserDAO();
        this.adminView.ImportProduct(new ImportProducts());
        this.adminView.OpenReports(new Reports());
        this.adminView.AddProduct(new AddProducts());
        this.adminView.EditProduct(new EditProducts());
        this.adminView.RemoveProduct(new DeleteProducts());
        this.adminView.LogOut(new Logout());
        this.clientView.SearchProduct(new FilterProduct());
        this.adminView.CreateComposite(new Composite());
        this.clientView.PlaceOrder(new CreateOrder());
        this.clientView.LogOutC(new LogoutC());
        this.reportsView.LogOutR(new LogoutR());
        this.reportsView.Report1(new GenerateR1());
        this.reportsView.Report2(new GenerateR2());
        this.reportsView.Report3(new GenerateR3());
        this.reportsView.Report4(new GenerateR4());
        this.adminView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                deliveryServiceDAO.addDelivery(deliveryService);
            }
        });
        this.clientView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                deliveryServiceDAO.addDelivery(deliveryService);
            }
        });
        this.reportsView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                deliveryServiceDAO.addDelivery(deliveryService);
            }
        });
    }

    class Logout implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            adminView.setVisible(false);
            mainView.setVisible(true);
        }
    }

    class LogoutC implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            clientView.setVisible(false);
            mainView.setVisible(true);
        }
    }

    class LogoutR implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            reportsView.setVisible(false);
            mainView.setVisible(true);
        }
    }

    class Reports implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            clientView.setVisible(false);
            reportsView.setVisible(true);
        }
    }

    class Login implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = mainView.username();
            String pass = mainView.password();

            try {
                if(userBLL.login(name, pass) == 1) {
                    adminView.setVisible(true);
                    mainView.setVisible(false);
                }
                if(userBLL.login(name, pass) == 2) {
                    clientView.setVisible(true);
                    mainView.setVisible(false);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class Register implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = mainView.username();
            String pass = mainView.password();
            int type = mainView.getComboType();

            try {
                if(type == 0){
                    userBLL.register(name, pass, Type.CLIENT);
                }
                else
                {
                    userBLL.register(name, pass, Type.EMPLOYEE);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            System.out.println("Contul a fost creat cu succes!");
        }
    }

    class ShowAllProducts implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            if(scrollTable != null){
                adminView.remove(scrollTable);
            }
            scrollTable = new JScrollPane();
            scrollTable.setBounds(312, 100, 511, 459);
            adminView.getContentPane().add(scrollTable);
            List<BaseProduct> products = productDAO.listProducts();
            ShowProducts<BaseProduct> showP = new ShowProducts<>(products);
            String[] columnP = showP.columns();
            String[][] rowP = showP.rows();
            tableProduct = new JTable(rowP,columnP);
            scrollTable.setViewportView(tableProduct);
        }
    }

    class ImportProducts implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                deliveryService.TableP();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

            System.out.println("Produsele au fost importate cu succes!");
        }
    }

    class AddProducts implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            BaseProduct baseProduct = new BaseProduct(adminView.title(), Double.parseDouble(adminView.rating()), Double.parseDouble(adminView.calories()), Double.parseDouble(adminView.proteins()), Double.parseDouble(adminView.fats()), Double.parseDouble(adminView.sodium()), Double.parseDouble(adminView.price()));
            List<BaseProduct> products = productDAO.listProducts();
            products.add(baseProduct);
            productDAO.addProduct(products);

            System.out.println("Produs adaugat cu succes!");
            adminView.getShowBtn().doClick();
        }
    }

    class EditProducts implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            BaseProduct baseProduct = new BaseProduct(
                    adminView.title(),
                    (!adminView.rating().equals("") ) ? Double.parseDouble(adminView.rating()) : null,
                    (!adminView.calories().equals("")) ? Double.parseDouble(adminView.calories()) : null,
                    (!adminView.proteins().equals("")) ? Double.parseDouble(adminView.proteins()) : null,
                    (!adminView.fats().equals("")) ? Double.parseDouble(adminView.fats()) : null,
                    (!adminView.sodium().equals("")) ? Double.parseDouble(adminView.sodium()) : null,
                    (!adminView.price().equals("")) ? Double.parseDouble(adminView.price()) : null);
            deliveryService.EditTheProduct(baseProduct.getTitle(), baseProduct);

            System.out.println("Produs editat cu succes!");
            adminView.getShowBtn().doClick();
        }
    }

    class DeleteProducts implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String title = adminView.title();
            deliveryService.DeleteTheProduct(title);

            System.out.println("Produs eliminat cu succes!");
            adminView.getShowBtn().doClick();
        }
    }

    class FilterProduct implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            BaseProduct baseProduct = new BaseProduct(
                    clientView.titleC(),
                    (!clientView.ratingC().equals("") ) ? Double.parseDouble(clientView.ratingC()) : null,
                    (!clientView.caloriesC().equals("")) ? Double.parseDouble(clientView.caloriesC()) : null,
                    (!clientView.proteinsC().equals("")) ? Double.parseDouble(clientView.proteinsC()) : null,
                    (!clientView.fatsC().equals("")) ? Double.parseDouble(clientView.fatsC()) : null,
                    (!clientView.sodiumC().equals("")) ? Double.parseDouble(clientView.sodiumC()) : null,
                    (!clientView.priceC().equals("")) ? Double.parseDouble(clientView.priceC()) : null);

            List<BaseProduct> produse = deliveryService.SearchAllProducts(baseProduct);

            if(scrollPane != null){
                clientView.remove(scrollPane);
            }
            scrollPane = new JScrollPane();
            scrollPane.setBounds(312, 100, 511, 459);
            clientView.getContentPane().add(scrollPane);
            ShowProducts<BaseProduct> showP = new ShowProducts<>(produse);
            String[] columnP = showP.columns();
            String[][] rowP = showP.rows();
            tableProductC = new JTable(rowP,columnP);
            scrollPane.setViewportView(tableProductC);

            System.out.println("Produsele au fost filtrate cu succes!");
        }
    }

    class Composite implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int[] elements = tableProduct.getSelectedRows();
            String titleComposite = adminView.title();

            deliveryService.CreateCompositeItem(titleComposite, elements);

            adminView.getShowBtn().doClick();

            System.out.println("Meniul a fost creat cu succes!");
        }
    }

    class CreateOrder implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            List<User> users = userDAO.listUsers();
            int idClient = 0;
            for(User user : users){
                if(user.getName().equals(mainView.username()) && user.getType() == Type.CLIENT){
                    break;
                }
                idClient++;
            }
            int[] elements = tableProductC.getSelectedRows();

            deliveryService.PlaceOrder(idClient, elements);

            System.out.println("Comanda a fost plasata cu succes!");
        }
    }

    class GenerateR1 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Date startHour = new Date();
            Date endHour = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            try {
                startHour = formatter.parse(reportsView.startHour());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            try {
                endHour = formatter.parse(reportsView.startHour());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

            List<Order> report1 = deliveryService.GenerateReport1(startHour, endHour);

            String adaugare = new String();
            for(Order order : report1){
                adaugare += order.toString();
                adaugare+= "\n";
            }
            reportsView.setReport1Field(adaugare);
            System.out.println(adaugare);
            System.out.println("Raportul 1 a fost generat cu succes!");
        }
    }

    class GenerateR2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int amount = Integer.parseInt(reportsView.amount());
            Map<String, Integer> report2 = deliveryService.GenerateReport2(amount);

            String adaugare = new String();
            for(String produs : report2.keySet()){
                adaugare += produs;
                adaugare+= "\n";
                System.out.println(produs);
            }

            reportsView.setReport1Field(adaugare);
            System.out.println("Raportul 2 a fost generat cu succes!");
        }
    }

    class GenerateR3 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int noOfTimes = Integer.parseInt(reportsView.times());
            Double orderTotal = Double.parseDouble(reportsView.total());

            Map<String, Integer> report3 = deliveryService.GenerateReport3(noOfTimes, orderTotal);

            String adaugare = new String();
            for(String user : report3.keySet()){
                adaugare += user;
                adaugare+= "\n";
                System.out.println(user);
            }

            reportsView.setReport1Field(adaugare);

            System.out.println("Raportul 3 a fost generat cu succes!");
        }
    }

    class GenerateR4 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            int date = Integer.parseInt(reportsView.day());

            Map<String, Integer> report4 = deliveryService.GenerateReport4(date);

            String adaugare = new String();
            for(String produs : report4.keySet()){
                adaugare += produs;
                adaugare += " " ;
                adaugare += report4.get(produs).toString();
                adaugare+= "\n";
                System.out.println(adaugare);
            }

            reportsView.setReport1Field(adaugare);
            System.out.println("Raportul 4 a fost generat cu succes!");
        }
    }
}