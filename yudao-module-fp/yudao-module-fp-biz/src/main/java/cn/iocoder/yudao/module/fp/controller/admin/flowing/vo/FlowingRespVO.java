package cn.iocoder.yudao.module.fp.controller.admin.flowing.vo;

import cn.iocoder.yudao.module.fp.controller.admin.bankaccount.vo.BankAccountRespVO;
import cn.iocoder.yudao.module.fp.dal.dataobject.bankaccount.BankAccountDO;
import cn.iocoder.yudao.module.fp.dal.dataobject.contract.ContractDO;
import cn.iocoder.yudao.module.fp.dal.dataobject.project.ProjectDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 流水明细 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FlowingRespVO extends FlowingBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15390")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "修改时间")
    private LocalDateTime updateTime;

    @Schema(description = "所属账户")
    private BankAccountDO account;

    @Schema(description = "所属项目")
    private ProjectDO project;

    @Schema(description = "所属合同")
    private ContractDO contract;

}
