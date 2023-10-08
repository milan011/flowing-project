package cn.iocoder.yudao.module.fp.controller.admin.flowing.vo;

import cn.iocoder.yudao.module.fp.controller.admin.bankaccount.vo.BankAccountRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 流水明细 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FlowingRespVO extends FlowingBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15390")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "修改时间")
    private LocalDateTime updateTime;

    /*@Schema(description = "所属账户")
    private BankAccountRespVO account;*/
    private Account account;

    @Schema(description = "所属账户")
    @Data
    public static class Account {
        @Schema(description = "账户ID")
        private Long id;

        @Schema(description = "账户名称")
        private String name;

    }

}
