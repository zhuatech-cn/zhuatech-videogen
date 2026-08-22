/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.videogen;

import cn.zhuatech.videogen.service.VideoGenService;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class VideoGenServiceTests {
    private final VideoGenService service = new VideoGenService();
    @Test void createsSixShotStoryboard() {
        var result = service.plan(new VideoGenService.Request("面向制造企业负责人，展示设备预警如何减少停机并引导预约演示。", 36, "16:9", "企业纪实", true));
        assertThat(result.status()).isEqualTo("READY");
        assertThat(result.storyboard()).hasSize(6);
    }
    @Test void blocksUnlicensedBrandMaterial() {
        assertThat(service.plan(new VideoGenService.Request("企业产品介绍视频创意", 30, "16:9", "简洁", false)).status()).isEqualTo("BLOCKED");
    }
}
