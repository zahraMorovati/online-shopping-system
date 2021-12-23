package service;

import dao.DaoProduct;
import model.Product;
import model.enumation.ProductGroup;
import view.GetValidData;

import java.util.List;

public class ProductService {
    DaoProduct daoProduct = new DaoProduct();

    public Product getProductById(int id){
        Product product = daoProduct.findById(id);
        if(product==null)
            throw new RuntimeException("invalid product id!");
        else return product;
    }

    public List<Product> getListProducts(){
        List<Product> products = daoProduct.findAllProducts();
        if(products.isEmpty())
            throw new RuntimeException("there is no product!");
        else return products;
    }

    public Product getProduct(){

         String name= GetValidData.getValidName("name product: ");
         ProductGroup productGroup=GetValidData.getValidProductGroup();
         ProductGroup.ProductType productType=GetValidData.getValidProductType(productGroup);
         double price=GetValidData.getValidDouble("price: ");
         int count=GetValidData.getValidInt("count: ");

        return setProduct(name, productGroup, productType, price, count);
    }

    private Product setProduct(String name, ProductGroup productGroup, ProductGroup.ProductType productType, double price, int count) {
        return Product.ProductBuilder.aProduct()
                .setName(name).setProductGroup(productGroup).setProductType(productType)
                .setPrice(price).setCount(count).build();
    }

    public void save(Product product){
        daoProduct.save(product);
        System.out.println("product saved!");
    }
}
