package lesson23;

import lesson19.entity.Person;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lesson23 {

    List<String> list = new ArrayList<>();
    Map<String, String> map = new HashMap<>();

    @BeforeTest
    public void preconditions() {
        list.add("Test 2");
        list.add("Test 1");
        list.add("Test 3");
        list.add("Test 3");
        list.add("Test 5");
        list.add("Test 4");

        map.put("name", "Test");
        map.put("age", "14");
        map.put("city", "Test town");
    }

    @Test
    public void createStream_Test() {
        // map.entrySet().stream()... - вызов стрима для мапы
        IntStream.of(1, 2, 3, 4, 5, 6); //стрим для примитивных типов
        Stream.of("Test 1", "Test 2", "Test 3"); // вызов стрима без коллекции

        String[] array = {"Test 1", "Test 2", "Test 3"};
        Arrays.asList(array).stream();//стрим от массива 1- способ
        Arrays.stream(array);//стрим от массива 2- способ
    }

    @Test
    public void streamMethods_Test() {
        Stream<String> stream = list.stream()
                .filter(data -> data.contains("Test")) //фильтрация по условию
                .map(data -> data.concat("@")) //map выполняет действие над каждым элементом(используется, чтобы перегнать из одного формата данных в другой) concat - добавляет
                .sorted()  //сортировка, по умолчанию по возрастанию
                .distinct() //убирает дубликат
                .limit(3) // количество значений, которое должно содержатся в коллекции
                .peek(System.out::println) //промежуточный метод, чтобы вывести коллекцию в текущий момент
                .skip(1); //пропустить указанное количество элементов

        //System.out.println("findFirst result :: " + stream.findFirst().get()); // вернет первый элемент соответсвующий условию стрима
        //System.out.println("findAny result :: " + stream.findAny().get()); // найти любой рандомный элемент соответсвующий условию стрима
        //System.out.println(stream.collect(Collectors.toList())); // перевести из потока в лист
        //System.out.println(stream.count()); // количество элементов в стриме
        //stream.forEach(System.out::println); // для перебора элементов, терминальный
        Assert.assertTrue(list.stream().anyMatch(data -> data.equals("Test 1"))); //хотя бы одна строка содержит
        Assert.assertTrue(list.stream().allMatch(data -> data.contains(" "))); // все строки содержат " "
        Assert.assertTrue(list.stream().noneMatch(data -> data.contains("User"))); //ни одна строка не содержит
        System.out.println(list.stream().max(String::compareTo).get()); //максимальное значение в стриме
        System.out.println(list.stream().min(String::compareTo).get()); //минимальное значение в стриме
    }

    @Test
    public void streamObject_Test() {
        List<Person> personList = new ArrayList<>() {{
            add(new Person() {{
                setName("User 20");
                setAge(20);
            }});
            add(new Person() {{
                setName("User 30");
                setAge(30);
            }});
            add(new Person() {{
                setName("User 40");
                setAge(40);
            }});
            add(new Person() {{
                setName("User 50");
                setAge(50);
            }});
            add(new Person() {{
                setName("User 30");
                setAge(30);
            }});
            add(new Person() {{
                setName("User 40");
                setAge(40);
            }});
        }};
        System.out.println(personList.stream().mapToInt(Person::getAge).sum()); //сумма всех age

        Person p = new Person(1, "Test", 30, true, "Test", "Test", new ArrayList<>() {{ //создание объекта через конструктор
            add("audi");
        }}, null, null);
        p.setCars(new ArrayList<>() {{
            addAll(p.getCars());
            add("bmw"); //переменная audi перезапишется
        }});
        System.out.println(p);
    }
}
