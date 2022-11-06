package sql;


import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectHelper {

    Statement statement;

    public SelectHelper(Statement statement) {
        this.statement = statement;
    }

    @SneakyThrows
    public List<Map<String, String>> select(String sql) {
        ResultSet resultSet = statement.executeQuery(sql); // позволяет выполнить запрос и записать результат в ResultSet, который потом можно переформатировать в необходимый формат
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData(); //чтобы переиспользовать данные из таблицы, переводим в ResultSetMetaData
        List<Map<String, String>> data = new ArrayList<>(); //строка(название колонки = значение,...)
        while (resultSet.next()) { //-заходит на строку; значения будут перебираться до тех пор, пока существуют записи. next() означает перебор, можно обращаться к новому значению по индексу
            Map<String, String> row = new HashMap<>();
            for (int index = 1; index <= resultSetMetaData.getColumnCount(); index++) { //перебор значений в колонке;индексация с первого значения
                row.put(resultSetMetaData.getColumnName(index), resultSet.getString(index)); //забираем данные из колонки в текущей строке и помещаем в мапу название колонки и данные из нее (название колонки = значение)
            }
            data.add(row);//записывает мапу строк в лист
        }
        return data;
    }
}