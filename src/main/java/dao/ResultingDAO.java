package dao;

import MyException.MyException;
import dto.Resulting;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;


public class ResultingDAO {
    
    private Properties prop = new Properties();
    private Connection con;
    private PreparedStatement addResultSt;
    private PreparedStatement removeResultSt;

    public ResultingDAO() throws MyException {
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

    public Resulting addResulting(Resulting result) {
        try {
            if (addResultSt == null) {
                addResultSt = con.prepareStatement(prop.getProperty("addResulting"));
            }
            addResultSt.setInt(1, result.getIdResultSeller());
            addResultSt.setInt(2, result.getIdResultProducts());
            addResultSt.setFloat(3, result.getRateResult());
            addResultSt.setInt(4, result.getQuantityResult());
            addResultSt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public void removeResultingByIdSeller(int id_seller) {
        try {
            if (removeResultSt == null) {
                removeResultSt = con.prepareStatement(prop.getProperty("removeResulting"));
            }
            removeResultSt.setInt(1, id_seller);
            removeResultSt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void conCloseResulting() {
        try {
            if (removeResultSt != null) {
                removeResultSt.close();
            }
            if (addResultSt != null) {
                addResultSt.close();
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
