package cn.iocoder.yudao.module.fp.service.project;

import java.util.*;
import javax.validation.*;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.fp.controller.admin.project.vo.*;
import cn.iocoder.yudao.module.fp.dal.dataobject.bankaccount.BankAccountDO;
import cn.iocoder.yudao.module.fp.dal.dataobject.project.ProjectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 项目 Service 接口
 *
 * @author 芋道源码
 */
public interface ProjectService {

    /**
     * 创建项目
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProject(@Valid ProjectCreateReqVO createReqVO);

    /**
     * 更新项目
     *
     * @param updateReqVO 更新信息
     */
    void updateProject(@Valid ProjectUpdateReqVO updateReqVO);

    /**
     * 删除项目
     *
     * @param id 编号
     */
    void deleteProject(Long id);

    /**
     * 获得项目
     *
     * @param id 编号
     * @return 项目
     */
    ProjectDO getProject(Long id);

    /**
     * 获得项目列表
     *
     * @param ids 编号
     * @return 项目列表
     */
    List<ProjectDO> getProjectList(Collection<Long> ids);

    /**
     * 获得有效项目列表
     * @return 项目列表
     */
    List<ProjectDO> getActiveProjectList();

    /**
     * 获得项目分页
     *
     * @param pageReqVO 分页查询
     * @return 项目分页
     */
    PageResult<ProjectDO> getProjectPage(ProjectPageReqVO pageReqVO);

    /**
     * 获得项目列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 项目列表
     */
    List<ProjectDO> getProjectList(ProjectExportReqVO exportReqVO);

    /**
     * 更新项目状态
     *
     * @param id 项目ID
     * @param status 状态
     */
    void updateProjectStatus(Long id, Integer status);
    
    /**
     * 获得指定编号的项目 Map
     *
     * @param ids 项目数组
     * @return 项目 Map
     */
    default Map<Long, ProjectDO> getProjectMap(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyMap();
        }
        List<ProjectDO> list = getProjectList(ids);
        return CollectionUtils.convertMap(list, ProjectDO::getId);
    }

}
