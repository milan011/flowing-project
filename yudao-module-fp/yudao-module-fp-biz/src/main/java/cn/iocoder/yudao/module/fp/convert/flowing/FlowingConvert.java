package cn.iocoder.yudao.module.fp.convert.flowing;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.fp.controller.admin.flowing.vo.*;
import cn.iocoder.yudao.module.fp.dal.dataobject.flowing.FlowingDO;
import cn.iocoder.yudao.module.fp.dal.dataobject.bankaccount.BankAccountDO;

/**
 * 流水明细 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface FlowingConvert {

    FlowingConvert INSTANCE = Mappers.getMapper(FlowingConvert.class);

    FlowingDO convert(FlowingCreateReqVO bean);

    FlowingDO convert(FlowingUpdateReqVO bean);
    
    FlowingRespVO convert(FlowingDO bean);
    
    List<FlowingRespVO> convertList(List<FlowingDO> list);

    PageResult<FlowingRespVO> convertPage(PageResult<FlowingDO> page);

    List<FlowingExcelVO> convertList02(List<FlowingDO> list);

}
