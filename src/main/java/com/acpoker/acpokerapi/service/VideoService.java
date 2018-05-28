package com.acpoker.acpokerapi.service;

import com.acpoker.acpokerapi.entity.Video;
import com.acpoker.acpokerapi.entity.VideoComment;
import com.acpoker.acpokerapi.repository.VideoCommentRepository;
import com.acpoker.acpokerapi.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoCommentRepository videoCommentRepository;

    public boolean addComment(VideoComment comment) {
        videoCommentRepository.save(comment);
        return true;
    }

    public List<VideoComment> findAllComments(Integer id) {
        Video video = videoRepository.findVideoById(id);
        return videoCommentRepository.findVideoCommentsByVideo(video);
    }

    public boolean deleteComment(Integer id) {
        videoCommentRepository.delete(videoCommentRepository.findVideoCommentById(id));
        return true;
    }

    public Page<Video> findAll(Pageable pageable) {
        return videoRepository.findAll(pageable);
    }

    public boolean addVideo(Video video) {
        videoRepository.save(video);
        return true;
    }

    public boolean editVideo(Video video) {
        videoRepository.save(video);
        return true;
    }

    public boolean deleteVideo(Integer id) {
        videoRepository.delete(videoRepository.findVideoById(id));
        return true;
    }
}
