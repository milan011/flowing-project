package cn.iocoder.yudao.module.fp.dal.mysql.contract;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.fp.dal.dataobject.contract.ContractDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.fp.controller.admin.contract.vo.*;

/**
 * 合同 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ContractMapper extends BaseMapperX<ContractDO> {

    default PageResult<ContractDO> selectPage(ContractPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ContractDO>()
                .eqIfPresent(ContractDO::getProId, reqVO.getProId())
                .likeIfPresent(ContractDO::getName, reqVO.getName())
                .eqIfPresent(ContractDO::getPartyA, reqVO.getPartyA())
                .eqIfPresent(ContractDO::getPartyB, reqVO.getPartyB())
                .eqIfPresent(ContractDO::getMoney, reqVO.getMoney())
                .eqIfPresent(ContractDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ContractDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ContractDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ContractDO::getId));
    }

    default List<ContractDO> selectList(ContractExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ContractDO>()
                .eqIfPresent(ContractDO::getProId, reqVO.getProId())
                .likeIfPresent(ContractDO::getName, reqVO.getName())
                .eqIfPresent(ContractDO::getPartyA, reqVO.getPartyA())
                .eqIfPresent(ContractDO::getPartyB, reqVO.getPartyB())
                .eqIfPresent(ContractDO::getMoney, reqVO.getMoney())
                .eqIfPresent(ContractDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ContractDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ContractDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ContractDO::getId));
    }

}
