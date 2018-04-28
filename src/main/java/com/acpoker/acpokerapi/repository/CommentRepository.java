package com.acpoker.acpokerapi.repository;

import com.acpoker.acpokerapi.entity.Comment;
import com.acpoker.acpokerapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findCommentsByPost(Post post);
}
