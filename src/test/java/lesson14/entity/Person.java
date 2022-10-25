package lesson14.entity;

import lombok.*;
/**Создание сущности с библиотекой lombok */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Person {
    String firstName;
    String lastName;
    String address;
    Integer age;
    String sex;
    String id;
}

