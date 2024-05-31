package br.com.blog.controller;

import br.com.blog.dto.post.PostDTO;
import br.com.blog.dto.post.PostResponseDTO;
import br.com.blog.exceptions.CustomException;
import br.com.blog.model.Post;
import br.com.blog.services.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/post")
public class PostController {

    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> save(@RequestBody @Valid PostDTO dto) {
        Post post = postService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
        List<PostResponseDTO> posts = postService.listPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getPost(@PathVariable String id) {
        Optional<Post> getPost = postService.findById(id);
        return ResponseEntity.ok(getPost.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody PostDTO dto) {
        Post post = postService.update(id, dto);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable String id) {
        postService.delete(id);
        return ResponseEntity.ok().build();
    }
}
