package DB;

import Product.*;

import java.sql.SQLException;

import java.sql.* ;

import Product.Product;
import Users.Customer;
import Users.Seller;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class CustomerConnector extends DBconnector {




    protected static String sqlQuery(int queryNumber){

        String query = "";
        switch (queryNumber) {
            case 1:
                query = "SELECT customer_id , password FROM customer WHERE user_name LIKE ";
                break;
            case 2:
                query = "SELECT * FROM customer WHERE customer_id = ";
                break;
            case 3:
                query = "insert into customer (user_name, password, delivery_address, phone_number) VALUES ";
                break;
            case 4:
                query = "UPDATE customer SET ";
                break;
            case 5:
                query = "SELECT * FROM product where seller_id =";
                break;
            default:
                break;
        }
        return query;
    }


    //uses query 1
    public static int loginUser(String userName, String password) throws SQLException {
        int result = -1;

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pass);
        dataSource.setServerName(url);



        Connection connection = DriverManager.getConnection(url , user , pass);

        stmt = connection.createStatement();

        String str =sqlQuery(1)+"'"+userName+"'";

        ResultSet res = stmt.executeQuery(str);
        if(res.next())
            if (password.equals(res.getString("password")))
                result = res.getInt("customer_id");

        res.close();
        stmt.close();
        connection.close();
        return result;
    }
    //uses query 2
    public static Customer get_customer_data(int user_id) throws SQLException {

        Customer customer = new Customer();

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pass);
        dataSource.setServerName(url);



        Connection connection = DriverManager.getConnection(url , user , pass);

        stmt = connection.createStatement();

        ResultSet res = stmt.executeQuery(sqlQuery(2) +user_id);
        if(res.next()) {
            customer.setUserId(res.getInt("customer_id"));
            customer.setUserName(res.getString("user_name"));
            customer.setDeliveryAddress(res.getString("delivery_address"));
            customer.setPhoneNumber(res.getString("phone_number"));

        }
        res.close();
        stmt.close();
        connection.close();

        return customer;
    }


    //uses query 2
    public static Seller get_seller_data(int user_id) throws SQLException {

        Seller seller = new Seller();


        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pass);
        dataSource.setServerName(url);

        Connection connection = DriverManager.getConnection(url , user , pass);

        stmt = connection.createStatement();

        ResultSet res = stmt.executeQuery(sqlQuery(2) +user_id);
        if(res.next())
            if(res.getInt("seller_id") > 0)

                seller.setSellerId(res.getInt("seller_id"));

        res.close();
        stmt.close();
        connection.close();

        return seller;
    }

    //uses query 3
    public static boolean create_user(Customer newCustomer) throws SQLException {


        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pass);
        dataSource.setServerName(url);
        Connection connection = DriverManager.getConnection(url , user , pass);

        stmt = connection.createStatement();


        stmt.executeUpdate(sqlQuery(3)+"("
                +"'"+newCustomer.getUserName()+"'"+","
                +"'"+newCustomer.getPassword()+"'"+","
                +"'"+newCustomer.getDeliveryAddress()+"'"+","
                +"'"+newCustomer.getPhoneNumber()+"'"+")"
        );

        stmt.close();
        connection.close();
        return true;
    }

    //uses query 4
    public static int update_user_data(int user_id, String column_name, String value) throws SQLException {
        Customer customer = new Customer();
        int result = -1;

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pass);
        dataSource.setServerName(url);


        Connection connection = DriverManager.getConnection(url , user , pass);
        stmt = connection.createStatement();
        String str = sqlQuery(4) + column_name + "=" + "'" + value + "'" + " WHERE customer_id = " + user_id;
        result = stmt.executeUpdate(str);
        stmt.close();
        connection.close();

        return result;
    }

    //uses qurey 5
    public static Product [] get_all_seller_items(int sellerId) throws SQLException {
        int size  = 0;
        Product[] sellerProducts;

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pass);
        dataSource.setServerName(url);
        Connection connection = DriverManager.getConnection(url , user , pass);

        stmt = connection.createStatement();

        ResultSet sizeres = stmt.executeQuery("SELECT COUNT(*) FROM product where seller_id = " + sellerId);
        if(sizeres.next())
            size = sizeres.getInt("COUNT(*)");
        else
            return null;
        sizeres.close();
        sellerProducts = new Product[size];

        ResultSet res = stmt.executeQuery(sqlQuery(5) + sellerId);

        for(int i = 0;res.next();i++){
            sellerProducts[i] = productFactory.getProduct(res.getString("type"));
            sellerProducts[i].setProductId(res.getInt("product_id"));
            sellerProducts[i].setProductName(res.getString("product_name"));
            sellerProducts[i].setPrice(res.getFloat("price"));
            sellerProducts[i].setSellerId(res.getInt("seller_id"));
            sellerProducts[i].setDiscountPercent(res.getInt("discount_percent"));
            sellerProducts[i].setDescription(res.getString("product_id"));
        }
        stmt.close();
        res.close();
        connection.close();
        return sellerProducts;
    }
}
