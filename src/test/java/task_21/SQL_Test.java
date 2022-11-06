package task_21;

import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sql.SelectHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static jdk.nashorn.internal.objects.Global.print;


public class SQL_Test {

    Connection connection;
    Statement statement;
    SelectHelper selectHelper;

    @SneakyThrows
    @BeforeTest
    public void precondition() {
        connection = DriverManager.getConnection("jdbc:mysql://db4free.net/testqa07?user=testqa07&password=testqa07");
        statement = connection.createStatement();
        selectHelper = new SelectHelper(statement);
        print(selectHelper.select("SELECT * FROM Pets"));
    }

    /** Создать таблицу */
    @Test(enabled = false)
    public void createTable_Test() {
        createTable( "CREATE TABLE Pets " +
                "(PetsID INTEGER not NULL, " +
                " name VARCHAR(255), " +
                " owner VARCHAR(255), " +
                " species VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( PetsID ))");

        String sql = "INSERT INTO Pets VALUES (1, 'Fluffy', 'Gwen', 'cat', 2)";
        insert(sql);
       sql = "INSERT INTO Pets VALUES (3, 'Claws', 'Benny', 'dog', 7)";
         insert(sql);
      //.......................
    }


    /** Реализовать теста (2 - 3) которые выполняют запрос на добавление данных в таблицу */
    @Test(priority = 1)
    public void insert1_Test() {
        print(selectHelper.select("SELECT * FROM Pets WHERE owner is null"));
        insert("INSERT INTO Pets (PetsId, name, species) VALUES (20, 'Micky','hamster')");
        print(selectHelper.select("SELECT * FROM Pets WHERE owner is null"));
    }

    @Test(priority = 2)
    public void insert2_Test() {
        insert("INSERT INTO Pets VALUES (21, 'Fluffy', 'Alex', 'cat', 7)");
    }

    @Test(priority = 3)
    public void insert3_Test() {
        print(selectHelper.select("SELECT * FROM Pets WHERE name is null"));
        insert("INSERT INTO Pets (PetsID) VALUES (33)");
        print(selectHelper.select("SELECT * FROM Pets WHERE name is null"));
    }

    /** Реализовать тесты (2 - 3) которые выполняют запрос на извлечения данных из таблицы */

    @Test(priority = 4)
    public void selectLike_Test() {
        String sql = "SELECT * FROM Pets WHERE name LIKE '%y'";
        print(selectHelper.select(sql));
        selectHelper.select(sql).forEach(row -> {
            Assert.assertTrue(row.get("name").contains("y"));
        });
    }

    @Test(priority = 5)
    public void selectBetween_Test() {
        String sql = "SELECT * FROM Pets WHERE age BETWEEN 2 AND 10";
        print(selectHelper.select(sql));
        selectHelper.select(sql).forEach(row -> {
            Assert.assertTrue(Integer.parseInt(row.get("age")) >= 2 && Integer.parseInt(row.get("age")) <= 10);
        });
    }

    @Test(priority = 6)
    public void selectIn_Test() {
        String sql = "SELECT * FROM Pets WHERE species IN('hamster','bird')";
        print(selectHelper.select(sql));
        selectHelper.select(sql).forEach(row -> {
            Assert.assertTrue(row.get("species").contains("hamster") | row.get("species").contains("bird"));
        });
    }

    /** Реализовать тесты (2 - 3) которые выполняют запрос на редактирование данных таблицы */
    @Test(priority = 7)
    public void update1_Test() {
        print(selectHelper.select("SELECT * FROM Pets WHERE age is null"));
        update("UPDATE Pets SET age=null WHERE age=2");
        print(selectHelper.select("SELECT * FROM Pets WHERE age is null"));
    }

    @Test(priority = 8)
    public void update2_Test() {
        print(selectHelper.select("SELECT * FROM Pets WHERE owner LIKE '%User%'"));
        update("UPDATE Pets SET owner='Admin' WHERE owner LIKE '%User%'");
        print(selectHelper.select("SELECT * FROM Pets WHERE owner='Admin'"));
    }

    @Test(priority = 9)
    public void update3_Test() {
        print(selectHelper.select("SELECT * FROM Pets WHERE species='bird'"));
        update("UPDATE Pets SET species=null WHERE species='bird'");
        print(selectHelper.select("SELECT * FROM Pets WHERE species is null"));
    }


    /** Реализовать тесты (2 - 3) которые выполняют запрос на удаление данных таблицы  */
    @Test(priority = 10)
    public void delete1_Test() {
        print(selectHelper.select("SELECT * FROM Pets WHERE species is null"));
        delete("DELETE FROM Pets WHERE name='Chirpy'");
        print(selectHelper.select("SELECT * FROM Pets WHERE name is null"));
    }

    @Test(priority = 11)
    public void delete2_Test() {
        print(selectHelper.select("SELECT * FROM Pets WHERE owner='Admin'"));
        delete("DELETE FROM Pets WHERE PetsID=12");
        print(selectHelper.select("SELECT * FROM Pets WHERE owner='Admin'"));
    }

    @SneakyThrows
    private Integer createTable(String sql) {
        return statement.executeUpdate(sql);
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


    private void print(List<Map<String, String>> data) {
        System.out.println("===================================================");
        data.forEach(System.out::println);
        System.out.println("===================================================");
    }

   @SneakyThrows
    @AfterTest
    public void postcondition(){
        if(connection!=null)
            connection.close();
    }

}
