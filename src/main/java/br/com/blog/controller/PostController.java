package br.com.blog.controller;

import br.com.blog.dto.PostDTO;
import br.com.blog.model.Post;
import br.com.blog.services.PostService;
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
    public ResponseEntity<Post> save(@RequestBody PostDTO dto) {
        Post post = postService.save(dto);
        return ResponseEntity.ok(post);
    }

    @GetMapping
    public ResponseEntity<List<Post>> listPosts() {
        return ResponseEntity.ok(postService.listPosts());
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody PostDTO dto) {
        Optional<Post> getPost = postService.findById(id);

        if(getPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Post post = postService.update( id, dto);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable String id) {
        Optional<Post> getPost = postService.findById(id);

        if(getPost.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        postService.delete(id);
        return ResponseEntity.ok().build();
    }
}
