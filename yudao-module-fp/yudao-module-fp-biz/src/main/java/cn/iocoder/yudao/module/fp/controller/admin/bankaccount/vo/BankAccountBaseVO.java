package cn.iocoder.yudao.module.fp.controller.admin.bankaccount.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 银行账户 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BankAccountBaseVO {

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

    @Schema(description = "账户名称", example = "wm")
    private String name;

}
