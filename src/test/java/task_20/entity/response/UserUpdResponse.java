package task_20.entity.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserUpdResponse {
    public String name;
    public String job;
    public String updatedAt;
}
