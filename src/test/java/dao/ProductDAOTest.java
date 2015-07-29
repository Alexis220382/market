package dao;

import dto.Product;
import myException.MyException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by B50-30 on 28.07.2015.
 */
public class ProductDAOTest {

    private final Properties prop = new Properties();
    private ProductDAO productDAO = new ProductDAO();
    private Product product;
    private ResultSet rs;
    private Connection con;
    private PreparedStatement addProductSt;
    private PreparedStatement getProductByDescrSt;
    private PreparedStatement removeProductSt;

    public ProductDAOTest() throws MyException{
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
        Assert.assertEquals("Select id_products, numberProduct, description, rate, quantity From products",
                prop.getProperty("product"));
        Assert.assertEquals("Select numberProduct, description, rate, quantity From products Where id_products=?",
                prop.getProperty("productById"));
        Assert.assertEquals("Insert into products (numberProduct, description, rate, quantity) values (?, ?, ?, ?)",
                prop.getProperty("addProduct"));
        Assert.assertEquals("Update products Set numberProduct=?, description=?, rate=?, quantity=? Where id_products=?",
                prop.getProperty("setProduct"));
        Assert.assertEquals("Delete from products Where id_products=?",
                prop.getProperty("removeProduct"));
    }

    @Test
    public void writeReadDeleteTest(){
        if (addProductSt == null) {
            try {
                addProductSt = con.prepareStatement
                        ("Insert into products (numberProduct, description, rate, quantity) values (9999, 'test', 99.9, 99)");
                addProductSt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        List<Product> productList = new ArrayList<Product>();
        try {
            if (getProductByDescrSt == null) {
                getProductByDescrSt = con.prepareStatement
                        ("Select id_products, numberProduct, description, rate, " +
                                "quantity From products Where description='test'");
            }
            rs = getProductByDescrSt.executeQuery();
            product = null;
            while (rs.next()) {
                product = new Product(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getInt(5));
                productList.add(product);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Assert.assertEquals(9999, productList.get(0).getNumberProduct());
        Assert.assertEquals("test", productList.get(0).getDescription());
//        Assert.assertTrue(productList.get(0).getRate() == 99.9);
        Assert.assertEquals(99, productList.get(0).getQuantity());
        if (removeProductSt == null) {
            try {
                removeProductSt = con.prepareStatement
                        ("Delete from products Where id_products="+productList.get(0).getId());
                removeProductSt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        List<Product> productList1 = productDAO.getProductById(productList.get(0).getId());
        Assert.assertTrue(productList1.size() == 0);
    }
}
