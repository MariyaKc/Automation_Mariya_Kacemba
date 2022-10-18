package lessson14.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**Builder pattern from Lombok*/

@Getter
@ToString
@Builder(builderClassName = "HomeBuilder", setterPrefix = "with", buildMethodName = "create") //добавление префикса и изменение buildMethodName
public class Home {
    String doors;
    String color;
    String address;
    Integer winCount;
    Boolean old;

    public static class HomeBuilder {
        public HomeBuilder() { //по умолчанию модификатор доступа protected(не доступен в других пакетах).Чтобы сделать public, создаем пустой конструктор во внутр. классе
        }
    }
}
