package cn.iocoder.yudao.module.fp.controller.admin.flowing.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 流水明细 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class FlowingBaseVO {

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "名称不能为空")
    private String name;

    @Schema(description = "流水号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "流水号不能为空")
    private Long flowingNumber;

    @Schema(description = "所属账户", requiredMode = Schema.RequiredMode.REQUIRED, example = "17540")
    @NotNull(message = "所属账户不能为空")
    private Long accId;

    @Schema(description = "关联项目表ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10215")
    @NotNull(message = "关联项目表ID不能为空")
    private Long proId;

    @Schema(description = "关联合同表ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20756")
    @NotNull(message = "关联合同表ID不能为空")
    private Long conId;

    @Schema(description = "金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "金额不能为空")
    private Long money;

    @Schema(description = "资金类型: 1->投资;2->回款;3->费用;9->其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    //@NotNull(message = "资金类型: 1->投资;2->回款;3->费用;9->其他不能为空")
    private Integer moneyType;

    @Schema(description = "费用类型: 1->费用1;2->费用2;3->费用3;9->其他", example = "2")
    //@NotNull(message = "费用类型: 1->费用1;2->费用2;3->费用3;9->其他不能为空")
    private Integer costType;

    @Schema(description = "状态：0->无效；1->有效", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态：0->无效；1->有效不能为空")
    private Integer status;

    @Schema(description = "虚拟：0->虚拟；1->真实", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "虚拟：0->虚拟；1->真实不能为空")
    private Integer virtually;

}
