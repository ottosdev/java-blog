package br.com.blog.services;

import br.com.blog.dto.post.PostRequestDTO;
import br.com.blog.dto.post.PostResponseDTO;
import br.com.blog.exceptions.CustomException;
import br.com.blog.model.Category;
import br.com.blog.model.Post;
import br.com.blog.repository.CategoryRepository;
import br.com.blog.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public PostResponseDTO save(PostRequestDTO dto) {
        Category category = categoryRepository.findByName(dto.categoryName())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(dto.categoryName());
                    return categoryRepository.save(newCategory);
                });

        Post post = new Post(dto);
        post.setCategory(category);
        Post postsaved = postRepository.save(post);
        return getPostResponseDTO(postsaved);
    }



    public List<PostResponseDTO> listPosts() {
        return postRepository.findAll().stream()
                .map(PostService::getPostResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<PostResponseDTO> findById(String id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            throw new CustomException("Resource not found: " + id, HttpStatus.NOT_FOUND, "RESOURCE_NOT_FOUND");
        }
        Post foundPost = post.get();
        PostResponseDTO dto = getPostResponseDTO(foundPost);
        return Optional.of(dto);
    }

    @Transactional
    public PostResponseDTO update(String id, PostRequestDTO dto) {
        Optional<Post> existingPostOptional = postRepository.findById(id);
        if (existingPostOptional.isEmpty()) {
            throw new CustomException("Resource not found: " + id, HttpStatus.NOT_FOUND, "RESOURCE_NOT_FOUND");
        }

        Post existingPost = existingPostOptional.get();

        Optional<Category> category = categoryRepository.findByName(dto.categoryName());
        if (category.isEmpty()) {
            throw new CustomException("Resource not found: " + dto.categoryName(), HttpStatus.NOT_FOUND, "RESOURCE_NOT_FOUND");
        }

        existingPost.setTitle(dto.title());
        existingPost.setContent(dto.content());
        existingPost.setAuthor(dto.author());
        existingPost.setCategory(category.get());

        Post post = postRepository.save(existingPost);
        return getPostResponseDTO(post);
    }

    @Transactional
    public void delete(String id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found with id: " + id);
        }
        postRepository.deleteById(id);
    }

    private static PostResponseDTO getPostResponseDTO(Post postsaved) {
        return new PostResponseDTO(
                postsaved.getId(),
                postsaved.getTitle(),
                postsaved.getContent(),
                postsaved.getAuthor(),
                postsaved.getCategory().getName()
        );
    }
}
