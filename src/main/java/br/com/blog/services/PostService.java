package br.com.blog.services;

import br.com.blog.dto.PostDTO;
import br.com.blog.model.Post;
import br.com.blog.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Post save(PostDTO dto) {
        Post post = new Post(dto);
        return  postRepository.save(post);
    }

    public List<Post> listPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(String id) {
        return postRepository.findById(id);
    }

    @Transactional
    public Post update(String id, PostDTO dto) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isEmpty()) {
            throw new RuntimeException("Post not found with id: " + id);
        }
        Post post = new Post(id, dto);
        return postRepository.save(post);
    }

    @Transactional
    public void delete(String id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found with id: " + id);
        }
        postRepository.deleteById(id);
    }
}
