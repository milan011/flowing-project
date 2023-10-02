package cn.iocoder.yudao.module.fp.convert.bankaccount;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.fp.controller.admin.bankaccount.vo.*;
import cn.iocoder.yudao.module.fp.dal.dataobject.bankaccount.BankAccountDO;

/**
 * 银行账户 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface BankAccountConvert {

    BankAccountConvert INSTANCE = Mappers.getMapper(BankAccountConvert.class);

    BankAccountDO convert(BankAccountCreateReqVO bean);

    BankAccountDO convert(BankAccountUpdateReqVO bean);

    BankAccountRespVO convert(BankAccountDO bean);

    List<BankAccountRespVO> convertList(List<BankAccountDO> list);

    PageResult<BankAccountRespVO> convertPage(PageResult<BankAccountDO> page);

    List<BankAccountExcelVO> convertList02(List<BankAccountDO> list);

}
