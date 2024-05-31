package br.com.blog.model;

import br.com.blog.dto.post.PostRequestDTO;
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

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private String author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Post(PostRequestDTO dto) {
        this.title = dto.title();
        this.content = dto.content();
        this.author = dto.author();

    }

    public Post(String id, PostRequestDTO dto) {
        this.id = id;
        this.title = dto.title();
        this.content = dto.content();
        this.author = dto.author();
    }
}
