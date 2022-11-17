package sql;


import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j
public class TableHelper extends DBConnector {
    Statement statement;

    @SneakyThrows
    public TableHelper createTable(String tableName) {
        log.debug("Create table");
        statement.executeUpdate("CREATE TABLE " + tableName + " (id int, " + "firstname varchar(255), " + "lastname varchar(255), " + "age int, " + "city varchar(255))");
        return this;
    }

    @SneakyThrows
    public Integer insert(String sql) {
        return statement.executeUpdate(sql);
    }

    @SneakyThrows
    public List<Map<String, String>> select(String sql) {
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        List<Map<String, String>> data = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, String> row = new HashMap<>();
            for (int index = 1; index <= resultSetMetaData.getColumnCount(); index++) {
                row.put(resultSetMetaData.getColumnName(index), resultSet.getString(index));
            }
            data.add(row);
        }
        return data;
    }
}
