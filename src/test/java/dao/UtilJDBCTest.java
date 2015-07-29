package dao;

import org.junit.*;

import java.io.IOException;
import java.util.Properties;


public class UtilJDBCTest {

    private final Properties prop = new Properties();

    public UtilJDBCTest(){
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readFromPropertiesFileForConnectionTest() throws Exception {
        Assert.assertEquals("com.mysql.jdbc.Driver", prop.getProperty("Driver"));
        Assert.assertEquals("jdbc:mysql://localhost/shop_db", prop.getProperty("database"));
        Assert.assertEquals("root", prop.getProperty("user"));
        Assert.assertEquals("1111", prop.getProperty("password"));
    }

}