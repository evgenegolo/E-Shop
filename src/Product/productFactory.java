package Product;

public class productFactory {

    public static Product getProduct(String productType){
        if(productType == null)
            return null;
        if(productType.equalsIgnoreCase("Phone"))
            return new Phone();
        if(productType.equalsIgnoreCase("Computer"))
            return new Computer();
        if(productType.equalsIgnoreCase("Tablet"))
            return new Tablet();
        return null;
    }
}
