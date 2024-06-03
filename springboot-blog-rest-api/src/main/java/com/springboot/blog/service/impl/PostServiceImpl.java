package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
private PostRepository postRepository;

public PostServiceImpl(PostRepository postRepository){
    this.postRepository = postRepository;
}

@Override
    public PostDto createPost(PostDto postDto){
    Post post =mapToEntity(postDto);


    Post newPost = postRepository.save(post);


    PostDto postResponse = mapToDTO(newPost);
    return  postResponse;
}

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize) {

    //create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();
        return listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
        Post post  = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post  = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatePost = postRepository.save(post);
        return mapToDTO(updatePost);

    }

    @Override
    public void deletePostById(long id) {
        Post post  = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
    }

    //Convert Entity into DTO
    private PostDto mapToDTO(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getDescription());
        postDto.setDescription(post.getContent());
        postDto.setContent(post.getContent());
        return postDto;
    }

    //Covert DTO to entity
    private Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return  post;
    }
}
