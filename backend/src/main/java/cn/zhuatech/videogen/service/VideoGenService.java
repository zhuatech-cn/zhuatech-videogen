/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.videogen.service;

import jakarta.validation.constraints.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VideoGenService {
    public Result plan(Request request) {
        int shotCount = Math.max(3, Math.min(12, (int) Math.ceil(request.durationSeconds() / 6.0)));
        List<Shot> shots = new ArrayList<>();
        for (int i = 1; i <= shotCount; i++) {
            String purpose = i == 1 ? "建立场景与主题" : i == shotCount ? "品牌收束与行动引导" : "展开第 %d 个核心信息".formatted(i - 1);
            shots.add(new Shot(i, Math.round(request.durationSeconds() * 10.0 / shotCount) / 10.0,
                purpose, i % 3 == 0 ? "特写" : i % 2 == 0 ? "中景" : "全景", "稳定推镜 + 自然环境运动"));
        }
        List<String> checks = new ArrayList<>();
        checks.add(request.brief().length() >= 30 ? "创意简报信息量充足" : "建议补充受众、场景和核心卖点");
        checks.add(request.brandAuthorized() ? "品牌与素材使用权已确认" : "品牌或素材授权尚未确认");
        checks.add(request.durationSeconds() <= 90 ? "时长适合单条企业短视频" : "建议拆分为系列内容");
        String status = !request.brandAuthorized() ? "BLOCKED" : request.brief().length() >= 30 ? "READY" : "NEEDS_BRIEF";
        return new Result(status, shotCount, request.aspectRatio(), List.copyOf(shots), List.copyOf(checks),
            Map.of("prompt", request.brief(), "duration", request.durationSeconds(), "aspectRatio", request.aspectRatio(), "style", request.visualStyle(), "seedPolicy", "reproducible"),
            "LOCAL_STORYBOARD_ENGINE");
    }

    public record Request(@NotBlank @Size(max = 3000) String brief,
                          @Min(6) @Max(180) int durationSeconds,
                          @NotBlank String aspectRatio,
                          @NotBlank String visualStyle,
                          boolean brandAuthorized) {}
    public record Shot(int sequence, double seconds, String purpose, String framing, String motion) {}
    public record Result(String status, int shotCount, String aspectRatio, List<Shot> storyboard,
                         List<String> checks, Map<String, Object> providerPayload, String executionMode) {}
}
