package cn.iocoder.yudao.module.fp.controller.admin.contract.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 合同 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ContractBaseVO {

    @Schema(description = "关联项目表ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18612")
    @NotNull(message = "关联项目表ID不能为空")
    private Long proId;

    @Schema(description = "合同名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotNull(message = "合同名称不能为空")
    private String name;

    @Schema(description = "甲方", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "甲方不能为空")
    private String partyA;

    @Schema(description = "乙方", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "乙方不能为空")
    private String partyB;

    @Schema(description = "金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "金额不能为空")
    private Long money;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "启用状态：0->禁用；1->启用", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "启用状态：0->禁用；1->启用不能为空")
    private Integer status;

}
