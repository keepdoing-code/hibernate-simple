package ru.keepdoing;

import ru.keepdoing.Entity.Student;
import ru.keepdoing.Repository.StudentRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MainApp {
    private static final String CREATE_TABLE =
            "create table if not exists student (" +
                    "id integer primary key autoincrement," +
                    "name text not null," +
                    "age integer not null)";

    public static void main(String[] args) {
        createTable();
        StudentRepository.insert(new Student("Yuri", 31));
        StudentRepository.insert(new Student("Sashka", 28));
        StudentRepository.delete(1);
        List<Student> list = StudentRepository.getAll();
        list.forEach(System.out::println);
        HibernateUtil.shutdown();
    }

    static void createTable(){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:studentsdb");
            statement = connection.createStatement();
            statement.execute(CREATE_TABLE);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try{
                connection.close();
                statement.close();
            } catch (NullPointerException e){
                System.out.println("Connection not opened");
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
