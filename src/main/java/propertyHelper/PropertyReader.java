package propertyHelper;

import lombok.extern.log4j.Log4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j
public class PropertyReader {
    private static Properties properties;

    public PropertyReader(String propertyName) { // конструктор, который отвечает за работу проперти с файловой системой
        properties = new Properties();
        if (propertyName == null || propertyName.isEmpty()) {
            log.error("Please check your -Dconfig value.");
        }

        try {
            FileInputStream inputStream = new FileInputStream("src/main/resources/" + propertyName + ".properties");//забираем файл из файловой системы
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
