package com.acpoker.acpokerapi.repository;

import com.acpoker.acpokerapi.entity.Post;
import javafx.geometry.Pos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findPostById(Integer id);

    Page<Post> findPostByUsername(String usename, Pageable pageable);

}
