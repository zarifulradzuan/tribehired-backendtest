package com.backendtest.service;

import com.backendtest.dto.model.CommentDTO;
import com.backendtest.dto.model.PostDTO;

import java.util.List;

public interface InternalService {
    List<CommentDTO> getComments();
    List<PostDTO> getPosts();
}
