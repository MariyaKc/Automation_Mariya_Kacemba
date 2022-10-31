package lesson20.entity.response;
import lombok.Data;

@Data
public class UserResponse {
    public String name;
    public String job;
    public String id;
    public String createdAt;
}
