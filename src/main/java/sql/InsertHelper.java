package sql;

import lombok.SneakyThrows;

import java.sql.ResultSet;

public class InsertHelper extends DBConnector {
    private String insert;
    private String into;
    private String values;


    public static InsertHelper getInsert() {
        return new InsertHelper();
    }

    public InsertHelper insert(String insert) {
        this.insert = insert;
        return this;
    }

    public InsertHelper into(String into) {
        this.into = into;
        return this;
    }

    public InsertHelper values(String values) {
        this.values = values;
        return this;
    }

    @SneakyThrows
    public ResultSet execute() {
        return getStatement().executeQuery("INSERT INTO " + insert + "( " + into + ") " + " VALUES (" + values + ")");
    }
}
