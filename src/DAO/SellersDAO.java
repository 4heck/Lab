package DAO;

import Domain.Sellers;
import Util.DBWork;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellersDAO implements GenerateDAO<Sellers> {

    @Override
    public Sellers create(Sellers object) {
        try {
            // Запрос следующего свободного name в последовательности
            final String SQL_SELECT_NAME = "SELECT Имя from Продавцы";
            // Запрос вставки нового кортежа
            final String SQL_INSERT = "INSERT INTO Продавцы(Имя, Город, Название, Цена, Количество) VALUES (?, ?, ?, ?, ?)";

            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            Statement statement = connection.createStatement();

            // Получаем следующий свободный id в последовательности
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_NAME);
            String name = "";
            if (resultSet.next()) {
                name = resultSet.getString("Имя");
            }

            // Устанавливаем значения в запрос
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getCity());
            preparedStatement.setString(3, object.getProducts());
            preparedStatement.setInt(4, object.getPrice());
            preparedStatement.setInt(5, object.getAmount());

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
    public void update(Sellers object) {
        try {
            // Запрос обновления данных кортежа
            final String SQL_UPDATE = "UPDATE Продавцы SET Город = ?, Название = ?, Цена = ?, Количество = ? WHERE Имя = ?";

            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);

            // Устанавливаем значение в запрос
            preparedStatement.setString(5, object.getName());
            preparedStatement.setString(1, object.getCity());
            preparedStatement.setString(2, object.getProducts());
            preparedStatement.setInt(3, object.getPrice());
            preparedStatement.setInt(4, object.getAmount());


            // Выполняем запрос
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public void delete(Sellers object) {
        try {
            // Запрос на удаление кортежа
            final String SQL_DELETE = "DELETE FROM Продавцы WHERE Имя = ?";

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
    public Sellers getById(String name1) {
        try {
            // Запрос поиска кортежа по id
            final String SQL_SELECT_BY_NAME = "SELECT * FROM Продавцы WHERE Имя = ?";

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
            Sellers sellers = null;
            if (resultSet.next()) {
                String name = resultSet.getString("Имя");
                String city = resultSet.getString("Город");
                String products = resultSet.getString("Название");
                Integer price = resultSet.getInt("Цена");
                Integer amount = resultSet.getInt("Количество");


                sellers = new Sellers(name, city, products, price, amount);
            }

            // Возвращаем результат
            return sellers;
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public List getAll() {
        try {
            // Запрос поиска всех кортежей
            final String SQL_SELECT = "SELECT * FROM Продавцы";

            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            Statement statement = connection.createStatement();

            // Выполняем запрос
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            // Чтение результатов поиска
            ArrayList<Sellers> sellers = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("Имя");
                String city = resultSet.getString("Город");
                String products = resultSet.getString("Название");
                Integer price = resultSet.getInt("Цена");
                Integer amount = resultSet.getInt("Количество");


                sellers.add(new Sellers(name, city, products, price, amount));
            }

            // Возвращаем результат
            return sellers;
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
                String INSERT_INTO_TABLE = "INSERT INTO " + table + " VALUES(?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_TABLE);
                preparedStatement.setString(1, s.split(",")[0]);
                preparedStatement.setString(2, s.split(",")[1]);
                preparedStatement.setString(3, s.split(",")[2]);
                preparedStatement.setInt(4, Integer.parseInt(s.split(",")[3]));
                preparedStatement.setInt(5, Integer.parseInt(s.split(",")[4]));
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
    }}
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

                String INSERT_INTO_TABLE = "INSERT INTO " + table + " VALUES(?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_TABLE);
                Statement statement = connection.createStatement();
                statement.executeUpdate(INSERT_INTO_TABLE);
                preparedStatement.setString(1, s);
                preparedStatement.setString(2, s);
                preparedStatement.setString(3, s);
                preparedStatement.setInt(4, Integer.parseInt(s));
                preparedStatement.setInt(5, Integer.parseInt(s));

                // Выполняем запрос
                preparedStatement.executeUpdate();
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            throw new Error(e);
        }
    }}*/

