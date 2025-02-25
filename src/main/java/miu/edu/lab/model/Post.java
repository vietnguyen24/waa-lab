package miu.edu.lab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Post {
    private Long id;
    private String title;
    private String content;
    private String author;
}
