package br.com.blog.services;

import br.com.blog.dto.PostDTO;
import br.com.blog.model.Post;
import br.com.blog.repository.PostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post salvar(PostDTO dto) {
        Post post = new Post(dto);
        return  postRepository.save(post);
    }

    public List<Post> listarPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(String id) {
        return postRepository.findById(id);
    }

    public Post atualizar(String id, PostDTO dto) {
        Post post = new Post(id, dto);
        return postRepository.save(post);
    }

    public void deletar(String id) {
         postRepository.deleteById(id);
    }
}
