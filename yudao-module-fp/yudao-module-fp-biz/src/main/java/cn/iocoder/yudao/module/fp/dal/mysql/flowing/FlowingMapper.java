package cn.iocoder.yudao.module.fp.dal.mysql.flowing;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.fp.dal.dataobject.bankaccount.BankAccountDO;
import cn.iocoder.yudao.module.fp.dal.dataobject.flowing.FlowingDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.fp.controller.admin.flowing.vo.*;

/**
 * 流水明细 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface FlowingMapper extends BaseMapperX<FlowingDO> {

    default PageResult<FlowingDO> selectPage(FlowingPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FlowingDO>()
                .likeIfPresent(FlowingDO::getName, reqVO.getName())
                .eqIfPresent(FlowingDO::getFlowingNumber, reqVO.getFlowingNumber())
                .eqIfPresent(FlowingDO::getAccId, reqVO.getAccId())
                .eqIfPresent(FlowingDO::getProId, reqVO.getProId())
                .eqIfPresent(FlowingDO::getConId, reqVO.getConId())
                .eqIfPresent(FlowingDO::getMoney, reqVO.getMoney())
                .eqIfPresent(FlowingDO::getMoneyType, reqVO.getMoneyType())
                .eqIfPresent(FlowingDO::getCostType, reqVO.getCostType())
                .eqIfPresent(FlowingDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(FlowingDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FlowingDO::getId));
    }

  List<FlowingRespVO> selectWithAccountPage(IPage<FlowingRespVO> page);

    default List<FlowingDO> selectList(FlowingExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<FlowingDO>()
                .likeIfPresent(FlowingDO::getName, reqVO.getName())
                .eqIfPresent(FlowingDO::getFlowingNumber, reqVO.getFlowingNumber())
                .eqIfPresent(FlowingDO::getAccId, reqVO.getAccId())
                .eqIfPresent(FlowingDO::getProId, reqVO.getProId())
                .eqIfPresent(FlowingDO::getConId, reqVO.getConId())
                .eqIfPresent(FlowingDO::getMoney, reqVO.getMoney())
                .eqIfPresent(FlowingDO::getMoneyType, reqVO.getMoneyType())
                .eqIfPresent(FlowingDO::getCostType, reqVO.getCostType())
                .eqIfPresent(FlowingDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(FlowingDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FlowingDO::getId));
    }

}
