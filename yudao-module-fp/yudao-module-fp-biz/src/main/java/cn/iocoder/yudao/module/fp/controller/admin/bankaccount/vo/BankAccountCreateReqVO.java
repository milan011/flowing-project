package cn.iocoder.yudao.module.fp.controller.admin.bankaccount.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 银行账户创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BankAccountCreateReqVO extends BankAccountBaseVO {

}
