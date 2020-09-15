package com.backendtest.api.controller;

import com.backendtest.api.constants.ControllerConstants;
import com.backendtest.dto.model.CommentDTO;
import com.backendtest.dto.model.PostDTO;
import com.backendtest.dto.response.PostResponseDTO;
import com.backendtest.serviceimpl.InternalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = ControllerConstants.POSTS)
public class PostController {

    @Autowired
    InternalServiceImpl internalServiceImpl;

    @GetMapping(path = "")
    public PostResponseDTO getPosts() {
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        List<PostDTO> posts;
        try {
            posts = internalServiceImpl.getPosts(); //get posts from API
        } catch (Exception e) {
            postResponseDTO.setResult(false);
            postResponseDTO.setError(e.getMessage());
            return postResponseDTO;
        }
        postResponseDTO.setResult(true);
        postResponseDTO.setPosts(posts);
        return postResponseDTO;
    }

    @GetMapping(path = ControllerConstants.SORT)
    public PostResponseDTO getSortedPosts(@RequestParam("by") String type) {
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        List<PostDTO> posts;
        List<CommentDTO> comments;
        try {
            posts = internalServiceImpl.getPosts(); //get posts from API
            comments = internalServiceImpl.getComments(); //get comments from API
        } catch (Exception e) {
            postResponseDTO.setResult(false);
            postResponseDTO.setError(e.getMessage());
            return postResponseDTO;
        }

        if (type.matches("top")) {
            HashMap<Integer, Integer> postCommentsNumber = new HashMap<>();
            for (CommentDTO comment : comments) {
                postCommentsNumber.put(
                        comment.getPostId(), postCommentsNumber.getOrDefault(comment.getPostId(), 0) + 1 //count comments for every unique post id
                );
            }
            for (PostDTO post : posts) {
                post.setTotal_number_of_comments(postCommentsNumber.get(post.getPost_id())); //insert number of comments into post object
            }
            posts.sort(Comparator.comparing(PostDTO::getTotal_number_of_comments).reversed()); //sort posts by total number of comments
            postResponseDTO.setResult(true);
            postResponseDTO.setPosts(posts);
            return postResponseDTO;
        }
        postResponseDTO.setResult(false);
        postResponseDTO.setError("Given sort value doesn't match any options");
        return postResponseDTO;
    }
}
