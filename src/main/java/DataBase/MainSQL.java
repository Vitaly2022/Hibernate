package DataBase;

import java.sql.*;
import java.util.Scanner;

public class MainSQL {
    //private static final String URL = "jdbc:mysql://localhost:3306/dz?autoReconnect=true&useSSL=false";
    private static final String URL = "jdbc:mysql://localhost:3306/dz";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {

        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //Scanner scan = new Scanner(System.in);
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Select an action");
            System.out.println("1 - View journal");
            System.out.println("2 - Add new student");
            System.out.println("3 - Change student details");
            int menuSelection = scan.nextInt();

            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) System.out.println("Good connect");//проверка соединения

            if (menuSelection == 1) {
                preparedStatement = connection.prepareStatement("select * from autor");
                resultSet = preparedStatement.executeQuery();//возвращаем результат выбоки

                viewTable(resultSet);
            }

            if (menuSelection == 2) {
                Scanner scan2 = new Scanner(System.in);//прикол если использовать первый сканер то не ждет ввода каждого слова
                System.out.println("Insert name:");
                String name = scan2.nextLine();
                System.out.println("Insert surname:");
                String surname = scan2.nextLine();

                preparedStatement = connection.prepareStatement("insert autor (name, surname) VALUES (?,?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, surname);
                int row = preparedStatement.executeUpdate();//вставляем нового типа:)
            }

            if (menuSelection == 3) {
                System.out.println("Insert ID rename:");
                int id = scan.nextInt();
                System.out.println("You pick:");
                preparedStatement = connection.prepareStatement("select * from autor where id=?");
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                viewTable(resultSet);
                Scanner scan3 = new Scanner(System.in);
                System.out.println("Insert new name:");
                String newName = scan3.nextLine();
                System.out.println("Insert surname:");
                String newSurname = scan3.nextLine();
                preparedStatement = connection.prepareStatement("update autor set name=?, surname=? where id=?");//меняем имя
                preparedStatement.setString(1, newName);
                preparedStatement.setString(2, newSurname);
                preparedStatement.setInt(3, id);
                int row = preparedStatement.executeUpdate();
            }

            System.out.println("Our students:");
            preparedStatement = connection.prepareStatement("select * from autor");
            resultSet = preparedStatement.executeQuery();
            viewTable(resultSet);
            connection.close();
        } catch (SQLException e) {
            System.out.println("Dont load driver SQL");
        }

    }

    public static void viewTable(ResultSet table) { //метод вывода журнала
        try {
            while (table.next()) {
                System.out.print(table.getInt("id") + " ");
                System.out.print(table.getString("name") + " ");
                System.out.println(table.getString("surname"));
            }
        } catch (SQLException S) {
            System.out.println("SQL error");
        }
    }

}





