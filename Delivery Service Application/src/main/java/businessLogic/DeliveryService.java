package businessLogic;

import dataAccess.OrderDAO;
import dataAccess.ProductDAO;
import dataAccess.UserDAO;
import model.BaseProduct;
import model.CompositeProduct;
import model.Order;
import model.User;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DeliveryService implements Serializable, IDeliveryServiceProcessing {

    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    private Map<Order, List<BaseProduct>> orderListMap = new HashMap<>();

    public void TableP() throws FileNotFoundException {

        ProductDAO productDAO = new ProductDAO();
        List<BaseProduct> products = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("products.csv"));
        String row;
        try {
            bufferedReader.readLine();
            row = bufferedReader.readLine();
            while (row != null) {
                String[] data = row.split(",");
                BaseProduct product = new BaseProduct(data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]), Double.parseDouble(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6]));

                for (BaseProduct baseProduct : products) {
                    if (baseProduct.getTitle().equals(product.getTitle())) {
                        break; // exista deja un produs cu numele asta
                    }
                }
                products.add(product);
                row = bufferedReader.readLine();
            }
            productDAO.addProduct(products);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void EditTheProduct(String title, BaseProduct product) {
        productDAO = new ProductDAO();
        List<BaseProduct> products = productDAO.listProducts();
        for (BaseProduct prod : products) {
            if (prod.getTitle().equals(title)) {
                prod.setTitle(product.getTitle());
                if (product.getRating() != null) {
                    prod.setRating(product.getRating());
                }
                if (product.getCalories() != null) {
                    prod.setCalories(product.getCalories());
                }
                if (product.getProteins() != null) {
                    prod.setProteins(product.getProteins());
                }
                if (product.getFats() != null) {
                    prod.setFats(product.getFats());
                }
                if (product.getSodium() != null) {
                    prod.setSodium(product.getSodium());
                }
                if (product.getPrice() != null) {
                    prod.setPrice(product.getPrice());
                }
            }
        }
        productDAO.addProduct(products);
    }

    public void DeleteTheProduct(String title) {
        productDAO = new ProductDAO();
        List<BaseProduct> products = productDAO.listProducts();
        for (BaseProduct prod : products) {
            if (prod.getTitle().equals(title)) {
                products.remove(prod);
                break;
            }
        }
        productDAO.addProduct(products);
    }

    public List<BaseProduct> SearchAllProducts(BaseProduct baseProduct) {
        productDAO = new ProductDAO();
        List<BaseProduct> filteredProducts = productDAO.listProducts();
        if (baseProduct.getTitle() != null) {
            filteredProducts = filteredProducts.stream()
                    .filter(baseProduct1 -> baseProduct1.getTitle().startsWith(baseProduct.getTitle())
                    ).collect(Collectors.toList());
        }
        if (baseProduct.getRating() != null) {
            filteredProducts = filteredProducts.stream()
                    .filter(baseProduct1 -> baseProduct1.getRating().equals(baseProduct.getRating())
                    ).collect(Collectors.toList());
        }
        if (baseProduct.getCalories() != null) {
            filteredProducts = filteredProducts.stream()
                    .filter(baseProduct1 -> baseProduct1.getCalories().equals(baseProduct.getCalories())
                    ).collect(Collectors.toList());
        }
        if (baseProduct.getProteins() != null) {
            filteredProducts = filteredProducts.stream()
                    .filter(baseProduct1 -> baseProduct1.getProteins().equals(baseProduct.getProteins())
                    ).collect(Collectors.toList());
        }
        if (baseProduct.getFats() != null) {
            filteredProducts = filteredProducts.stream()
                    .filter(baseProduct1 -> baseProduct1.getFats().equals(baseProduct.getFats())
                    ).collect(Collectors.toList());
        }
        if (baseProduct.getSodium() != null) {
            filteredProducts = filteredProducts.stream()
                    .filter(baseProduct1 -> baseProduct1.getSodium().equals(baseProduct.getSodium())
                    ).collect(Collectors.toList());
        }
        if (baseProduct.getPrice() != null) {
            filteredProducts = filteredProducts.stream()
                    .filter(baseProduct1 -> baseProduct1.getPrice().equals(baseProduct.getPrice())
                    ).collect(Collectors.toList());
        }
        return filteredProducts;
    }

    public void CreateCompositeItem(String title, int[] productsEl) {
        productDAO = new ProductDAO();
        List<BaseProduct> products = productDAO.listProducts();
        CompositeProduct compositeProduct = new CompositeProduct(title, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        for (Integer prod : productsEl) {
            compositeProduct.setRating((compositeProduct.getRating() + products.get(prod).getRating()));
            compositeProduct.setCalories(compositeProduct.getCalories() + products.get(prod).getCalories());
            compositeProduct.setProteins(compositeProduct.getProteins() + products.get(prod).getProteins());
            compositeProduct.setFats(compositeProduct.getFats() + products.get(prod).getFats());
            compositeProduct.setSodium(compositeProduct.getSodium() + products.get(prod).getSodium());
            compositeProduct.setPrice(compositeProduct.getPrice() + products.get(prod).getPrice());
        }
        BaseProduct baseProduct = new BaseProduct(compositeProduct.getTitle(), compositeProduct.getRating() / 2, compositeProduct.getCalories(), compositeProduct.getProteins(), compositeProduct.getFats(), compositeProduct.getSodium(), compositeProduct.getPrice() - 0.1 * compositeProduct.getPrice());

        products.add(baseProduct);
        productDAO.addProduct(products);
    }

    public void PlaceOrder(int idUser, int[] elements) {
        orderDAO = new OrderDAO();
        productDAO = new ProductDAO();
        Double price = 0.0;
        List<BaseProduct> products = productDAO.listProducts();
        List<BaseProduct> orderProducts = new ArrayList<>();

        for (Integer prod : elements) {
            BaseProduct baseProduct = new BaseProduct(products.get(prod).getTitle(), products.get(prod).getRating(), products.get(prod).getCalories(), products.get(prod).getProteins(), products.get(prod).getFats(), products.get(prod).getCalories(), products.get(prod).getPrice());
            price = price + products.get(prod).getPrice();
            orderProducts.add(baseProduct);
        }

        //Order order = new Order(0, idUser, price);
        orderListMap = orderDAO.listOrders();
        Order order = new Order(orderListMap.size(), idUser, price);

        BufferedWriter buffer = null;
        FileWriter file = null;
        try {
            file = new FileWriter("order.txt");
            buffer = new BufferedWriter(file);
        } catch (IOException er) {
            er.printStackTrace();
        }

        try {
            buffer.write("Order number: ");
            buffer.write(String.valueOf(order.getIdOrder()));
            buffer.newLine();
            buffer.write("Client's id: ");
            buffer.write(String.valueOf(order.getIdClient()));
            buffer.newLine();
            buffer.write("Time: ");
            buffer.write(String.valueOf(order.getDateOrder()));
            buffer.newLine();
            buffer.write("----------------");
            buffer.newLine();
            buffer.write("The containing of the order: ");
            buffer.newLine();
            for (BaseProduct product : orderProducts) {
                buffer.write(String.valueOf(product.getTitle()));
                buffer.newLine();
            }
            buffer.write("----------------");
            buffer.newLine();
            buffer.write("TOTAL is ");
            buffer.write(String.valueOf(order.getPriceOrder()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            buffer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        orderListMap.put(order, orderProducts);
        orderDAO.addOrder(orderListMap);
    }

    public List<Order> GenerateReport1(Date startHour, Date endHour) {
        OrderDAO orderDAO = new OrderDAO();
        orderListMap = orderDAO.listOrders();
        List<Order> filteredOrders = orderListMap.keySet().stream()
                .filter(order -> order.getDateOrder().getHour() > startHour.getHours() || order.getDateOrder().getHour() == startHour.getHours() && order.getDateOrder().getHour() < endHour.getHours() || order.getDateOrder().getHour() == endHour.getHours()
                ).collect(Collectors.toList());

        return filteredOrders;
    }

    public Map<String, Integer> GenerateReport2(int amount) {
        OrderDAO orderDAO = new OrderDAO();
        orderListMap = orderDAO.listOrders();
        Map<String, Integer> numberOfTimes = new HashMap<>();
        for(Order o : orderListMap.keySet()){
            List<BaseProduct> products = orderListMap.get(o);
            for(BaseProduct prod : products){
                if(numberOfTimes.containsKey(prod.getTitle())){
                    numberOfTimes.replace(prod.getTitle(),numberOfTimes.get(prod.getTitle()) + 1);
                }
                else numberOfTimes.put(prod.getTitle(), 1);
            }
        }

        numberOfTimes = numberOfTimes.entrySet().stream()
                .filter(map -> map.getValue() > amount || map.getValue() == amount
                ).collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

        return numberOfTimes;
    }

    public  Map<String, Integer> GenerateReport3(int noOfTimes, Double total) {
        OrderDAO orderDAO = new OrderDAO();
        UserDAO userDAO = new UserDAO();
        orderListMap = orderDAO.listOrders();
        Map<String, Integer> numberOfTimes = new HashMap<>();
        List<User> users = userDAO.listUsers();

        orderListMap = orderListMap.entrySet().stream()
                .filter(map1 -> map1.getKey().getPriceOrder() > total || map1.getKey().getPriceOrder() == total
                ).collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

        System.out.println(orderListMap);
        for(Order o : orderListMap.keySet()){
            int idClient = 0;
            for(User user : users){
                if(idClient == o.getIdClient()){
                    if(numberOfTimes.containsKey(user.getName())){
                        numberOfTimes.replace(user.getName(),numberOfTimes.get(user.getName()) + 1);
                    }
                    else numberOfTimes.put(user.getName(), 1);
                }
                idClient++;
            }
        }

        numberOfTimes = numberOfTimes.entrySet().stream()
                .filter(map -> map.getValue() > noOfTimes || map.getValue() == noOfTimes
                ).collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

        return numberOfTimes;
    }

   public Map<String, Integer> GenerateReport4(int date) {
       OrderDAO orderDAO = new OrderDAO();
       orderListMap = orderDAO.listOrders();
       Map<String, Integer> numberOfTimes = new HashMap<>();

       orderListMap = orderListMap.entrySet().stream()
               .filter(map1 -> map1.getKey().getDateOrder().getDayOfMonth() == date
               ).collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

       for(Order o : orderListMap.keySet()){
           List<BaseProduct> products = orderListMap.get(o);
           for(BaseProduct prod : products){
               if(numberOfTimes.containsKey(prod.getTitle())){
                   numberOfTimes.replace(prod.getTitle(),numberOfTimes.get(prod.getTitle()) + 1);
               }
               else numberOfTimes.put(prod.getTitle(), 1);
           }
       }

       return numberOfTimes;
   }
}
