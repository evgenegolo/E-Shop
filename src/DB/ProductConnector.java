package DB;


import Product.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductConnector extends DBconnector{




    public static String sqlQuery(int queryNumber) {
        String query= "";

        switch(queryNumber){
            case 1:
                query = "insert into product (type,product_name,price,seller_id,discount_percent,description) VALUES";
                break;
            case 2:
                query = "SELECT * FROM product WHERE seller_id = ";
                break;
            case 3:
                query = "SELECT * FROM product WHERE product_id = ";
                break;
            case 4:
                query = "SELECT *  FROM product";
                break;
            case 5:
                query="DELETE FROM product WHERE product_id = ";
                break;
            default:
                break;

        }
        return query;

    }

    //uses the 1 query
   public static void new_product(String productType ,int sellerId) throws SQLException {
       Product newProduct = productFactory.getProduct(productType);
       newProduct.create_product(sellerId);

       MysqlDataSource dataSource = new MysqlDataSource();
       dataSource.setUser(user);
       dataSource.setPassword(pass);
       dataSource.setServerName(url);
       Connection connection = DriverManager.getConnection(url, user, pass);

       stmt = connection.createStatement();


       //product_name,prise,seller_id,discount_percent,description
       String str = sqlQuery(1);
       //sql query creation

       str += "("
               + "'" + productType + "'" + ","
               + "'" + newProduct.getProductName() + "'" + ","
               + "'" + newProduct.getPrice() + "'" + ","
               + "'" + newProduct.getSellerId() + "'" + ","
               + "'" + newProduct.getDiscountPercent() + "'" + ","
               + "'" + newProduct.getDescription() + "'"
               + ")"
       ;//ends here

       stmt.executeUpdate(str);


       stmt.close();
       connection.close();
   }



    //uses 3 query
    public static Product get_product_by_product(String productType, int productId)throws SQLException {

        Product dbProduct = productFactory.getProduct(productType);

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pass);
        dataSource.setServerName(url);

        Connection connection = DriverManager.getConnection(url,user,pass);

        stmt = connection.createStatement();
        String str = sqlQuery(3)+ productId;
        ResultSet res = stmt.executeQuery(str);

        if(res.next())
        {
            dbProduct.setProductId(res.getInt("product_id"));
            dbProduct.setProductName(res.getString("product_name"));
            dbProduct.setPrice(res.getFloat("price"));
            dbProduct.setDiscountPercent(res.getInt("discount_percent"));
            dbProduct.setDescription(res.getString("product_id"));

        }

        stmt.close();
        res.close();
        connection.close();

        return dbProduct;
    }

    //uses 4 query
    public static Product[] getAllProductByName()throws SQLException {

        Product[] dbProduct ;

        int size = 0;

        String [] shop_product_names = {
                "phone",
                "tablet",
                "computer"
        };


        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pass);
        dataSource.setServerName(url);
        Connection connection = DriverManager.getConnection(url,user,pass);
        stmt = connection.createStatement();

        //getting the umount of the products
        ResultSet sizeres = stmt.executeQuery("SELECT COUNT(*) FROM product");
        if(sizeres.next())
            size = sizeres.getInt("COUNT(*)");
        else
            return null;
        sizeres.close();


        dbProduct = new Product[size];


        String str = sqlQuery(4);
        ResultSet res = stmt.executeQuery(str);



        for(int i = 0;res.next();i++) {
            dbProduct[i] = productFactory.getProduct(res.getString("type"));
            dbProduct[i].setProductId(res.getInt("product_id"));
            dbProduct[i].setProductName(res.getString("product_name"));
            dbProduct[i].setPrice(res.getFloat("price"));
            dbProduct[i].setSellerId(res.getInt("seller_id"));
            dbProduct[i].setDiscountPercent(res.getInt("discount_percent"));
            dbProduct[i].setDescription(res.getString("product_id"));
        }
        stmt.close();
        res.close();
        connection.close();
        return dbProduct;
    }

    public static void delete_product(int productId) throws SQLException{
        MysqlDataSource dataSource= new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pass);
        dataSource.setServerName(url);
        Connection connection = DriverManager.getConnection(url ,user ,pass);

        stmt = connection.createStatement();

        String str = sqlQuery(5) + productId;

         stmt.executeUpdate(str);

         stmt.close();
         connection.close();
    }

}
