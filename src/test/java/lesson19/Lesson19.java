package lesson19;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lesson19.entity.Person;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Lesson19 {

    // @Test
    public void jsonTest() {
        Gson gson = new Gson();
        String data = getJsonData();
        JsonObject jsonObject = gson.fromJson(data, JsonObject.class);//преобразование объекта строкового типа в Json
        System.out.println(gson.toJson(jsonObject)); //
        System.out.println(jsonObject.get("id").getAsInt()); //получение id в типе Int(без getAsInt берет как Json)
        System.out.println(jsonObject.get("name").getAsString());
        System.out.println(gson.fromJson(jsonObject.get("cars"), List.class)); // преобразование массива в лист
        System.out.println(gson.fromJson(jsonObject, Map.class)); //преобразование в мапу всего Json
        System.out.println(jsonObject.get("wife").getAsJsonObject().get("name")); //получение у jsonObject и у него поле
        System.out.println(jsonObject.get("children").getAsJsonArray().get(0).getAsJsonObject().get("name")); //получить элемент массива по индексу
        System.out.println(jsonObject.get("children").getAsJsonArray().get(1).getAsJsonObject().get("name"));
    }

    @Test //описание Json с точки зрения java(pojo object)
    public void jsonObjectTest() {
        Gson gson = new Gson();
        String data = getJsonData();
        Person person = gson.fromJson(data, Person.class);//преобразование объекта строкового типа в Person
        person.getCars().forEach(System.out::println);//получение Cars у объекта person
        person.getChildren().forEach(children -> System.out.print(" " + children.getName()));
    }

    //метод который позволяет получить файл в качестве строки
    public String getJsonData() {
        try {
            return new String(Files.readAllBytes(Paths.get("files/person.json")));//конвертация файла в байты и из байтов в строку
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
