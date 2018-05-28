package com.acpoker.acpokerapi.repository;

import com.acpoker.acpokerapi.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Integer> {

    Video findVideoById(Integer id);

}
