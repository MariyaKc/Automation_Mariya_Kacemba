package task_21;

import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

import java.util.List;
import java.util.Map;

import static sql.DeleteHelper.getDelete;
import static sql.InsertHelper.getInsert;
import static sql.SelectHelper.getSelect;
import static sql.UpdateHelper.getUpdate;


public class
SQL_Test {

    Connection connection;
    Statement statement;

    @SneakyThrows
    @BeforeTest
    public void precondition() {
        connection = DriverManager.getConnection("jdbc:mysql://db4free.net/testqa07?user=testqa07&password=testqa07");
        statement = connection.createStatement();
        print(getSelect().select("*").from("Pets").getData());

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
        getInsert().insert(sql);
       sql = "INSERT INTO Pets VALUES (3, 'Claws', 'Benny', 'dog', 7)";
         getInsert().insert(sql);
      //.......................
    }


    /** Реализовать теста (2 - 3) которые выполняют запрос на добавление данных в таблицу */
    @Test(priority = 1)
    public void insert1_Test() {
        print(getSelect().select("*").from("Pets").where("owner is null").getData());
        getInsert().insert("Pets").into("PetsId, name, species").values("20,'Micky','hamster'").execute();
        print(getSelect().select("*").from("Pets").where("owner is null").getData());
    }

    @Test(priority = 2)
    public void insert2_Test() {
        getInsert().insert("Pets").values("21, 'Fluffy', 'Alex', 'cat', 7").execute();
    }

    @Test(priority = 3)
    public void insert3_Test() {
        print(getSelect().select("*").from("Pets").where("name is null").getData());
        getInsert().insert("Pets").into("PetsID").values("33");
        print(getSelect().select("*").from("Pets").where("name is null").getData());
    }

    /** Реализовать тесты (2 - 3) которые выполняют запрос на извлечения данных из таблицы */

    @Test(priority = 4)
    public void selectLike_Test() {
        String sql = "SELECT * FROM Pets WHERE name LIKE '%y'";
        print(getSelect().select(sql).getData());
        getSelect().select(sql).getData().forEach(row -> {
            Assert.assertTrue(row.get("name").contains("y"));
        });
    }

    @Test(priority = 5)
    public void selectBetween_Test() {
        String sql = "SELECT * FROM Pets WHERE age BETWEEN 2 AND 10";
        print(getSelect().select(sql).getData());
        getSelect().select(sql).getData().forEach(row -> {
            Assert.assertTrue(Integer.parseInt(row.get("age")) >= 2 && Integer.parseInt(row.get("age")) <= 10);
        });
    }

    @Test(priority = 6)
    public void selectIn_Test() {
        String sql = "SELECT * FROM Pets WHERE species IN('hamster','bird')";
        print(getSelect().select(sql).getData());
        getSelect().select(sql).getData().forEach(row -> {
            Assert.assertTrue(row.get("species").contains("hamster") | row.get("species").contains("bird"));
        });
    }

    /** Реализовать тесты (2 - 3) которые выполняют запрос на редактирование данных таблицы */
    @Test(priority = 7)
    public void update1_Test() {
        print(getSelect().select("SELECT * FROM Pets WHERE age is null").getData());
        getUpdate().update("Pets").set("age=null WHERE age=2");
        print(getSelect().select("SELECT * FROM Pets WHERE age is null").getData());
    }

    @Test(priority = 8)
    public void update2_Test() {
        print(getSelect().select("SELECT * FROM Pets WHERE owner LIKE '%User%'").getData());
        getUpdate().update("Pets").set("owner='Admin' WHERE owner LIKE '%User%'");
        print(getSelect().select("SELECT * FROM Pets WHERE owner LIKE '%User%'").getData());
    }

    @Test(priority = 9)
    public void update3_Test() {
        print(getSelect().select("SELECT * FROM Pets WHERE species='bird'").getData());
        getUpdate().update("Pets").set("species=null WHERE species='bird'");
        print(getSelect().select("SELECT * FROM Pets WHERE species is null").getData());
    }


    /** Реализовать тесты (2 - 3) которые выполняют запрос на удаление данных таблицы  */
    @Test(priority = 10)
    public void delete1_Test() {
        print(getSelect().select("SELECT * FROM Pets WHERE name is null").getData());
        getDelete().deleteFrom("Pets").where("name='Chirpy'");
        print(getSelect().select("SELECT * FROM Pets WHERE name is null").getData());
    }

    @Test(priority = 11)
    public void delete2_Test() {
        print(getSelect().select("SELECT * FROM Pets WHERE owner='Admin'").getData());
        getDelete().deleteFrom("Pets").where("PetsID=12");
        print(getSelect().select("SELECT * FROM Pets WHERE owner='Admin'").getData());
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

   @SneakyThrows
    @AfterTest
    public void postcondition(){
        if(connection!=null)
            connection.close();
    }

}
