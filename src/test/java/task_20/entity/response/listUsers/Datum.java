package task_20.entity.response.listUsers;

import lombok.Data;

@Data
public class Datum {
    public int id;
    public String email;
    public String first_name;
    public String last_name;
    public String avatar;
}
