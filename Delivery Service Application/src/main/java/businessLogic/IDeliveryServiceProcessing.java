package businessLogic;

import model.BaseProduct;
import model.Order;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IDeliveryServiceProcessing {

    public void TableP() throws FileNotFoundException;
    public void EditTheProduct(String title, BaseProduct product);
    public void DeleteTheProduct(String title);
    public List<BaseProduct> SearchAllProducts(BaseProduct baseProduct);
    public void CreateCompositeItem(String title, int[] productsEl);
    public void PlaceOrder(int idUser, int[] elements);
    public List<Order> GenerateReport1(Date startHour, Date endHour);
    public Map<String, Integer> GenerateReport2(int amount);
    public  Map<String, Integer> GenerateReport3(int noOfTimes, Double total);
    public Map<String, Integer> GenerateReport4(int date);
}
