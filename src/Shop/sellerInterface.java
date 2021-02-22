package Shop;

import DB.CustomerConnector;
import DB.ProductConnector;
import Product.Product;
import Users.Customer;
import Users.Seller;

import java.sql.SQLException;
import java.util.Scanner;

public class sellerInterface {


    public static Seller log_in() throws SQLException, InterruptedException {
        boolean flag  = true;
        Seller seller = new Seller();
        String userName , password;
        Scanner s = new Scanner(System.in);


            System.out.println("Enter Your User Name:");
            userName = s.nextLine();
            System.out.println("Enter Your Password:");
            password = s.nextLine();
            seller.setUserId(CustomerConnector.loginUser(userName, password));
            if (seller.getSellerId() > 0) {
                seller = CustomerConnector.get_seller_data(seller.getUserId());
                seller.setStatus(true);
                flag = false;
            } else {
                System.out.println("You entered a wrong user name or password. \n" +
                        "for registration press 1 \n" +
                        "if your a customer press 2 \n" +
                        "to re-enter press any other button");
                int choice = s.nextInt();
                if(choice == 1) {
                    CustomerInterface.createUser();
                    return null;
                }
                else if(choice ==2)
                {
                    ShopMain.interface_redirect(2);
                }
                else
                    seller = log_in();
            }

        //if(seller.setSellerId() == null)

        return seller;
    }

    public static void show_product(Seller seller) throws SQLException {

        if (seller.getStatus())
        {
            seller.setProducts(CustomerConnector.get_all_seller_items(seller.getSellerId()));
            Product [] sellerProducts = seller.getProducts();
            for (int i =0;i < sellerProducts.length;i++){
                System.out.println("product name: "+sellerProducts[i].getProductName() +"\n"+
                        "Product ID :"+sellerProducts[i].getProductId()+"\n"+
                        "Product price: "+sellerProducts[i].getPrice()+"\n"+
                        "Product discount percent"+sellerProducts[i].getDiscountPercent()+"\n"+
                        "----------------------------------------------------------------------"+
                        "----------------------------------------------------------------------"
                        );
            }
        }
        else {
            System.out.println("bla bla");
        }
    }

    public static void add_product(Seller seller)throws SQLException{
        String type = "";
        Scanner s = new Scanner(System.in);
        int option = 0;

        do{
            System.out.println("chose the type of your product :\n" +
                    "1. Phone \n" +
                    "2. Tablet \n" +
                    "3. PC");
            option = s.nextInt();
        } while (option > 3 || option <= 0);
        switch (option){
            case 1:
                type = "Phone";
                break;
            case 2:
                type = "Tablet";
                break;
            case 3:
                type = "Computer";
                break;
            default:
                break;
        }
        ProductConnector.new_product(type , seller.getSellerId());
    }

    public static void delete_product(Seller seller)throws SQLException{
        Scanner s  =  new Scanner(System.in);
        seller.setProducts(CustomerConnector.get_all_seller_items(seller.getSellerId()));
        Product [] sellerProducts = seller.getProducts();
        int id = 0;
        for (int i =0;i < sellerProducts.length;i++){
            System.out.println("product name:"+sellerProducts[i].getProductName() +"\n"+
                    "Product ID :"+sellerProducts[i].getProductId());
        }

        System.out.println("Enter the id of the product you want to delete:");
        id = s.nextInt();
        ProductConnector.delete_product(id);
    }



}
