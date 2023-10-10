package cn.iocoder.yudao.module.fp.dal.dataobject.flowing;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 流水明细 DO
 *
 * @author 芋道源码
 */
@TableName("fp_flowing")
@KeySequence("fp_flowing_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlowingDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 流水号
     */
    private Long flowingNumber;
    /**
     * 所属账户
     */
    private Long accId;
    /**
     * 关联项目表ID
     */
    private Long proId;
    /**
     * 关联合同表ID
     */
    private Long conId;
    /**
     * 金额
     */
    private Long money;
    /**
     * 资金类型: 1->投资;2->回款;3->费用;9->其他
     */
    private Integer moneyType;
    /**
     * 费用类型: 1->费用1;2->费用2;3->费用3;9->其他
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer costType;
    /**
     * 状态：0->无效；1->有效
     */
    private Integer status;

}
