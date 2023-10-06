package cn.iocoder.yudao.module.fp.controller.admin.contract.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 合同 Excel 导出 Request VO，参数和 ContractPageReqVO 是一致的")
@Data
public class ContractExportReqVO {

    @Schema(description = "关联项目表ID", example = "18612")
    private Long proId;

    @Schema(description = "合同名称", example = "赵六")
    private String name;

    @Schema(description = "甲方")
    private String partyA;

    @Schema(description = "乙方")
    private String partyB;

    @Schema(description = "金额")
    private Long money;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "启用状态：0->禁用；1->启用", example = "1")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
