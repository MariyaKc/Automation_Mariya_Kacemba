package lesson21;

import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import sql.SelectHelper;
import sql.TableHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Lesson21 extends BaseTest {

    Connection connection;
    Statement statement;
    SelectHelper selectHelper;

    @SneakyThrows //позволяет не писать try catch
    @BeforeTest
    public void preconditions() { //создает соединение с БД
        connection = DriverManager.getConnection("jdbc:mysql://db4free.net/testqa07?user=testqa07&password=testqa07");
        statement = connection.createStatement(); //содержит реализации для запросов в БД
        get(TableHelper.class).createTable("user");
        get(TableHelper.class).insert("INSERT INTO user (id, firstname) VALUES (100, 'QA16onl')");
        print(get(TableHelper.class).select("SELECT * FROM user"));
    }

    @Test(priority = 1)
    public void selectLike_Test() {
        String sql = "SELECT * FROM user WHERE last_name LIKE 'test%'";
        print(get(TableHelper.class).select(sql));
        select(sql).forEach(row -> {
            Assert.assertTrue(row.get("last_name").contains("test"));
        });
    }

    @Test(priority = 2)
    public void selectBetween_Test() {
        String sql = "SELECT * FROM user WHERE age BETWEEN 20 AND 30";
        print(get(TableHelper.class).select(sql));
        select(sql).forEach(row -> {
            Assert.assertTrue(Integer.parseInt(row.get("age")) >= 20 && Integer.parseInt(row.get("age")) <= 30);
        });
    }

    @Test(priority = 3)
    public void update_Test() {
        print(select("SELECT * FROM user WHERE first_name is null"));
        update("UPDATE user SET first_name=null WHERE first_name='User_2'");
        print(select("SELECT * FROM user WHERE first_name is null"));
    }

    @Test(priority = 4)
    public void insert_Test() {
        print(select("SELECT * FROM user WHERE first_name is null"));
        insert("INSERT INTO user (id, last_name) VALUES (100, 'QA16onl')");
        print(select("SELECT * FROM user WHERE first_name is null"));
    }

    @Test(priority = 5)
    public void delete_Test() {
        print(select("SELECT * FROM user WHERE first_name is null"));
        delete("DELETE FROM user WHERE last_name='QA16onl'");
        print(select("SELECT * FROM user WHERE first_name is null"));
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

    @SneakyThrows
    public List<Map<String, String>> select(String sql) {
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        List<Map<String, String>> data = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, String> row = new HashMap<>();
            for (int index = 1; index <= resultSetMetaData.getColumnCount(); index++) {
                row.put(resultSetMetaData.getColumnName(index), resultSet.getString(index));
            }
            data.add(row);
        }
        return data;
    }

    private void print(List<Map<String, String>> data) {
        System.out.println("===================================================");
        data.forEach(System.out::println);
        System.out.println("===================================================");
    }
}
