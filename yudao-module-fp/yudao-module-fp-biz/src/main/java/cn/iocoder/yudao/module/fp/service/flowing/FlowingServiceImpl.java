package cn.iocoder.yudao.module.fp.service.flowing;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.fp.controller.admin.flowing.vo.*;
import cn.iocoder.yudao.module.fp.dal.dataobject.flowing.FlowingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.fp.convert.flowing.FlowingConvert;
import cn.iocoder.yudao.module.fp.dal.mysql.flowing.FlowingMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.fp.enums.ErrorCodeConstants.*;

/**
 * 流水明细 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class FlowingServiceImpl implements FlowingService {

    @Resource
    private FlowingMapper flowingMapper;

    @Override
    public Long createFlowing(FlowingCreateReqVO createReqVO) {
        // 插入
        FlowingDO flowing = FlowingConvert.INSTANCE.convert(createReqVO);
        flowingMapper.insert(flowing);
        // 返回
        return flowing.getId();
    }

    @Override
    public void updateFlowing(FlowingUpdateReqVO updateReqVO) {
        // 校验存在
        validateFlowingExists(updateReqVO.getId());
        // 更新
        FlowingDO updateObj = FlowingConvert.INSTANCE.convert(updateReqVO);
        flowingMapper.updateById(updateObj);
    }

    @Override
    public void deleteFlowing(Long id) {
        // 校验存在
        validateFlowingExists(id);
        // 删除
        flowingMapper.deleteById(id);
    }

    private void validateFlowingExists(Long id) {
        if (flowingMapper.selectById(id) == null) {
            throw exception(FLOWING_NOT_EXISTS);
        }
    }

    @Override
    public FlowingDO getFlowing(Long id) {
        return flowingMapper.selectById(id);
    }

    @Override
    public List<FlowingDO> getFlowingList(Collection<Long> ids) {
        return flowingMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<FlowingDO> getFlowingPage(FlowingPageReqVO pageReqVO) {
        return flowingMapper.selectPage(pageReqVO);
    }

    @Override
    public List<FlowingDO> getFlowingList(FlowingExportReqVO exportReqVO) {
        return flowingMapper.selectList(exportReqVO);
    }

}
