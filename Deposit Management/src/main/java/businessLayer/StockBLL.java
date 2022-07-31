package businessLayer;

import dataAccessLayer.StockDAO;
import model.Stock;

public class StockBLL {
    public int insertStock(Stock stock){
        return StockDAO.insert(stock);
    }
    public int editStock(Stock stock){
        return StockDAO.edit(stock);
    }
    public int removeStock(int id){
        return StockDAO.remove(id);
    }
    public int verifStock(int id){return StockDAO.verificare(id);}
}
