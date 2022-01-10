package podamEx;

import java.util.List;

import lombok.Data;

@Data
public class Response {
    private List<String> photoUrls;
    private String name;
    private int id;
    private Category category;
    private List<TagsItem> tags;
    private String status;
}