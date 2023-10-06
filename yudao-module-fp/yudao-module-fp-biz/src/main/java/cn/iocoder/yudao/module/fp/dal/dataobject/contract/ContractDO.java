package cn.iocoder.yudao.module.fp.dal.dataobject.contract;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 合同 DO
 *
 * @author 芋道源码
 */
@TableName("fp_contract")
@KeySequence("fp_contract_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 关联项目表ID
     */
    private Long proId;
    /**
     * 合同名称
     */
    private String name;
    /**
     * 甲方
     */
    private String partyA;
    /**
     * 乙方
     */
    private String partyB;
    /**
     * 金额
     */
    private Long money;
    /**
     * 备注
     */
    private String remark;
    /**
     * 启用状态：0->禁用；1->启用
     */
    private Integer status;

}
