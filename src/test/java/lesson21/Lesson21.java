package lesson21;

import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sql.SelectHelper;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class Lesson21 {

    Connection connection;
    Statement statement;

    SelectHelper selectHelper;

    @SneakyThrows //позволяет не писать try catch
    @BeforeTest
    public void preconditions() { //создает соединение с БД
        connection = DriverManager.getConnection("jdbc:mysql://db4free.net/testqa07?user=testqa07&password=testqa07");
        statement = connection.createStatement(); //содержит реализации для запросов в БД
        selectHelper = new SelectHelper(statement);
        print(selectHelper.select("SELECT * FROM user"));
    }

    @Test(priority = 1)
    public void selectLike_Test() {
        String sql = "SELECT * FROM user WHERE last_name LIKE 'test%'";
        print(selectHelper.select(sql));
        selectHelper.select(sql).forEach(row -> {
            Assert.assertTrue(row.get("last_name").contains("test"));
        });
    }

    @Test(priority = 2)
    public void selectBetween_Test() {
        String sql = "SELECT * FROM user WHERE age BETWEEN 20 AND 30";
        print(selectHelper.select(sql));
        selectHelper.select(sql).forEach(row -> {
            Assert.assertTrue(Integer.parseInt(row.get("age")) >= 20 && Integer.parseInt(row.get("age")) <= 30);
        });
    }

    @Test(priority = 3)
    public void update_Test() {
        print(selectHelper.select("SELECT * FROM user WHERE first_name is null"));
        update("UPDATE user SET first_name=null WHERE first_name='User_2'");
        print(selectHelper.select("SELECT * FROM user WHERE first_name is null"));
    }

    @Test(priority = 4)
    public void insert_Test() {
        print(selectHelper.select("SELECT * FROM user WHERE first_name is null"));
        insert("INSERT INTO user (id, last_name) VALUES (100, 'QA16onl')");
        print(selectHelper.select("SELECT * FROM user WHERE first_name is null"));
    }

    @Test(priority = 5)
    public void delete_Test() {
        print(selectHelper.select("SELECT * FROM user WHERE first_name is null"));
        delete("DELETE FROM user WHERE last_name='QA16onl'");
        print(selectHelper.select("SELECT * FROM user WHERE first_name is null"));
    }

    @SneakyThrows
    private Integer update(String sql) { //метод для update
        return statement.executeUpdate(sql);
    }

    @SneakyThrows
    private Integer insert(String sql) {
        return statement.executeUpdate(sql);
    }

    @SneakyThrows
    private Integer delete(String sql) {
        return statement.executeUpdate(sql);
    }

    @SneakyThrows
    private Integer createTable(String sql) {
        return statement.executeUpdate(sql);
    }

    private void print(List<Map<String, String>> data) {
        System.out.println("===================================================");
        data.forEach(System.out::println);
        System.out.println("===================================================");
    }
}
