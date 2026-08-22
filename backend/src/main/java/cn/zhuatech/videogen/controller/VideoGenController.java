/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.videogen.controller;

import cn.zhuatech.videogen.service.VideoGenService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/videogen")
@CrossOrigin(originPatterns = {"http://localhost:*", "http://127.0.0.1:*"})
public class VideoGenController {
    private final VideoGenService service;
    public VideoGenController(VideoGenService service) { this.service = service; }
    @PostMapping("/plan") public VideoGenService.Result plan(@Valid @RequestBody VideoGenService.Request request) { return service.plan(request); }
}
