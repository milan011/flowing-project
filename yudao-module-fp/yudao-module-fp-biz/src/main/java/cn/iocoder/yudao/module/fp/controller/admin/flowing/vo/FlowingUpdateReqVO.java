package cn.iocoder.yudao.module.fp.controller.admin.flowing.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 流水明细更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FlowingUpdateReqVO extends FlowingBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15390")
    @NotNull(message = "ID不能为空")
    private Long id;

}
