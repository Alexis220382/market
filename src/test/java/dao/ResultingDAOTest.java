package dao;

import myException.MyException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by B50-30 on 28.07.2015.
 */
public class ResultingDAOTest {

    private final Properties prop = new Properties();

    public ResultingDAOTest() throws MyException{
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyException("Error! Connection is not created");
        }
    }

    @Test
    public void readFromProperties(){
        Assert.assertEquals("Insert into resulting (id_seller, id_products, date_saled, rate, quantity) values (?, ?, CURDATE(), ?, ?)",
                prop.getProperty("addResulting"));
        Assert.assertEquals("Delete from resulting Where id_seller=?",
                prop.getProperty("removeResulting"));
    }


}
