package cn.iocoder.yudao.module.fp.controller.admin.project.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 项目 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ProjectExcelVO {

    @ExcelProperty("用户ID")
    private Long id;

    @ExcelProperty("项目名称")
    private String name;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
