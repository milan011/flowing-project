package cn.iocoder.yudao.module.fp.dal.mysql.project;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.fp.dal.dataobject.project.ProjectDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.fp.controller.admin.project.vo.*;

/**
 * 项目 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProjectMapper extends BaseMapperX<ProjectDO> {

    default PageResult<ProjectDO> selectPage(ProjectPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProjectDO>()
                .betweenIfPresent(ProjectDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(ProjectDO::getName, reqVO.getName())
                .eqIfPresent(ProjectDO::getMoney, reqVO.getMoney())
                .eqIfPresent(ProjectDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ProjectDO::getStatus, reqVO.getStatus())
                .orderByDesc(ProjectDO::getId));
    }

    default List<ProjectDO> selectList(ProjectExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProjectDO>()
                .betweenIfPresent(ProjectDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(ProjectDO::getName, reqVO.getName())
                .eqIfPresent(ProjectDO::getMoney, reqVO.getMoney())
                .eqIfPresent(ProjectDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ProjectDO::getStatus, reqVO.getStatus())
                .orderByDesc(ProjectDO::getId));
    }

}
