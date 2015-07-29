package dao;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by B50-30 on 28.07.2015.
 */
public class CombineQuerryDAOTest {

    private final Properties prop = new Properties();

    public CombineQuerryDAOTest(){
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readFromProperties(){
        Assert.assertEquals("Select seller.last_name, seller.first_name, seller.second_name, " +
                        "products.description, resulting.rate, resulting.quantity, resulting.date_saled " +
                        "From resulting, products, seller Where products.id_products=resulting.id_products " +
                        "And seller.id_seller=resulting.id_seller And seller.last_name=? And resulting.date_saled>=? " +
                        "And resulting.date_saled<=?",
                prop.getProperty("combineQuerryWhoFromTill"));
    }
}
