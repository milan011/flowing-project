package cn.iocoder.yudao.module.fp.controller.admin.contract.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 合同 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ContractExcelVO {

    @ExcelProperty("ID")
    private Long id;

    @ExcelProperty("关联项目表ID")
    private Long proId;

    @ExcelProperty("合同名称")
    private String name;

    @ExcelProperty("甲方")
    private String partyA;

    @ExcelProperty("乙方")
    private String partyB;

    @ExcelProperty("金额")
    private Long money;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("启用状态：0->禁用；1->启用")
    private Integer status;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("修改时间")
    private LocalDateTime updateTime;

}
