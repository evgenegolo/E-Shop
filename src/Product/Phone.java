package Product;

import DB.ProductConnector;

import java.sql.SQLException;
import java.util.Scanner;

public class Phone implements Product{

    int productId;
    String productName ;
    Float price ;
    int sellerId ;
    int discountPercent ;
    String description ;


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Phone() {
    }


    @Override
    public void create_product(int sellerId) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter the product name");
        this.productName = s.nextLine();

        System.out.println("Enter the product price");
        this.price = s.nextFloat();

        this.sellerId = sellerId;

        System.out.println("Enter the product discount percent");
        this.discountPercent = s.nextInt();

        System.out.println("Enter the product description");
        this.description = s.nextLine();

    }

    public void create_product() {
    }


    //get the product by the product id
    @Override
    public Product get_product_by_product(String productType, int productId)throws SQLException{

        Product dbProduct = productFactory.getProduct(productType);

        dbProduct = ProductConnector.get_product_by_product(productType ,productId);

        dbProduct.setProductId(productId);

        return dbProduct;
    }



    @Override
    public void delete_product() {

    }

    @Override
    public void update_product() {

    }
}



