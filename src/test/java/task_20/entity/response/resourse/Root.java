package task_20.entity.response.resourse;

import lombok.Data;

import java.util.List;

@Data
public class Root {
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public List<Datum> data;
    public Support support;
}
