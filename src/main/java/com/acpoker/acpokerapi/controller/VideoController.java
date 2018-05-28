package com.acpoker.acpokerapi.controller;

import com.acpoker.acpokerapi.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;
}
