package Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FreeQuery {
    public void freeQuery(){
        try {
            // Обращаемся к объекту БД
            DBWork db = DBWork.getInstance();
            // Обращаемся к состоянию БД
            Connection connection = db.getConnection();
            Statement statement = connection.createStatement();
            String s = null;
            System.out.println("Выберите режим работы FreeQuery:");
            System.out.println("1. Города");
            System.out.println("2. Имя, чья регистрация 2004-01-01");
            System.out.println("3. Имена продавцов");
            System.out.println("4. Названия и цены продуктов");
            System.out.println("5. Продукт и его поставщик");
            System.out.println("6. Поставщик и его полн. стоим.");
            System.out.println("7. Имя продавцы и его средн. кол-во");
            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();
            switch(choice) {
                case 1:
                    s = "SELECT DISTINCT Город\n" +
                            "FROM Продавцы;";
                    ResultSet rs = statement.executeQuery(s);
                    while(rs.next()) {
                        String city = rs.getString(1);
                        System.out.println(city);
                    }
                    rs.close();
                    break;
                case 2:
                    s = "SELECT Имя \n" +
                            "FROM Регистрация\n" +
                            "WHERE Дата_регистрации >= '2004-01-01'\n";
                    rs = statement.executeQuery(s);
                    while(rs.next()) {
                        String fio = rs.getString("Имя");
                        System.out.println(fio);
                    }
                    rs.close();
                    break;
                case 3:
                    s = "SELECT Имя, COUNT(Название), SUM(Количество)\n" +
                            "FROM Продавцы\n" +
                            "GROUP BY Имя";
                    rs = statement.executeQuery(s);
                    while(rs.next()) {
                        String fio = rs.getString("Имя");
                        int count = rs.getInt("COUNT");
                        int sum = rs.getInt("SUM");
                        System.out.println(fio + " " + count + " " + sum);
                    }
                    rs.close();
                    break;
                case 4:
                    s = "SELECT Название, AVG(Цена)\n" +
                            "FROM Продавцы\n" +
                            "GROUP BY Название";
                    rs = statement.executeQuery(s);
                    while(rs.next()) {
                        String name = rs.getString("Название");
                        double avg = rs.getDouble("AVG");
                        System.out.println(name + " " + avg);
                    }
                    rs.close();
                    break;
                case 5:
                    s = "SELECT Магазин.Название, Поставщик.Поставщик\n" +
                            "FROM Магазин INNER JOIN Поставщик ON Магазин.Тип = Поставщик.Тип";
                    rs = statement.executeQuery(s);
                    while(rs.next()) {
                        String name = rs.getString("Название");
                        String supplier = rs.getString("Поставщик");
                        System.out.println(name + " " + supplier);
                    }
                    rs.close();
                    break;
                case 6:
                    s = "SELECT Поставщик.Поставщик, SUM(Продавцы.Цена / 1.2 * Продавцы.Количество)\n" +
                            "FROM Поставщик INNER JOIN Магазин ON Магазин.Тип = Поставщик.Тип INNER JOIN Продавцы ON Магазин.Название = Продавцы.Название\n" +
                            "GROUP BY Поставщик.Поставщик\n";
                    rs = statement.executeQuery(s);
                    while(rs.next()) {
                        String supplier = rs.getString("Поставщик");
                        double sum = rs.getDouble("SUM");
                        System.out.println(supplier + " " + sum);
                    }
                    rs.close();
                    break;
                case 7:
                    s = "SELECT Имя, AVG(Количество)\n" +
                            "FROM Продавцы\n" +
                            "GROUP BY Имя\n" +
                            "HAVING AVG(Количество) > (SELECT AVG(Количество) FROM Продавцы)";
                    rs = statement.executeQuery(s);
                    while(rs.next()) {
                        String fio = rs.getString("Имя");
                        double avg = rs.getDouble("AVG");
                        System.out.println(fio + " " + avg);
                    }
                    rs.close();
                    break;
                default:
                    break;

            }
            statement.close();
        } catch(SQLException e){
            throw new Error(e);
        }
    }
}
