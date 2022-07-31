package businessLayer;

import dataAccessLayer.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductBLL {
    public int insertProduct(Product product){
        return ProductDAO.insert(product);
    }
    public int editProduct(Product product){
        return ProductDAO.edit(product);
    }
    public int removeProduct(int id){
        return ProductDAO.remove(id);
    }
    public int pretProduct(int id){return ProductDAO.price(id);}
    public List<Product> showProducts(){
        return ProductDAO.showP();
    }
    public List<String> comboBoxP(){
        List<Product> products = ProductDAO.showP();
        List<String> rezultat = new ArrayList<>();
        for(Product product : products){
            rezultat.add(product.getId() + " " + product.getName());
        }
        return rezultat;
    }
}
