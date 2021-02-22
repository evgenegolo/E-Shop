package Users;

import Product.Product;

public class Customer extends User{
    private String deliveryAddress;
    private String phoneNumber;
    private boolean status = true;
    private Product[] products;


    //getters setters unique for the customer class

    public Product[] getProducts() { return products; }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}