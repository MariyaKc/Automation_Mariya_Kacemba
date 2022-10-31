package lesson19.entity;

import lombok.Data;

import java.util.List;
/**Представление JSON в качестве стандартного джава entity */
@Data
public class Person {
    Integer id;
    String name;
    Integer age;
    Boolean work;
    String country;
    String sex;
    List<String> cars;
    List<Children> children;
    Wife wife;
}
