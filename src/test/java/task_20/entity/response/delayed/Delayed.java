package task_20.entity.response.delayed;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Delayed {
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public ArrayList<Datum> data;
    public Support support;
}
