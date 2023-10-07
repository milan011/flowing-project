package cn.iocoder.yudao.module.fp.dal.dataobject.bankaccount;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 银行账户 DO
 *
 * @author 芋道源码
 */
@TableName("fp_bank_account")
@KeySequence("fp_bank_account_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDO extends BaseDO {

  /**
   * ID
   */
  @TableId
  private Long id;
  /**
   * 卡号
   */
  private String accountNumber;
  /**
   * 所属人/公司
   */
  private String accountBelong;
  /**
   * 所属银行
   */
  private String bank;
  /**
   * 开户行
   */
  private String bankBelong;
  /**
   * 账户余额
   */
  private Long balance;
  /**
   * 启用状态：0->禁用；1->启用
   */
  private Integer status;
  /**
   * 公/私：1->公户；2->个人账户
   */
  private Integer mainBody;
  /**
   * 备注
   */
  private String remark;

  /**
   * 账户名称
   */
  private String name;

}
