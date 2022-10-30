package lesson20;

import lesson20.entity.request.UserRequest;
import lesson20.entity.response.UserResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class Lesson20 {

    @BeforeTest
    public void precondition() {
        baseURI = "https://reqres.in/api";
    }

    @Test // тест на гет
    public void get_test() {
        Integer id = 2;
        Response response = given().get("/users?id=" + id); //передаем uri+id и записываем в переменную
        response.then().assertThat().statusCode(200);//проверяем что статус код 200 - 1 способ
        Assert.assertEquals(response.then().extract().response().jsonPath().getInt("data.id"), id);//(проверка, что внутри тела сообщения существует id и он равен 2) //  extract - получить jsonPath().getInt путь к переменной в типе int
        Assert.assertEquals(response.then().extract().response().statusCode(), 200);//2-способ
    }

    @Test //тест на пост
    public void post_test1() {
        String body = "{\n" +                     //так лучше не хранить
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        Response response = given().header("Content-Type", "application/json").body(body).post("/users"); //для запроса с json обязательно указывать "Content-Type" , post - куда посылаем, body - что посылаем
        response.then().assertThat().statusCode(201);
        Assert.assertEquals(response.then().extract().response().jsonPath().getString("name"), "morpheus");
        Assert.assertEquals(response.then().extract().response().jsonPath().getString("job"), "leader");
    }

    @Test /** 2 вариант post запроса из файла */
    public void post_test2() {
        Response response = given().header("Content-Type", "application/json").body(getJsonData("lesson20")).post("/users"); //лучше забирать тело запроса из файла
        response.then().assertThat().statusCode(201);
        Assert.assertEquals(response.then().extract().response().jsonPath().getString("name"), "morpheus");
        Assert.assertEquals(response.then().extract().response().jsonPath().getString("job"), "leader");
    }

    @Test /** 3 вариант post запроса POJO Object */
    public void post_test3() {
        UserRequest user = new UserRequest() {{ //создает объект user и передает значения
            setName("morpheus");
            setJob("leader");
        }};
        Response response = given().header("Content-Type", "application/json").body(user).post("/users"); //передаем user
        response.then().assertThat().statusCode(201);
        //->получили ответ, перегнали в POJO Object, поместили в response.UserResponse
        UserResponse userResponse = response.as(UserResponse.class); //as(UserResponse.class) - получить респонс в качестве объекта UserResponse
        Assert.assertEquals(userResponse.getName(), "morpheus");
        Assert.assertEquals(userResponse.getJob(), "leader");
    }

    @Test /** валидация JSON схемы */
    public void get_userList_Test() {
        Response response = given().get("/users?page=2");
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body(matchesJsonSchema(getJsonData("lesson20_schema")));// matchesJsonSchema отвечает за валидацию схемы
    }


    public String getJsonData(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get("files/" + filename + ".json"))); //передаем название файла
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
