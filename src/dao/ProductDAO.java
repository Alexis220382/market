package dao;

import MyException.MyException;
import dto.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDAO {

    private Properties prop = new Properties();
    private Product product;
    private Connection con;
    private PreparedStatement getProductSt;
    private PreparedStatement getProductByIdSt;
    private PreparedStatement addProductSt;
    private PreparedStatement setProductSt;
    private PreparedStatement removeProductSt;
    private ResultSet rs;

    public ProductDAO() throws MyException {
        try {
            // Получение соединения с БД
            con = getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new MyException("Соединение с базой данных не установлено");
        }
    }

    // Вспомогательный метод получения соединения
    private Connection getConnection() throws MyException {
        //Открываем поток для чтения данных из файла config.properties и
        //загружаем в поток данные из файла config.properties
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("resourses/config.properties"));
            // Подгрузка драйвера БД
            Class.forName(prop.getProperty("Driver")).newInstance();
            return DriverManager.getConnection(prop.getProperty("database"),
                    prop.getProperty("user"), prop.getProperty("password"));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new MyException("Ошибка!\nОтсутствует соединение с базой данных");
        }
    }

    public List<Product> getProduct() {
        List<Product> products = new ArrayList<Product>();
        try {
            // Подготовка SQL-запроса
            if (getProductSt == null) {
                getProductSt = con.prepareStatement(prop.getProperty("product"));
            }
            // Выполнение запроса
            rs = getProductSt.executeQuery();
            product = null;
            // Перечисляем результаты выборки
            while (rs.next()) {
                // Из каждой строки выборки выбираем результаты,
                // формируем новый объект Product
                // и помещаем его в коллекцию
                product = new Product(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getInt(5));
                products.add(product);
            }
            // Закрываем выборку
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public List<Product> getProductById(int id) {
        List<Product> products = new ArrayList<Product>();
        try {
            // Подготовка SQL-запроса
            if (getProductByIdSt == null) {
                getProductByIdSt = con.prepareStatement(prop.getProperty("productById"));
            } 
            // Указание значений параметров запроса
            getProductByIdSt.setInt(1, id);
            // Выполнение запроса
            rs = getProductByIdSt.executeQuery();
            product = null;
            // Перечисляем результаты выборки
            while (rs.next()) {
                // Из каждой строки выборки выбираем результаты,
                // формируем новый объект Product
                // и помещаем его в коллекцию
                product = new Product(
                        id,
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getInt(4));
                products.add(product);
            }
            // Закрываем выборку и соединение с БД
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public Product addProduct(Product product) {
        try {
            // Подготовка SQL-запроса
            if (addProductSt == null) {
                addProductSt = con.prepareStatement(prop.getProperty("addProduct"));
            }
            // Указание значений параметров запроса
            addProductSt.setInt(1, product.getNumberProduct());
            addProductSt.setString(2, product.getDescription());
            addProductSt.setFloat(3, product.getRate());
            addProductSt.setInt(4, product.getQuantity());
            // Выполнение запроса
            addProductSt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }

    public Product setProduct(Product product) {
        try {
            // Подготовка SQL-запроса
            if (setProductSt == null) {
                setProductSt = con.prepareStatement(prop.getProperty("setProduct"));
            }
            // Указание значений параметров запроса
            setProductSt.setInt(1, product.getNumberProduct());
            setProductSt.setString(2, product.getDescription());
            setProductSt.setFloat(3, product.getRate());
            setProductSt.setInt(4, product.getQuantity());
            setProductSt.setInt(5, product.getId());
            // Выполнение запроса
            setProductSt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }

    public void removeProduct(int id) {
        try {
            // Подготовка SQL-запроса
            if (removeProductSt == null) {
                removeProductSt = con.prepareStatement(prop.getProperty("removeProduct"));
            }
            // Указание значений параметров запроса
            removeProductSt.setInt(1, id);
            // Выполнение запроса
            removeProductSt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void conCloseProduct() {
        try {
            if (getProductSt != null) {
                getProductSt.close();
            }
            if (getProductByIdSt != null) {
                getProductByIdSt.close();
            }
            if (addProductSt != null) {
                addProductSt.close();
            }
            if (setProductSt != null) {
                setProductSt.close();
            }
            if (removeProductSt != null) {
                removeProductSt.close();
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
