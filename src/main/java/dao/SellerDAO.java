package dao;

import MyException.MyException;
import dto.Seller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SellerDAO {

    private Properties prop = new Properties();
    private Seller seller;
    private Connection con;
    private PreparedStatement getSellerSt;
    private PreparedStatement getSellerByIdSt;
    private PreparedStatement getSellerByLoginSt;
    private PreparedStatement addSellerSt;
    private PreparedStatement setSellerSt;
    private PreparedStatement removeSellerSt;
    private ResultSet rs;

    public SellerDAO() throws MyException {
        try {
            con = getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new MyException("Соединение с базой данных не установлено");
        }
    }

    private Connection getConnection() throws MyException {
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            // Подгрузка драйвера БД
            Class.forName(prop.getProperty("Driver")).newInstance();
            return DriverManager.getConnection(prop.getProperty("database"),
                    prop.getProperty("user"), prop.getProperty("password"));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new MyException("Ошибка!\nОтсутствует соединение с базой данных");
        }
    }

    public List<Seller> getSeller() {
        List<Seller> sellers = new ArrayList<Seller>();
        try {
            if (getSellerSt == null) {
                getSellerSt = con.prepareStatement(prop.getProperty("seller"));
            }
            rs = getSellerSt.executeQuery();
            seller = null;
            while (rs.next()) {
                seller = new Seller(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                sellers.add(seller);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sellers;
    }

    public List<Seller> getSellerById(int id) {
        List<Seller> sellers = new ArrayList<Seller>();
        try {
            if (getSellerByIdSt == null) {
                getSellerByIdSt = con.prepareStatement(prop.getProperty("sellerById"));
            } 
            getSellerByIdSt.setInt(1, id);
            rs = getSellerByIdSt.executeQuery();
            seller = null;
            while (rs.next()) {
                seller = new Seller(
                        id,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3));
                sellers.add(seller);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sellers;
    }

    public List<Seller> getSellerByLogin(String login) {
        List<Seller> sellers = new ArrayList<Seller>();
        try {
            if (getSellerByLoginSt == null) {
                getSellerByLoginSt = con.prepareStatement(prop.getProperty("sellerByLastName"));
            } 
            getSellerByLoginSt.setString(1, login);
            rs = getSellerByLoginSt.executeQuery();
            seller = null;
            while (rs.next()) {
                seller = new Seller(
                        rs.getInt(1),
                        login,
                        rs.getString(2),
                        rs.getString(3));
                sellers.add(seller);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sellers;
    }
    
    public Seller addSeller(Seller seller) {
        try {
            if (addSellerSt == null) {
                addSellerSt = con.prepareStatement(prop.getProperty("addSeller"));
            }
            addSellerSt.setString(1, seller.getLastName());
            addSellerSt.setString(2, seller.getFirstName());
            addSellerSt.setString(3, seller.getSecondName());
            addSellerSt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return seller;
    }

    public Seller setSeller(Seller seller) {
        try {
            if (setSellerSt == null) {
                setSellerSt = con.prepareStatement(prop.getProperty("setSeller"));
            }
            setSellerSt.setInt(1, seller.getIdSeller());
            setSellerSt.setString(2, seller.getLastName());
            setSellerSt.setString(3, seller.getFirstName());
            setSellerSt.setString(4, seller.getSecondName());
            setSellerSt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return seller;
    }

    public void removeSeller(int id) {
        try {
            // Подготовка SQL-запроса
            if (removeSellerSt == null) {
                removeSellerSt = con.prepareStatement(prop.getProperty("removeSeller"));
            }
            // Указание значений параметров запроса
            removeSellerSt.setInt(1, id);
            // Выполнение запроса
            removeSellerSt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void conCloseSeller() {
        try {
            if (getSellerSt != null) {
                getSellerSt.close();
            }
            if (getSellerByIdSt != null) {
                getSellerByIdSt.close();
            }
            if (getSellerByLoginSt != null) {
                getSellerByLoginSt.close();
            }
            if (addSellerSt != null) {
                addSellerSt.close();
            }
            if (setSellerSt != null) {
                setSellerSt.close();
            }
            if (removeSellerSt != null) {
                removeSellerSt.close();
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
