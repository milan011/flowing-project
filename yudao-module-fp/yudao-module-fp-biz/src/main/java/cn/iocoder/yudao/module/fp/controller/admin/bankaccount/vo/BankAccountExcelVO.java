package cn.iocoder.yudao.module.fp.controller.admin.bankaccount.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 银行账户 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class BankAccountExcelVO {

    @ExcelProperty("ID")
    private Long id;

    @ExcelProperty("卡号")
    private String accountNumber;

    @ExcelProperty("所属人/公司")
    private String accountBelong;

    @ExcelProperty("所属银行")
    private String bank;

    @ExcelProperty("开户行")
    private String bankBelong;

    @ExcelProperty("账户余额")
    private Long balance;

    @ExcelProperty("启用状态：0->禁用；1->启用")
    private Integer status;

    @ExcelProperty("公/私：1->公户；2->个人账户")
    private Integer mainBody;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("修改时间")
    private LocalDateTime updateTime;

}
