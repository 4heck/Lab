package DAO;

import Domain.Shop;
import Util.DBWork;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopDAO implements GenerateDAO<Shop>{

    @Override
    public Shop create(Shop object) {
        try {
            // Запрос следующего свободного name в последовательности
            final String SQL_SELECT_NAME = "SELECT Название from Магазин";
            // Запрос вставки нового кортежа
            final String SQL_INSERT = "INSERT INTO Магазин(Название, Тип) VALUES (?, ?)";

            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            Statement statement = connection.createStatement();

            // Получаем следующий свободный name в последовательности
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_NAME);
            String name = "";
            if (resultSet.next()) {
                name = resultSet.getString("Название");
            }

            // Устанавливаем значения в запрос
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getType());

            // Выполняем запрос
            preparedStatement.executeUpdate();

            // Возвращаем объект вставки
            object.setName(name);
            return object;
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public void update(Shop object) {
        try {
            // Запрос обновления данных кортежа
            final String SQL_UPDATE = "UPDATE Магазин SET Тип = ? WHERE Название = ?";

            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);

            // Устанавливаем значение в запрос
            preparedStatement.setString(2, object.getName());
            preparedStatement.setString(1, object.getType());

            // Выполняем запрос
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public void delete(Shop object) {
        try {
            // Запрос на удаление кортежа
            final String SQL_DELETE = "DELETE FROM Магазин WHERE Имя = ?";

            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);

            // Устанавливаем значение в запрос
            preparedStatement.setString(1, object.getName());

            // Выполняем запрос
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public Shop getById(String name1) {
        try {
            // Запрос поиска кортежа по id
            final String SQL_SELECT_BY_NAME = "SELECT * FROM Магазин WHERE Название = ?";

            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_NAME);

            // Устанавливаем значение в запрос
            preparedStatement.setString(1, name1);

            // Выполняем запрос
            ResultSet resultSet = preparedStatement.executeQuery();

            // Чтение результатов запроса
            Shop shop = null;
            if (resultSet.next()) {
                String name = resultSet.getString("Название");
                String type = resultSet.getString("Тип");

                shop = new Shop(name, type);
            }

            // Возвращаем результат
            return shop;
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public List getAll() {
        try {
            // Запрос поиска всех кортежей
            final String SQL_SELECT = "SELECT * FROM Магазин";

            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            Statement statement = connection.createStatement();

            // Выполняем запрос
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            // Чтение результатов поиска
            ArrayList<Shop> shop = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("Название");
                String type = resultSet.getString("Тип");


                shop.add(new Shop(name, type));
            }

            // Возвращаем результат
            return shop;
        } catch (SQLException e) {
            throw new Error(e);
        }
    }
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
