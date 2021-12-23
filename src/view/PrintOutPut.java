package view;

import model.Cart;
import model.Product;

import java.util.List;

public class PrintOutPut {

    public static void printProductList(List<Product> productList){
        productList.stream().map(i -> i.getId()+") name:"+i.getName()+" price:"+i.getPrice()+" count:"+i.getCount() ).forEach(System.out::println);
    }

}
