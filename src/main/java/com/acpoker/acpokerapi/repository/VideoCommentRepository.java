package com.acpoker.acpokerapi.repository;

import com.acpoker.acpokerapi.entity.Video;
import com.acpoker.acpokerapi.entity.VideoComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoCommentRepository extends JpaRepository<VideoComment, Integer> {

    List<VideoComment> findVideoCommentsByVideo(Video video);

    VideoComment findVideoCommentById(Integer id);
}
