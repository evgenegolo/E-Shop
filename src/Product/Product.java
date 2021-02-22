package Product;

import java.sql.SQLException;

public interface Product {
    int productId = -1;
    String productName = "";
    float price = -1;
    int sellerId = -1;
    int discountPercent = -1;
    int amount = -1;
    String description = "";



    public int getProductId();

    public void setProductId(int productId);

    String getProductName();

    public void setProductName(String productName);

    public Float getPrice();

    public void setPrice(Float price);

    public int getSellerId();

    public void setSellerId(int sellerId);

    public int getDiscountPercent();

    public void setDiscountPercent(int discountPercent);

    public String getDescription();

    public void setDescription(String description);


    void create_product(int sellerId);



    Product get_product_by_product(String productType, int productId)throws SQLException;
    void delete_product();

    void update_product();



}
