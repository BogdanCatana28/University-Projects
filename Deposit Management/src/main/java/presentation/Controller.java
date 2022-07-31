package presentation;

import businessLayer.ClientBLL;
import businessLayer.OrderBLL;
import businessLayer.ProductBLL;
import businessLayer.StockBLL;
import model.Client;
import model.Order;
import model.Product;
import model.Stock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {
    private ViewP viewP;
    private ViewO viewO;
    private ViewC viewC;
    private MainView mainView;
    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private StockBLL stockBLL;
    private OrderBLL orderBLL;
    private int indexO = 1;

    public Controller(ViewP viewP, ViewC viewC, ViewO viewO, MainView mainView) {
        this.mainView = mainView;
        this.viewC = viewC;
        this.viewP = viewP;
        this.viewO = viewO;
        this.mainView.OpenClient(new openClient());
        this.mainView.OpenProduct(new openProduct());
        this.mainView.OpenOrder(new openOrder());
        this.viewC.GoBack(new goBack());
        this.viewP.GoBackP(new goBackP());
        this.viewO.GoBackO(new goBackO());
        this.viewC.ShowClients(new showClients());
        this.viewP.ShowProducts(new showProducts());
        this.viewO.ShowOrders(new showOrders());
        this.clientBLL = new ClientBLL();
        this.viewC.AddClient(new addClient());
        this.viewC.EditClient(new editClient());
        this.viewC.RemoveCLient(new removeClient());
        this.productBLL = new ProductBLL();
        this.viewP.AddProduct(new addProduct());
        this.viewP.EditProduct(new editProduct());
        this.viewP.RemoveProduct(new removeProduct());
        this.stockBLL = new StockBLL();
        this.orderBLL = new OrderBLL();
        this.viewO.AddOrder(new addOrder());
    }

    class openClient implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            mainView.setVisible(false);
            viewC.setVisible(true);
        }
    }
    class openProduct implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            mainView.setVisible(false);
            viewP.setVisible(true);
        }
    }
    class openOrder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            mainView.setVisible(false);
            viewO.setVisible(true);
            viewO.setProductCombo(productBLL.comboBoxP());
            viewO.setClientCombo(clientBLL.comboBoxC());
        }
    }
    class goBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            viewC.setVisible(false);
            mainView.setVisible(true);
        }
    }
    class goBackP implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            viewP.setVisible(false);
            mainView.setVisible(true);
        }
    }
    class goBackO implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            viewO.setVisible(false);
            mainView.setVisible(true);
        }
    }

    class showClients implements ActionListener {
        private JScrollPane scrollTable;
        private JTable tableClient;
        @Override
        public void actionPerformed(ActionEvent e){
            scrollTable = new JScrollPane();
            scrollTable.setBounds(454, 232, 438, 443);
            viewC.getContentPane().add(scrollTable);
            ShowObjects<Client> showC = new ShowObjects<>(clientBLL.showClients());
            String[] columnC = showC.columns();
            String[][] rowC = showC.rows();
            tableClient = new JTable(rowC, columnC);
            scrollTable.setViewportView(tableClient);
        }
    }
    class showProducts implements ActionListener {
        private JScrollPane scrollTableP;
        private JTable tableProduct;
        @Override
        public void actionPerformed(ActionEvent e){
            scrollTableP = new JScrollPane();
            scrollTableP.setBounds(454, 232, 438, 443);
            viewP.getContentPane().add(scrollTableP);
            ShowObjects<Product> showP = new ShowObjects<>(productBLL.showProducts());
            String[] columnP = showP.columns();
            String[][] rowP = showP.rows();
            tableProduct = new JTable(rowP,columnP);
            scrollTableP.setViewportView(tableProduct);
        }
    }
    class showOrders implements ActionListener {
        private JScrollPane scrollTableO;
        private JTable tableOrder;
        @Override
        public void actionPerformed(ActionEvent e){
            scrollTableO = new JScrollPane();
            scrollTableO.setBounds(454, 232, 438, 443);
            viewO.getContentPane().add(scrollTableO);
            ShowObjects<Order> showO = new ShowObjects<>(orderBLL.showOrders());
            String[] columnO = showO.columns();
            String[][] rowO = showO.rows();
            tableOrder = new JTable(rowO,columnO);
            scrollTableO.setViewportView(tableOrder);
        }
    }
    class addClient implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            Client client = new Client(viewC.idC(), viewC.nameC(), viewC.addressC());
            clientBLL.insertClient(client);
        }
    }
    class editClient implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            Client client = new Client(viewC.idC(), viewC.nameC(), viewC.addressC());
            clientBLL.editClient(client);
        }
    }
    class removeClient implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            int idRemove = viewC.idCR();
            clientBLL.removeClient(idRemove);
        }
    }
    class addProduct implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            Product product = new Product(viewP.idP(), viewP.nameP(), viewP.priceP());
            Stock stock = new Stock(viewP.idP(), viewP.stockP());
            productBLL.insertProduct(product);
            stockBLL.insertStock(stock);
        }
    }
    class editProduct implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            Product product = new Product(viewP.idP(), viewP.nameP(), viewP.priceP());
            Stock stock = new Stock(viewP.idP(), viewP.stockP());
            productBLL.editProduct(product);
            stockBLL.insertStock(stock);
        }
    }
    class removeProduct implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            int idRemove = viewP.idPR();
            productBLL.removeProduct(idRemove);
            stockBLL.removeStock((idRemove));
        }
    }

    class addOrder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            indexO = 0;
            String aux = viewO.getClientCombo();
            String auxP = viewO.getProductCombo();
            BufferedWriter buffer = null;
            FileWriter file = null;
            try {
                file = new FileWriter("order.txt");
                buffer = new BufferedWriter(file);
            } catch (IOException er) {
                er.printStackTrace();
            }
            int idP = 0;
            int j = 0;
            int idC = 0;
            int i = 0;
            while(true){
                if(aux.charAt(i) != ' '){
                    idC = idC*10 + Integer.parseInt(String.valueOf(aux.charAt(i)));
                    i++;
                }
                else break;
            }
            while(true){
                if(auxP.charAt(j) != ' '){
                    idP = idP*10 + Integer.parseInt(String.valueOf(auxP.charAt(j)));
                    j++;
                }
                else break;
            }
            int stock = stockBLL.verifStock(idP);
            int pret = productBLL.pretProduct(idP);
            if(viewO.quantity() <= stock){
                Order order = new Order(indexO, idC, idP, viewO.quantity(), viewO.quantity() * pret);
                orderBLL.insertOrder(order);
                try {
                    buffer.write("Order number ");
                    buffer.write(String.valueOf(order.getId()));
                    buffer.newLine();
                    buffer.write("The id of the Client is ");
                    buffer.write(String.valueOf(order.getIdClient()));
                    buffer.newLine();
                    buffer.write("----------------");
                    buffer.newLine();
                    buffer.write("The id of the Product is ");
                    buffer.write(String.valueOf(order.getIdProduct()));
                    buffer.newLine();
                    buffer.write("----------------");
                    buffer.newLine();
                    buffer.write(String.valueOf(order.getQuantity()));
                    buffer.write(" x ");
                    buffer.write(String.valueOf(pret));
                    buffer.newLine();
                    buffer.write("----------------");
                    buffer.newLine();
                    buffer.write("TOTAL is ");
                    buffer.write(String.valueOf(order.getPrice()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Stock stock1 = new Stock(idP, stock - viewO.quantity());
                stockBLL.editStock(stock1);
                try {
                    buffer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else viewO.showMessage("Not enough stock!");
        }
    }
}