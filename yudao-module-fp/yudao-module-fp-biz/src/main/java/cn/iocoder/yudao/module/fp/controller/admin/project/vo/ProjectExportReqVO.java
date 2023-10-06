package cn.iocoder.yudao.module.fp.controller.admin.project.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 项目 Excel 导出 Request VO，参数和 ProjectPageReqVO 是一致的")
@Data
public class ProjectExportReqVO {

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "名称", example = "王五")
    private String name;

    @Schema(description = "金额")
    private Long money;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "启用状态：0->禁用；1->启用", example = "2")
    private Integer status;

}
