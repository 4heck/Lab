package Util;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DBWork {
    private Connection connection;  // Состояние
    private String dbAdress;        // Адрес БД
    private int dbPort;             // Порт БД
    private String dbName;          // Имя БД
    private String userName;        // Имя пользователя БД
    private String userPassword;    // Пароль пользователя БД
    private static DBWork db;       // Статичный объект БД

    // Установить соединения
    private void connect() {
        try {
            readProperties();
            String pass="1";
            connection = DriverManager.getConnection(dbAdress + dbPort + "/" + dbName, userName, pass);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    // Получить экзмемпляр объект БД
    public static DBWork getInstance() {
        if (db == null) {
            db = new DBWork();
        }
        return db;
    }

    // Получить состояние
    public Connection getConnection() {
        if (connection == null) {
            connect();
        }
        return connection;
    }

    // Прочитать свойства из файла и записать в поля
    private void readProperties() {
        Properties properties;
        try {
            File file = new File("data.properties");
            if (file.exists()) {
                properties = new Properties();
                properties.load(new FileInputStream(file));
                dbAdress = properties.getProperty("db_adress");
                dbPort = Integer.parseInt(properties.getProperty("db_port"));
                dbName = properties.getProperty("db_name");
                userName = properties.getProperty("user_name");
                userPassword = properties.getProperty("user_password");
            }
        } catch (IOException | NumberFormatException e) {
            throw new Error(e);
        }
    }


}
