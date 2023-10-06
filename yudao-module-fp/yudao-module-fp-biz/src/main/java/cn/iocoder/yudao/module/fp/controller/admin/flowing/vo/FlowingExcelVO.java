package cn.iocoder.yudao.module.fp.controller.admin.flowing.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 流水明细 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class FlowingExcelVO {

    @ExcelProperty("ID")
    private Long id;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("流水号")
    private Long flowingNumber;

    @ExcelProperty("所属账户")
    private Long accId;

    @ExcelProperty("关联项目表ID")
    private Long proId;

    @ExcelProperty("关联合同表ID")
    private Long conId;

    @ExcelProperty("金额")
    private Long money;

    @ExcelProperty("资金类型: 1->投资;2->回款;3->费用;9->其他")
    private Integer moneyType;

    @ExcelProperty("费用类型: 1->费用1;2->费用2;3->费用3;9->其他")
    private Integer costType;

    @ExcelProperty("状态：0->无效；1->有效")
    private Integer status;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("修改时间")
    private LocalDateTime updateTime;

}
