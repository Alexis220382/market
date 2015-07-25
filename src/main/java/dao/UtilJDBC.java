package dao;

import myException.MyException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class UtilJDBC {

    private Properties prop = new Properties();

    public Connection getConnection() throws MyException {
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

}
