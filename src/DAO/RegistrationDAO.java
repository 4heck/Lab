package DAO;

import Domain.Registration;
import Util.DBWork;
import org.postgresql.util.PGTimestamp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RegistrationDAO implements GenerateDAO<Registration>{

    @Override
    public Registration create(Registration object) {
        try {
            // Запрос следующего свободного name в последовательности
            final String SQL_SELECT_NAME = "SELECT Имя from Регистрация";
            // Запрос вставки нового кортежа
            final String SQL_INSERT = "INSERT INTO Регистрация(Имя, Дата_регистрации, Количество_позиций) VALUES (?, ?, ?)";

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
                name = resultSet.getString("Имя");
            }

            // Устанавливаем значения в запрос
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getDateRegistration());
            preparedStatement.setString(3, object.getNumberOfPositions());

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
    public void update(Registration object) {
        try {
            // Запрос обновления данных кортежа
            final String SQL_UPDATE = "UPDATE Регистрация SET Дата_регистрации = ?, Количество_позиций = ? WHERE Имя = ?";

            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);

            // Устанавливаем значение в запрос
            preparedStatement.setString(3, object.getName());
            preparedStatement.setString(1, object.getDateRegistration());
            preparedStatement.setString(2, object.getNumberOfPositions());

            // Выполняем запрос
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new Error(e);
        }

    }

    @Override
    public void delete(Registration object) {
        try {
            // Запрос на удаление кортежа
            final String SQL_DELETE = "DELETE FROM Регистрация WHERE Имя = ?";

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
    public Registration getById(String name1) {
        try {
            // Запрос поиска кортежа по name
            final String SQL_SELECT_BY_NAME = "SELECT * FROM Регистрация WHERE Имя = ?";

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
            Registration registration = null;
            if (resultSet.next()) {
                String name = resultSet.getString("Имя");
                String dateRegistration = resultSet.getString("Дата_регистрации");
                String numberOfPositions = resultSet.getString("Количество_позиций");

                registration = new Registration(name, dateRegistration, numberOfPositions);
            }

            // Возвращаем результат
            return registration;
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public List<Registration> getAll() {
        try {
            // Запрос поиска всех кортежей
            final String SQL_SELECT = "SELECT * FROM Регистрация";

            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            // Создаем состояния
            Statement statement = connection.createStatement();

            // Выполняем запрос
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            // Чтение результатов поиска
            ArrayList<Registration> registrations = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("Имя");
                String numberOfPositions = resultSet.getString("Количество_позиций");
                String dateRegistration = resultSet.getString("Дата_регистрации");

                registrations.add(new Registration(name, dateRegistration, numberOfPositions));
            }

            // Возвращаем результат
            return registrations;
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
                String INSERT_INTO_TABLE = "INSERT INTO " + table + " VALUES(?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_TABLE);
                preparedStatement.setString(1, s.split(",")[0]);
                preparedStatement.setInt(3, Integer.parseInt(s.split(",")[1]));
               // preparedStatement.setString(3, s.split(",")[2]);


                //DateFormat format = new DateFormat("yyyy-MM-dd");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                LocalDate date;
                date = LocalDate.parse(s.split(",")[2], dtf);
                //format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss.SSSSSSX", Locale.getDefault());
                //String text = format.format(date);


                //System.out.println(text);
                preparedStatement.setObject(2, date);


                System.out.println(preparedStatement.toString());

                // Выполняем запрос
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        } catch (SQLException e) {
            throw new Error(e);
        }
    }
    public static java.sql.Date convertToSqlDate(Date utilDate){
        return new java.sql.Date(utilDate.getTime());
    }
}
