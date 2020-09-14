package com.backendtest.serviceimpl;
import com.backendtest.dto.model.CommentDTO;
import com.backendtest.dto.model.PostDTO;
import com.backendtest.service.InternalService;
import com.backendtest.api.constants.CommonConstants;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class InternalServiceImpl implements InternalService{

    @Override
    public List<CommentDTO> getComments() {
        RestTemplate restTemplate = new RestTemplate();
        final String url = CommonConstants.URL + "/comments";
        return restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CommentDTO>>() {
                }).getBody();
    }

    @Override
    public List<PostDTO> getPosts() {
        RestTemplate restTemplate = new RestTemplate();
        final String url = CommonConstants.URL + "/posts";
        return restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<PostDTO>>() {
                }).getBody();
    }
}
