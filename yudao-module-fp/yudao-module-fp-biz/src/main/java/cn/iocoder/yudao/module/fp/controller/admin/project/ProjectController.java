package cn.iocoder.yudao.module.fp.controller.admin.project;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.fp.controller.admin.project.vo.*;
import cn.iocoder.yudao.module.fp.dal.dataobject.project.ProjectDO;
import cn.iocoder.yudao.module.fp.convert.project.ProjectConvert;
import cn.iocoder.yudao.module.fp.service.project.ProjectService;

@Tag(name = "管理后台 - 项目")
@RestController
@RequestMapping("/fp/project")
@Validated
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @PostMapping("/create")
    @Operation(summary = "创建项目")
    @PreAuthorize("@ss.hasPermission('fp:project:create')")
    public CommonResult<Long> createProject(@Valid @RequestBody ProjectCreateReqVO createReqVO) {
        return success(projectService.createProject(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新项目")
    @PreAuthorize("@ss.hasPermission('fp:project:update')")
    public CommonResult<Boolean> updateProject(@Valid @RequestBody ProjectUpdateReqVO updateReqVO) {
        projectService.updateProject(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除项目")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('fp:project:delete')")
    public CommonResult<Boolean> deleteProject(@RequestParam("id") Long id) {
        projectService.deleteProject(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得项目")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('fp:project:query')")
    public CommonResult<ProjectRespVO> getProject(@RequestParam("id") Long id) {
        ProjectDO project = projectService.getProject(id);
        return success(ProjectConvert.INSTANCE.convert(project));
    }

    @GetMapping("/list")
    @Operation(summary = "获得项目列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('fp:project:query')")
    public CommonResult<List<ProjectRespVO>> getProjectList(@RequestParam("ids") Collection<Long> ids) {
        List<ProjectDO> list = projectService.getProjectList(ids);
        return success(ProjectConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得项目分页")
    @PreAuthorize("@ss.hasPermission('fp:project:query')")
    public CommonResult<PageResult<ProjectRespVO>> getProjectPage(@Valid ProjectPageReqVO pageVO) {
        PageResult<ProjectDO> pageResult = projectService.getProjectPage(pageVO);
        return success(ProjectConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出项目 Excel")
    @PreAuthorize("@ss.hasPermission('fp:project:export')")
    @OperateLog(type = EXPORT)
    public void exportProjectExcel(@Valid ProjectExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProjectDO> list = projectService.getProjectList(exportReqVO);
        // 导出 Excel
        List<ProjectExcelVO> datas = ProjectConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "项目.xls", "数据", ProjectExcelVO.class, datas);
    }

}
