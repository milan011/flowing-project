package cn.iocoder.yudao.module.fp.service.flowing;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.fp.controller.admin.flowing.vo.*;
import cn.iocoder.yudao.module.fp.dal.dataobject.flowing.FlowingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 流水明细 Service 接口
 *
 * @author 芋道源码
 */
public interface FlowingService {

    /**
     * 创建流水明细
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFlowing(@Valid FlowingCreateReqVO createReqVO);

    /**
     * 更新流水明细
     *
     * @param updateReqVO 更新信息
     */
    void updateFlowing(@Valid FlowingUpdateReqVO updateReqVO);

    /**
     * 删除流水明细
     *
     * @param id 编号
     */
    void deleteFlowing(Long id);

    /**
     * 获得流水明细
     *
     * @param id 编号
     * @return 流水明细
     */
    FlowingDO getFlowing(Long id);

    /**
     * 获得流水明细列表
     *
     * @param ids 编号
     * @return 流水明细列表
     */
    List<FlowingDO> getFlowingList(Collection<Long> ids);

    /**
     * 获得流水明细分页
     *
     * @param pageReqVO 分页查询
     * @return 流水明细分页
     */
    PageResult<FlowingDO> getFlowingPage(FlowingPageReqVO pageReqVO);

    /**
     * 获得流水明细列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 流水明细列表
     */
    List<FlowingDO> getFlowingList(FlowingExportReqVO exportReqVO);

}
