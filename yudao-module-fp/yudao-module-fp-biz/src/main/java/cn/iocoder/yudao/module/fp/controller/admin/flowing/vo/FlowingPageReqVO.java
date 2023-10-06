package cn.iocoder.yudao.module.fp.controller.admin.flowing.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 流水明细分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FlowingPageReqVO extends PageParam {

    @Schema(description = "名称", example = "芋艿")
    private String name;

    @Schema(description = "流水号")
    private Long flowingNumber;

    @Schema(description = "所属账户", example = "17540")
    private Long accId;

    @Schema(description = "关联项目表ID", example = "10215")
    private Long proId;

    @Schema(description = "关联合同表ID", example = "20756")
    private Long conId;

    @Schema(description = "金额")
    private Long money;

    @Schema(description = "资金类型: 1->投资;2->回款;3->费用;9->其他", example = "1")
    private Integer moneyType;

    @Schema(description = "费用类型: 1->费用1;2->费用2;3->费用3;9->其他", example = "2")
    private Integer costType;

    @Schema(description = "状态：0->无效；1->有效", example = "1")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
