package task_20;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

/**
 * (*) JsonSchema
 * К 3 -5 ответам сформировать схемы которые будут валидировать :
 * тип элемента
 * обязательные поля объекта
 * данные которые может содержать элемент
 */

public class JsonSchema_test {

    @BeforeTest
    public void precondition() {
        baseURI = "https://reqres.in/api";
    }

    @Test(priority = 1, description = "GET SINGLE <RESOURCE> test JSON Schema")
    public void getSingleResource_test() {
        Response response = given().get("/unknown/2");
        response.then().assertThat().statusCode(200).body(Matchers.notNullValue());
        response.then().assertThat().body(matchesJsonSchema(getJsonData("jsonschema/singleResource_schema")));
    }

    @Test(priority = 2, description = "GET LIST <RESOURCE> test JSON Schema")
    public void getListResource_test() {
        Response response = given().get("/unknown");
        response.then().assertThat().statusCode(200).body(Matchers.notNullValue());
        response.then().assertThat().body(matchesJsonSchema(getJsonData("jsonschema/listResource_schema")));
    }

    @Test(priority = 3, description = "DELAYED RESPONSE test JSON Schema")
    public void getDelayedResponse_test() {
        Response response = given().get("/users?delay=3");
        response.then().assertThat().statusCode(200).body(Matchers.notNullValue());
        response.then().assertThat().body(matchesJsonSchema(getJsonData("jsonschema/delayedResponse_schema")));
    }

    @Test(priority = 4, description = "PUT UPDATE  test JSON Schema")
    public void putUpdate_test() {
        Response response = given().header("Content-Type", "application/json").body(getJsonData("requests/update")).put("/users/2");
        response.then().assertThat().statusCode(200).body(Matchers.notNullValue());
        response.then().assertThat().body(matchesJsonSchema(getJsonData("jsonschema/update_schema")));
    }

    @Test(priority = 5, description = "POST CREATE test JSON Schema")
    public void postCreate_test() {
        Response response = given().header("Content-Type", "application/json").body(getJsonData("lesson20")).post("/users");
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body(matchesJsonSchema(getJsonData("jsonschema/create_schema")));
    }


    public String getJsonData(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get("files/" + filename + ".json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
