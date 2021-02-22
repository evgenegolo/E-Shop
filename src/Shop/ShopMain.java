package Shop;

import DB.ProductConnector;
import Product.Product;
import Users.Customer;
import Users.Seller;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ShopMain {
    private static Product[] shop_product;
    String[] shop_product_names = {
            "phone",
            "tablet",
            "computer"
    };





    public static void interface_redirect(int userType) throws SQLException, InterruptedException {


        //choce wich interface to show depnde on wich user type we get bac
        if (userType == 1)
            seller_interface();

        if (userType == 2)
            user_interface();

        if (userType == 3)
            CustomerInterface.createUser();
    }




    //show all products in the shop
    private static void get_all_products() throws SQLException {
        shop_product = ProductConnector.getAllProductByName();

    }


    public static void show_all() throws SQLException {

        get_all_products();

        for (int i =0;i < shop_product.length;i++){
            System.out.println("product name: "+shop_product[i].getProductName() +"\n"+
                    "Product ID :"+shop_product[i].getProductId()+"\n"+
                    "Product price: "+shop_product[i].getPrice()+"\n"+
                    "Product discount percent"+shop_product[i].getDiscountPercent()+"\n"+
                    "----------------------------------------------------------------------"+
                    "----------------------------------------------------------------------"
            );
        }
    }




    //user interface
    private static void user_interface() throws SQLException, InterruptedException {

        //some varibalse
        boolean lifeCycle = true;
        int option = 2;
        Scanner s = new Scanner(System.in);

        //here will be the log in and we will work with the objaect
        Customer customer = CustomerInterface.log_in();

        while (lifeCycle) {
            System.out.println("Hello this is the User interface \n" +
                    "chose one option: \n" +
                    "1.Show all your products \n" +
                    "2.Show user details \n" +
                    "3.Update user details \n" +
                    "4.Add product to List \n" +
                    "5.Delete product to list \n" +
                    "6.Exit \n");
            option = s.nextInt();
            switch (option) {
                case 1:
                    CustomerInterface.show_product(customer);
                    TimeUnit.SECONDS.sleep(5);
                    break;
                case 2:
                    CustomerInterface.showUserDetails(customer);
                    TimeUnit.SECONDS.sleep(5);
                    break;
                case 3:
                    CustomerInterface.updateUserDetails(customer);
                    break;
                case 4:
                    CustomerInterface.add_product(customer);
                    break;
                case 5:
                    CustomerInterface.delete_product(customer);
                    break;
                case 6:
                    lifeCycle = false;
                    break;

                default:
                    break;
            }
        }


    }


    //seller interface
    private static void seller_interface() throws SQLException, InterruptedException {

        //some varibalse
        boolean lifeCycle = true;
        int option = 2;
        Scanner s = new Scanner(System.in);

        //here will be the log in and we will work with the objaect
        Seller seller = sellerInterface.log_in();
        if( seller == null)
            return;
        while (lifeCycle) {
            System.out.println("Hello this is the Seller interface \n" +
                    "chose one option: \n" +
                    "1.Show all your products \n" +
                    "2.add a new product \n" +
                    "3.delete a product \n" +
                    "4.go to the customer interface \n" +
                    "5.Exit \n");
            option = s.nextInt();
            switch (option) {
                case 1:
                    sellerInterface.show_product(seller);
                    TimeUnit.SECONDS.sleep(5);
                    break;
                case 2:
                    sellerInterface.add_product(seller);
                    break;
                case 3:
                    sellerInterface.delete_product(seller);
                    break;
                case 4:
                    lifeCycle = false;
                    interface_redirect(2);
                    break;
                case 5:
                    lifeCycle = false;
                    break;

                default:
                    break;
            }
        }


    }
}
