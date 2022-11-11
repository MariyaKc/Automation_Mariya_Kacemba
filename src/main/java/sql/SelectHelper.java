package sql;


import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectHelper extends DBConnector {
    private String select;
    private String from;
    private String where;


    public static SelectHelper getSelect() {
        return new SelectHelper();
    }

    public SelectHelper select(String select) {
        this.select = select;
        return this;
    }

    public SelectHelper from(String from) {
        this.from = from;
        return this;
    }

    public SelectHelper where(String where) {
        this.where = where;
        return this;
    }

    @SneakyThrows
    public ResultSet execute() { // что нужно получить мз строки
        String setWhere = where == null ? "" : " WHERE " + where;
        return getStatement().executeQuery("SELECT " + select + " FROM " + from +"WHERE" +where);
    }

    @SneakyThrows
    public List<Map<String, String>> getData() {
        ResultSet resultSet = execute(); // позволяет выполнить запрос и записать результат в ResultSet, который потом можно переформатировать в необходимый формат
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