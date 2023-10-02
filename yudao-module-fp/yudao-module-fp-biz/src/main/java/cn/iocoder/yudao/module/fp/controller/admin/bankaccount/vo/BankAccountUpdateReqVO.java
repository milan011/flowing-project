package cn.iocoder.yudao.module.fp.controller.admin.bankaccount.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 银行账户更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BankAccountUpdateReqVO extends BankAccountBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30789")
    @NotNull(message = "ID不能为空")
    private Long id;

}
