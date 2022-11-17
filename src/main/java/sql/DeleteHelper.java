package sql;

import lombok.SneakyThrows;

public class DeleteHelper extends DBConnector {

    private String from;
    private String where;

    public static DeleteHelper getDelete() {
        return new DeleteHelper();
    }

    public DeleteHelper deleteFrom(String from) {
        this.from = from;
        return this;
    }

    public DeleteHelper where(String where) {
        this.where = where;
        return this;
    }

    @SneakyThrows
    public DeleteHelper execute() {
        getStatement().executeUpdate("DELETE FROM " + from + " WHERE " + where);
        return this;
    }
}
