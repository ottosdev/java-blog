package br.com.blog.model;

import br.com.blog.dto.PostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private String content;
    private String author;
    private String category;

    public Post(PostDTO dto) {
        this.title = dto.title();
        this.content = dto.content();
        this.author = dto.author();
        this.category = dto.category();
    }

    public Post(String id, PostDTO dto) {
        this.id = id;
        this.title = dto.title();
        this.content = dto.content();
        this.author = dto.author();
        this.category = dto.category();
    }
}
