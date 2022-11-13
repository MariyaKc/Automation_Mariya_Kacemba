package task_20;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import task_20.entity.request.UserUpdRequest;
import task_20.entity.response.UserUpdResponse;
import task_20.entity.response.delayed.Delayed;
import task_20.entity.response.listUsers.Users;
import task_20.entity.response.resourse.Root;
import task_20.entity.response.singleResourse.SingleRoot;
import task_20.entity.response.singleUser.SingleUser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

/**
 * Для приложения https://reqres.in/ автоматизировать REST API-запросы (10 - 15 тестов) с использованием следующих http методов:
 * GET
 * POST
 * PUT
 * DELETE
 * PATCH
 * HEAD
 */

public class RestAssured_Test {

    @BeforeTest
    public void precondition() {
        baseURI = "https://reqres.in/api";
    }

    @Test(priority = 1, description = "GET USERS LIST test with POJO Object")
    public void getListUsers_test() {
        Response response = given().get("users?page=2");
        response.then().assertThat().statusCode(200).body(Matchers.notNullValue());

        Users users = response.as(Users.class);

        Assert.assertEquals(users.getPage(), 2);
        Assert.assertEquals(users.getPer_page(), 6);
        Assert.assertEquals(users.getTotal(), 12);
        Assert.assertEquals(users.getTotal_pages(), 2);

        users.getData().forEach(element -> {
            Assert.assertTrue(element.id > 0);
            Assert.assertNotNull(element.email);
            Assert.assertNotNull(element.first_name);
            Assert.assertNotNull(element.last_name);
            Assert.assertNotNull(element.avatar);
        });

        Assert.assertTrue(users.getSupport().url.toString().contains("#support-heading"));
        Assert.assertTrue(users.getSupport().text.toString().contains("To keep ReqRes free"));
    }

    @Test(priority = 2, description = "GET SINGLE USER test")
    public void getSingleUser_test() {
        Integer id = 2;
        Response response = given().get("/users/" + id);
        response.then().assertThat().statusCode(200).body(Matchers.notNullValue());

        SingleUser singleUser = response.as(SingleUser.class);

        Assert.assertEquals(singleUser.getData().toString(), "Datum(id=2, email=janet.weaver@reqres.in, first_name=Janet, last_name=Weaver, avatar=https://reqres.in/img/faces/2-image.jpg)");
        Assert.assertTrue(singleUser.getSupport().url.toString().contains("#support-heading"));
        Assert.assertTrue(singleUser.getSupport().text.toString().contains("To keep ReqRes free"));
    }

    @Test(priority = 3, description = "GET SINGLE USER NOT FOUND test")
    public void getSingleUserNotFound_test() {
        Integer id = 23;
        Response response = given().get("/users/" + id);
        response.then().assertThat().statusCode(404).body(Matchers.anything()); //проверка статус кода и что тело ответа пустое - 1 способ
        Assert.assertEquals(response.then().extract().response().statusCode(), 404); //2-способ
        Assert.assertEquals(response.getBody().asString(), "{}");
    }

    @Test(priority = 4, description = "GET LIST <RESOURCE> test with POJO Object")
    public void getListResource_test() {
        Response response = given().get("/unknown");
        response.then().assertThat().statusCode(200).body(Matchers.notNullValue());

        Root root = response.as(Root.class);

        Assert.assertEquals(root.getPage(), 1);
        Assert.assertEquals(root.getPer_page(), 6);
        Assert.assertEquals(root.getTotal(), 12);
        Assert.assertEquals(root.getTotal_pages(), 2);

        root.getData().forEach(element -> {
            Assert.assertTrue(element.id > 0);
            Assert.assertNotNull(element.name);
            Assert.assertTrue(element.year > 0);
            Assert.assertNotNull(element.pantone_value);
        });

        Assert.assertTrue(root.getSupport().url.toString().contains("#support-heading"));
        Assert.assertTrue(root.getSupport().text.toString().contains("To keep ReqRes free"));
        Assert.assertEquals(response.then().extract().response().statusCode(), 200);
    }

    @Test(priority = 5, description = "GET SINGLE <RESOURCE> test with POJO Object")
    public void getSingleResource_test() {
        Response response = given().get("/unknown/2");
        response.then().assertThat().statusCode(200).body(Matchers.notNullValue());

        SingleRoot singleRoot = response.as(SingleRoot.class);
        Assert.assertEquals(singleRoot.getData().toString(), "Datum(id=2, name=fuchsia rose, year=2001, color=#C74375, pantone_value=17-2031)");
        Assert.assertTrue(singleRoot.getSupport().url.toString().contains("#support-heading"));
        Assert.assertTrue(singleRoot.getSupport().text.toString().contains("To keep ReqRes free"));
        Assert.assertEquals(response.then().extract().response().statusCode(), 200);
    }

    @Test(priority = 6, description = "GET SINGLE <RESOURCE> NOT FOUND test")
    public void getSingleResourceNotFound_test() {
        Integer id = 23;
        Response response = given().get("/unknown/" + id);

        Assert.assertEquals(response.then().extract().response().statusCode(), 404);
        Assert.assertEquals(response.getBody().asString(), "{}");
    }

