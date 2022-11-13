package sql;

import lombok.SneakyThrows;

public class UpdateHelper extends DBConnector {

    private String update;
    private String set;
    private String where;

    public static UpdateHelper getUpdate() {
        return new UpdateHelper();
    }

    public UpdateHelper update(String update) {
        this.update = update;
        return this;
    }

    public UpdateHelper set(String set) {
        this.set = set;
        return this;
    }

    public UpdateHelper where(String where) {
        this.where = where;
        return this;
    }

    @SneakyThrows
    public UpdateHelper execute() {
        String setWhere = where == null ? "" : " WHERE " + where;
        getStatement().executeUpdate("UPDATE " + update + " SET " + set + setWhere);
        return this;
    }
}
