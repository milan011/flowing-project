package cn.iocoder.yudao.module.fp.service.project;

import cn.iocoder.yudao.module.fp.dal.dataobject.bankaccount.BankAccountDO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.fp.controller.admin.project.vo.*;
import cn.iocoder.yudao.module.fp.dal.dataobject.project.ProjectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.fp.convert.project.ProjectConvert;
import cn.iocoder.yudao.module.fp.dal.mysql.project.ProjectMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.fp.enums.ErrorCodeConstants.*;

/**
 * 项目 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public Long createProject(ProjectCreateReqVO createReqVO) {

        // 校验正确性
        validateProjectForCreateOrUpdate(null, createReqVO.getName());
        // 插入
        ProjectDO project = ProjectConvert.INSTANCE.convert(createReqVO);
        projectMapper.insert(project);
        // 返回
        return project.getId();
    }

    @Override
    public void updateProject(ProjectUpdateReqVO updateReqVO) {
        // 校验存在
        //validateProjectExists(updateReqVO.getId());
        // 更新
        validateProjectForCreateOrUpdate(updateReqVO.getId(),updateReqVO.getName());

        ProjectDO updateObj = ProjectConvert.INSTANCE.convert(updateReqVO);
        projectMapper.updateById(updateObj);
    }

    @Override
    public void updateProjectStatus(Long id, Integer status) {
        // 校验是否可以更新
        //validateProjectForUpdate(id);

        // 更新状态
        ProjectDO updateObj = new ProjectDO().setId(id).setStatus(status);
        projectMapper.updateById(updateObj);
    }

    @Override
    public void deleteProject(Long id) {
        // 校验存在
        validateProjectExists(id);
        // 删除
        projectMapper.deleteById(id);
    }

    @Override
    public ProjectDO getProject(Long id) {
        return projectMapper.selectById(id);
    }

    @Override
    public List<ProjectDO> getProjectList(Collection<Long> ids) {
        return projectMapper.selectBatchIds(ids);
    }

    @Override
    public List<ProjectDO> getActiveProjectList() {
        return projectMapper.selectActiveList();
    }

    @Override
    public PageResult<ProjectDO> getProjectPage(ProjectPageReqVO pageReqVO) {
        return projectMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProjectDO> getProjectList(ProjectExportReqVO exportReqVO) {
        return projectMapper.selectList(exportReqVO);
    }

    private void validateProjectForCreateOrUpdate(Long id, String name) {
        // 校验自己存在
        validateProjectExists(id);
        // 校验岗位名的唯一性
        validateProjectNameUnique(id, name);

    }

    private void validateProjectExists(Long id) {
        if (id == null) {
            return;
        }
        if (projectMapper.selectById(id) == null) {
            throw exception(BANK_ACCOUNT_NOT_EXISTS);
        }
    }

    private void validateProjectNameUnique(Long id, String name) {
        ProjectDO project = projectMapper.selectByName(name);
        if (project == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的岗位
        if (id == null) {
            throw exception(PROJECT_DUPLICATE);
        }
        if (!project.getId().equals(id)) {
            throw exception(PROJECT_DUPLICATE);
        }
    }

}