    /**
     * вариант - 1
     */
    @Test(priority = 7, description = "PUT UPDATE test with POJO Object")
    public void putUpdate_test1() {

        UserUpdRequest user = new UserUpdRequest() {{
            setName("morpheus");
            setJob("zion resident");
        }};

        Response response = given().header("Content-Type", "application/json").body(user).put("/users/2");
        response.then().assertThat().statusCode(200).body(Matchers.notNullValue());

        UserUpdResponse userUpdResponse = response.as(UserUpdResponse.class);
        Assert.assertEquals(userUpdResponse.getName(), "morpheus");
        Assert.assertEquals(userUpdResponse.getJob(), "zion resident");
        Assert.assertTrue(userUpdResponse.getUpdatedAt().contains(LocalDate.now().toString()));
    }

    /**
     * вариант - 2
     */
    @Test(priority = 8, description = "PUT UPDATE test from file")
    public void putUpdate_test2() {
        Response response = given().header("Content-Type", "application/json").body(getJsonData("update")).put("/users/2");
        response.then().assertThat().statusCode(200).body(Matchers.notNullValue());
        Assert.assertEquals(response.then().extract().response().jsonPath().getString("name"), "morpheus");
        Assert.assertEquals(response.then().extract().response().jsonPath().getString("job"), "zion resident");
        Assert.assertTrue(response.then().extract().jsonPath().get("updatedAt").toString().contains(LocalDate.now().toString()));
    }

    @Test(priority = 9, description = "PATH UPDATE test from file")
    public void patchUpdate_test() {
        Response response = given().header("Content-Type", "application/json").body(getJsonData("update")).patch("/users/2");
        response.then().assertThat().statusCode(200).body(Matchers.notNullValue());
        Assert.assertEquals(response.then().extract().response().jsonPath().getString("name"), "morpheus");
        Assert.assertEquals(response.then().extract().response().jsonPath().getString("job"), "zion resident");
        Assert.assertTrue(response.then().extract().jsonPath().get("updatedAt").toString().contains(LocalDate.now().toString()));
    }

    @Test(priority = 10, description = "DELETE test")
    public void delete_test() {
        Response response = given().header("Content-Type", "application/json").delete("/users/2");
        response.then().assertThat().statusCode(204);
        Assert.assertTrue(response.body().asString().isEmpty());
    }

    @Test(priority = 11, description = "POST REGISTER - SUCCESSFUL test from file")
    public void postRegisterSuccessful_test() {
        Response response = given().header("Content-Type", "application/json").body(getJsonData("registerSuccessful")).post("/register");
        response.then().assertThat().statusCode(200).body(Matchers.notNullValue());
        Assert.assertEquals(response.then().extract().response().jsonPath().getInt("id"), 4);
        Assert.assertEquals(response.then().extract().response().jsonPath().getString("token"), "QpwL5tke4Pnpja7X4");
    }

    @Test(priority = 12, description = "POST REGISTER - UNSUCCESSFUL test from file")
    public void postRegisterUnsuccessful_test() {
        Response response = given().header("Content-Type", "application/json").body(getJsonData("registerUnsuccessful")).post("/register");
        response.then().assertThat().statusCode(400).body(Matchers.notNullValue());
        Assert.assertEquals(response.then().extract().response().jsonPath().getString("error"), "Missing password");
    }

    @Test(priority = 13, description = "POST LOGIN - SUCCESSFUL test from file")
    public void postLoginSuccessful_test() {
        Response response = given().header("Content-Type", "application/json").body(getJsonData("loginSuccessful")).post("/login");
        response.then().assertThat().statusCode(200).body(Matchers.notNullValue());
        Assert.assertEquals(response.then().extract().response().jsonPath().getString("token"), "QpwL5tke4Pnpja7X4");
    }

    @Test(priority = 4, description = "POST LOGIN - UNSUCCESSFUL test from file")
    public void postLoginUnsuccessful_test() {
        Response response = given().header("Content-Type", "application/json").body(getJsonData("loginUnsuccessful")).post("/login");
        response.then().assertThat().statusCode(400).body(Matchers.notNullValue());
        Assert.assertEquals(response.then().extract().response().jsonPath().getString("error"), "Missing password");
    }

    @Test(priority = 15, description = "DELAYED RESPONSE test with POJO Object")
    public void getDelayedResponse_test() {

        Response response = given().get("/users?delay=3");
        response.then().assertThat().statusCode(200).body(Matchers.notNullValue());

        Delayed delayed = response.as(Delayed.class);

        Assert.assertEquals(delayed.getPage(), 1);
        Assert.assertEquals(delayed.getPer_page(), 6);
        Assert.assertEquals(delayed.getTotal(), 12);
        Assert.assertEquals(delayed.getTotal_pages(), 2);

        delayed.getData().forEach(element -> {
            Assert.assertTrue(element.id > 0);
            Assert.assertNotNull(element.email.contains("@reqres.in"));
            Assert.assertNotNull(element.first_name);
            Assert.assertNotNull(element.last_name);
            Assert.assertNotNull(element.avatar.contains("reqres.in"));
        });

        Assert.assertTrue(delayed.getSupport().url.toString().contains("#support-heading"));
        Assert.assertTrue(delayed.getSupport().text.toString().contains("To keep ReqRes free"));
    }


    public String getJsonData(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get("files/requests/" + filename + ".json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
