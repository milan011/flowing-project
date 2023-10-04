package cn.iocoder.yudao.module.fp.controller.admin.bankaccount.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 银行账户 Excel 导出 Request VO，参数和 BankAccountPageReqVO 是一致的")
@Data
public class BankAccountExportReqVO {

    @Schema(description = "卡号")
    private String accountNumber;

    @Schema(description = "所属人/公司")
    private String accountBelong;

    @Schema(description = "所属银行")
    private String bank;

    @Schema(description = "开户行")
    private String bankBelong;

    @Schema(description = "账户余额")
    private Long balance;

    @Schema(description = "启用状态：0->禁用；1->启用", example = "2")
    private Integer status;

    @Schema(description = "公/私：1->公户；2->个人账户")
    private Integer mainBody;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
