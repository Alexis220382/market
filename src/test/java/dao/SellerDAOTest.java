package dao;

import dto.Seller;
import myException.MyException;
import org.junit.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by B50-30 on 28.07.2015.
 */
public class SellerDAOTest{

    private final Properties prop = new Properties();
    private SellerDAO sellerDAO = new SellerDAO();
    private Connection con;
    private PreparedStatement addSellerSt;
    private PreparedStatement removeSellerSt;

    public SellerDAOTest() throws MyException{
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            con = new UtilJDBC().getConnection();
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyException("Error! Connection is not created");
        }
    }

    @Test
    public void readFromProperties(){
        Assert.assertEquals("Select id_seller, last_name, first_name, second_name From seller",
                prop.getProperty("seller"));
        Assert.assertEquals("Select last_name, first_name, second_name From seller Where id_seller=?",
                prop.getProperty("sellerById"));
        Assert.assertEquals("Select id_seller, first_name, second_name From seller Where last_name=?",
                prop.getProperty("sellerByLastName"));
        Assert.assertEquals("Insert into seller (last_name, first_name, second_name) values (?, ?, ?)",
                prop.getProperty("addSeller"));
        Assert.assertEquals("Update seller Set last_name=?, first_name=?, second_name=? Where id_seller=?",
                prop.getProperty("setSeller"));
        Assert.assertEquals("Delete from seller Where id_seller=?",
                prop.getProperty("removeSeller"));
    }

    @Test
    public void writeReadDeleteTest(){
        if (addSellerSt == null) {
            try {
                addSellerSt = con.prepareStatement
                        ("Insert into seller (last_name, first_name, second_name) values ('Tarabanova', 'Tarabania', 'Tarabanievna')");
                addSellerSt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        List<Seller> sellerList = sellerDAO.getSellerByLogin("Tarabanova");
        Assert.assertEquals("Tarabania", sellerList.get(0).getFirstName());
        Assert.assertEquals("Tarabanievna", sellerList.get(0).getSecondName());
        if (removeSellerSt == null) {
            try {
                removeSellerSt = con.prepareStatement
                        ("Delete from seller Where id_seller="+sellerList.get(0).getIdSeller());
                removeSellerSt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        List<Seller> sellerList1 = sellerDAO.getSellerByLogin("Tarabanova");
        Assert.assertTrue(sellerList1.size() == 0);
    }

}