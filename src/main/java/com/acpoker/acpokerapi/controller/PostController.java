package com.acpoker.acpokerapi.controller;

import com.acpoker.acpokerapi.entity.Comment;
import com.acpoker.acpokerapi.entity.Post;
import com.acpoker.acpokerapi.model.Game;
import com.acpoker.acpokerapi.model.PostDTO;
import com.acpoker.acpokerapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @CrossOrigin(value = "*")
    public boolean addPost(@RequestBody Post post) {
        return postService.addPost(post);
    }

    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    @CrossOrigin(value = "*")
    public boolean addComment(@RequestBody Comment comment) {
        return postService.addComment(comment);
    }

    @RequestMapping(value = "/comment/delete/{id}", method = RequestMethod.DELETE)
    @CrossOrigin(value = "*")
    public boolean deleteComment(@PathVariable("id") Integer id){
        return postService.deleteComment(id);
    }

    @GetMapping("/comments/all/{id}")
    @CrossOrigin(value = "*")
    public List<Comment> findAllComments(@PathVariable("id") Integer id) {
        return postService.findAllComments(id);
    }

    @GetMapping("/all")
    @CrossOrigin(value = "*")
    public Page<Post> findAll(@RequestParam("page") Integer page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by(new Sort.Order(Sort.Direction.DESC, "date")));
        return postService.findAll(pageable);
    }

    @PatchMapping("/edit")
    @CrossOrigin(value = "*")
    public boolean editPost(@RequestBody Post post){
        return postService.editPost(post);
    }

    @RequestMapping(value = "find/{id}", method = RequestMethod.GET)
    @CrossOrigin(value = "*")
    public PostDTO findById(@PathVariable("id") Integer id){
        return postService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(value = "*")
    public boolean delete(@PathVariable("id") Integer id){
        return postService.deletePost(id);
    }

    @RequestMapping(value = "hand/{id}", method = RequestMethod.GET)
    @CrossOrigin(value = "*")
    public Game findHandById(@PathVariable("id") Integer id){
        return postService.findHandByPostId(id);
    }
}
