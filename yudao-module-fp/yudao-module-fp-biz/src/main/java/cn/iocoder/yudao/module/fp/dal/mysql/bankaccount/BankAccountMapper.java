package cn.iocoder.yudao.module.fp.dal.mysql.bankaccount;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.fp.dal.dataobject.bankaccount.BankAccountDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.fp.controller.admin.bankaccount.vo.*;

/**
 * 银行账户 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface BankAccountMapper extends BaseMapperX<BankAccountDO> {
	
	default PageResult<BankAccountDO> selectPage(BankAccountPageReqVO reqVO) {
		return selectPage(reqVO, new LambdaQueryWrapperX<BankAccountDO>()
				.eqIfPresent(BankAccountDO::getAccountNumber, reqVO.getAccountNumber())
				.eqIfPresent(BankAccountDO::getAccountBelong, reqVO.getAccountBelong())
				.eqIfPresent(BankAccountDO::getBank, reqVO.getBank())
				.eqIfPresent(BankAccountDO::getBankBelong, reqVO.getBankBelong())
				.eqIfPresent(BankAccountDO::getBalance, reqVO.getBalance())
				.eqIfPresent(BankAccountDO::getStatus, reqVO.getStatus())
				.eqIfPresent(BankAccountDO::getMainBody, reqVO.getMainBody())
				.eqIfPresent(BankAccountDO::getRemark, reqVO.getRemark())
				.betweenIfPresent(BankAccountDO::getCreateTime, reqVO.getCreateTime())
				.betweenIfPresent(BankAccountDO::getModifyTime, reqVO.getModifyTime())
				.orderByDesc(BankAccountDO::getId));
	}
	
	default List<BankAccountDO> selectList(BankAccountExportReqVO reqVO) {
		return selectList(new LambdaQueryWrapperX<BankAccountDO>()
				.eqIfPresent(BankAccountDO::getAccountNumber, reqVO.getAccountNumber())
				.eqIfPresent(BankAccountDO::getAccountBelong, reqVO.getAccountBelong())
				.eqIfPresent(BankAccountDO::getBank, reqVO.getBank())
				.eqIfPresent(BankAccountDO::getBankBelong, reqVO.getBankBelong())
				.eqIfPresent(BankAccountDO::getBalance, reqVO.getBalance())
				.eqIfPresent(BankAccountDO::getStatus, reqVO.getStatus())
				.eqIfPresent(BankAccountDO::getMainBody, reqVO.getMainBody())
				.eqIfPresent(BankAccountDO::getRemark, reqVO.getRemark())
				.betweenIfPresent(BankAccountDO::getCreateTime, reqVO.getCreateTime())
				.betweenIfPresent(BankAccountDO::getModifyTime, reqVO.getModifyTime())
				.orderByDesc(BankAccountDO::getId));
	}
	
}
