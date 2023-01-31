package presentation;

import java.lang.reflect.Field;
import java.util.List;

public class ShowProducts<T> {
    private String[] column;
    private String[][] row;
    public ShowProducts(List<T> object) {
        int i = 0;
        int j = 0;
        int k = 0;
        column = new String[object.get(0).getClass().getSuperclass().getDeclaredFields().length];
        row = new String[object.size()][object.get(0).getClass().getSuperclass().getDeclaredFields().length];
        for(Field field : object.get(0).getClass().getSuperclass().getDeclaredFields()){
            column[i++] = field.getName();
        }
        for(T var : object){
            for(Field field : var.getClass().getSuperclass().getDeclaredFields()){
                field.setAccessible(true);
                try {
                    row[j][k++] = field.get(var).toString();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
            j++;
            k = 0;
        }
    }

    public String[] columns(){
        return column;
    }
    public String[][] rows(){
        return row;
    }
}
