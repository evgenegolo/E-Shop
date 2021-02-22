package Shop;

import DB.CustomerConnector;
import DB.ProductConnector;
import Users.Customer;
import Product.Product;
import Users.Seller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CustomerInterface{

    //Customer details methods

    public static Customer log_in() throws SQLException {
        Customer customer = new Customer();
        String userName, password;
        Scanner s = new Scanner(System.in);


        System.out.println("Enter Your User Name:");
        userName = s.nextLine();
        System.out.println("Enter Your Password:");
        password = s.nextLine();
        customer.setUserId(CustomerConnector.loginUser(userName , password));
        if(customer.getUserId() > 0){
            customer = CustomerConnector.get_customer_data(customer.getUserId());
            customer.setStatus(true);
            System.out.println("Welcome to the shop!");
        }
        return customer;
    }

    public static void log_out(Customer customer){

        if(customer.getStatus() == true)
            customer.setStatus(false);
    }


    public static Customer createUser() throws SQLException {

        Customer customer = new Customer();
        Scanner s = new Scanner(System.in);

        System.out.println("Enter your user name");
        customer.setUserName(s.nextLine());
        System.out.println("Enter your password");
        customer.setPassword(s.nextLine());
        System.out.println("Enter your delivery address");
        customer.setDeliveryAddress(s.nextLine());
        System.out.println("Enter your phone number");
        customer.setPhoneNumber(s.nextLine());

        CustomerConnector.create_user(customer);

        return customer;

    }

    public static void showUserDetails(Customer customer) throws  SQLException{

        customer = CustomerConnector.get_customer_data(customer.getUserId());
        System.out.println("Customer ID: " + customer.getUserId());
        System.out.println("User name: " + customer.getUserName());
        System.out.println("Delivery address: " + customer.getDeliveryAddress());
        System.out.println("Phone number: " + customer.getPhoneNumber());
    }

    public static void updateUserDetails(Customer customer) throws SQLException{

        Scanner number = new Scanner(System.in);
        Scanner str = new Scanner(System.in);
        String updateData;
        int result = -1;

        System.out.println("Choose what to update:");
        System.out.println("Press 1 for user name");
        System.out.println("Press 2 for password");
        System.out.println("Press 3 for delivery address");
        System.out.println("Press 4 for phone number");

        int option = number.nextInt();

        System.out.println("Enter your text to update:");
        updateData = str.nextLine();
        switch(option) {
            case 1: result = CustomerConnector.update_user_data(customer.getUserId(), "user_name", updateData);
                break;
            case 2: result = CustomerConnector.update_user_data(customer.getUserId(), "password", updateData);
                break;
            case 3: result = CustomerConnector.update_user_data(customer.getUserId(), "delivery_address", updateData);
                break;
            case 4: result = CustomerConnector.update_user_data(customer.getUserId(), "phone_number", updateData);
                break;
            default:
                break;
        }
        if(result >= 0)
            System.out.println("Data was updated");
        else
            System.out.println("Failed to update data");
    }


    //Products details methods

    public static void show_product(Customer customer) {
        Product[] products = customer.getProducts();
        if (products != null) {
            for (int i = 0; i < products.length; i++) {
                System.out.println("Product number: " + (i+1));
                System.out.println("Product name: " + products[i].getProductName());
                System.out.println("Price: " + products[i].getPrice());
                System.out.println("Discount: " + products[i].getDiscountPercent());
                System.out.println("Description: " + products[i].getDescription());
                System.out.println("----------------------------------------------");
                System.out.println("----------------------------------------------");
            }
        } else
            System.out.println("Your products list is empty");
    }


    public static void delete_product(Customer customer) {

        Scanner s = new Scanner(System.in);
        Product[] products = customer.getProducts();
        Product[] new_list;
        Product temp;
        int product_to_delete;
        boolean flag =true;

        if (products.length <= 1) {
            customer.setProducts(null);
            return;
        }
        do {
            System.out.println("Choose and enter product number you wish to delete or 0 for exit: \n");
            show_product(customer);
            product_to_delete = s.nextInt() -1;

            if(product_to_delete == -1)
                return;

            if (product_to_delete >= products.length || product_to_delete < 0) {
                System.out.println("This product isn't exists in your list \n");

            }
            else
            {
                flag = false;
            }

        } while (flag) ;

                temp = products[products.length - 1];
                products[products.length-1] = products[product_to_delete];
                products[product_to_delete] = temp;

                new_list = Arrays.copyOf(products , products.length-1);
                customer.setProducts(new_list);
    }

    public static void add_product(Customer customer)throws SQLException {
        int index = 0;
        Scanner s = new Scanner(System.in);
        Product[] tempProduct = customer.getProducts();
        Product[] products = ProductConnector.getAllProductByName();



        if(tempProduct == null) {
            tempProduct = new Product[0];
        }

        if (products == null) {
            return;
        }


            tempProduct = Arrays.copyOf(tempProduct, tempProduct.length + 1);


            ShopMain.show_all();
            System.out.println("Enter The Product id you want to add");
            int id = s.nextInt();

            while (index < products.length) {
                if (id == products[index].getProductId())
                    break;
                else
                    index++;
            }

            tempProduct[tempProduct.length - 1] = products[index];
            customer.setProducts(tempProduct);


            System.out.println("if you want to add another product type yes to exit pres any Key \n");
            String answer = s.next();
            if(answer.equalsIgnoreCase("yes"))
                CustomerInterface.add_product(customer);

    }


}



