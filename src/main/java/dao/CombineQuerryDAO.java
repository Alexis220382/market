package dao;

import myException.MyException;
import dto.CombineQuerry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CombineQuerryDAO {

    private Properties prop = new Properties();
    private CombineQuerry combineQuerry;
    private PreparedStatement getCombineQuerryWhoFromTillSt;
    private Connection con;
    private ResultSet rs;

    public CombineQuerryDAO() throws MyException {
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            con = new UtilJDBC().getConnection();
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException("\"Ошибка!\\nОтсутствует соединение с базой данных\"");
        }
    }

    public List<CombineQuerry> getCombineQuerryWhoFromTill(String lastName, String dateFrom, String dateTill) {
        List<CombineQuerry> combineQuerrys = new ArrayList<CombineQuerry>();
        try {
            if (getCombineQuerryWhoFromTillSt == null) {
                getCombineQuerryWhoFromTillSt = con.prepareStatement(prop.getProperty("combineQuerryWhoFromTill"));
            }
            getCombineQuerryWhoFromTillSt.setString(1, lastName);
            getCombineQuerryWhoFromTillSt.setString(2, dateFrom);
            getCombineQuerryWhoFromTillSt.setString(3, dateTill);
            rs = getCombineQuerryWhoFromTillSt.executeQuery();
            combineQuerry = null;
            while (rs.next()) {
                combineQuerry = new CombineQuerry(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getFloat(5),
                        rs.getInt(6),
                        rs.getString(7),
                        lastName,
                        dateFrom,
                        dateTill);
                combineQuerrys.add(combineQuerry);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return combineQuerrys;
    }

    public void conCloseCombineQuerry() {
        try {
            if (getCombineQuerryWhoFromTillSt != null) {
                getCombineQuerryWhoFromTillSt.close();
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
