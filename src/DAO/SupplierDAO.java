package DAO;

import Domain.Shop;
import Domain.Suppliers;
import Util.DBWork;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO implements GenerateDAO<Suppliers> {
    @Override
    public Suppliers create(Suppliers object) {
        try {
            // Запрос следующего свободного name в последовательности
            final String SQL_SELECT_TYPE = "SELECT Тип from Поставщик";
            // Запрос вставки нового кортежа
            final String SQL_INSERT = "INSERT INTO Поставщик(Тип, Поставщик) VALUES (?, ?)";

            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            Statement statement = connection.createStatement();

            // Получаем следующий свободный name в последовательности
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_TYPE);
            String type = "";
            if (resultSet.next()) {
                type = resultSet.getString("Тип");
            }

            // Устанавливаем значения в запрос
            preparedStatement.setString(1, object.getType());
            preparedStatement.setString(2, object.getSupplier());

            // Выполняем запрос
            preparedStatement.executeUpdate();

            // Возвращаем объект вставки
            object.setType(type);
            return object;
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public void update(Suppliers object) {
        try {
            // Запрос обновления данных кортежа
            final String SQL_UPDATE = "UPDATE Поставщик SET Поставщик = ? WHERE Тип = ?";

            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);

            // Устанавливаем значение в запрос
            preparedStatement.setString(2, object.getType());
            preparedStatement.setString(1, object.getSupplier());

            // Выполняем запрос
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public void delete(Suppliers object) {
        try {
            // Запрос на удаление кортежа
            final String SQL_DELETE = "DELETE FROM Поставщик WHERE Тип = ?";

            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);

            // Устанавливаем значение в запрос
            preparedStatement.setString(1, object.getType());

            // Выполняем запрос
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public Suppliers getById(String name) {
        try {
            // Запрос поиска кортежа по id
            final String SQL_SELECT_BY_NAME = "SELECT * FROM Поставщик WHERE Тип = ?";

            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_NAME);

            // Устанавливаем значение в запрос
            preparedStatement.setString(1, name);

            // Выполняем запрос
            ResultSet resultSet = preparedStatement.executeQuery();

            // Чтение результатов запроса
            Suppliers suppliers = null;
            if (resultSet.next()) {
                String type = resultSet.getString("Тип");
                String supplier = resultSet.getString("Поставщик");

                suppliers = new Suppliers(type, supplier);
            }

            // Возвращаем результат
            return suppliers;
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public List getAll() {
        try {
            // Запрос поиска всех кортежей
            final String SQL_SELECT = "SELECT * FROM Поставщик";

            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            Statement statement = connection.createStatement();

            // Выполняем запрос
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            // Чтение результатов поиска
            ArrayList<Suppliers> suppliers = new ArrayList<>();
            while (resultSet.next()) {
                String type = resultSet.getString("Тип");
                String supplier = resultSet.getString("Поставщик");


                suppliers.add(new Suppliers(type, supplier));
            }

            // Возвращаем результат
            return suppliers;
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    /*public void readFileInsert(String table){
        try(BufferedReader br = new BufferedReader(new FileReader(table+ ".txt")))
        {
            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Чтение построчно и ввод в таблицу
            String s;



            while((s=br.readLine())!=null){
                // Создаем состояния

                String INSERT_INTO_TABLE = "INSERT INTO " + table + " VALUES(?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_TABLE);
                Statement statement = connection.createStatement();
                statement.executeUpdate(INSERT_INTO_TABLE);
                preparedStatement.setString(1, s);
                preparedStatement.setString(2, s);

                // Выполняем запрос
                preparedStatement.executeUpdate();
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            throw new Error(e);
        }
    }*/
    public void readFileInsert(String table){
        try(BufferedReader br = new BufferedReader(new FileReader(table+ ".txt")))
        {
            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Чтение построчно и ввод в таблицу
            String s;
            while((s=br.readLine())!=null){
                // Создаем состояния
                String INSERT_INTO_TABLE = "INSERT INTO " + table + " VALUES(?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_TABLE);
                preparedStatement.setString(1, s.split(",")[0]);
                preparedStatement.setString(2, s.split(",")[1]);
                System.out.println(preparedStatement.toString());

                // Выполняем запрос
                preparedStatement.executeUpdate();
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            throw new Error(e);
        }
    }
}
