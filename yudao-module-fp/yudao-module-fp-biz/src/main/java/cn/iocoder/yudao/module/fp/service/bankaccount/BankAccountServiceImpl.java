package cn.iocoder.yudao.module.fp.service.bankaccount;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.fp.controller.admin.bankaccount.vo.*;
import cn.iocoder.yudao.module.fp.dal.dataobject.bankaccount.BankAccountDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.fp.convert.bankaccount.BankAccountConvert;
import cn.iocoder.yudao.module.fp.dal.mysql.bankaccount.BankAccountMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.fp.enums.ErrorCodeConstants.*;

/**
 * 银行账户 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class BankAccountServiceImpl implements BankAccountService {

    @Resource
    private BankAccountMapper bankAccountMapper;

    @Override
    public Long createBankAccount(BankAccountCreateReqVO createReqVO) {
        // 插入
        BankAccountDO bankAccount = BankAccountConvert.INSTANCE.convert(createReqVO);
        bankAccountMapper.insert(bankAccount);
        // 返回
        return bankAccount.getId();
    }

    @Override
    public void updateBankAccount(BankAccountUpdateReqVO updateReqVO) {
        // 校验存在
        validateBankAccountExists(updateReqVO.getId());
        // 更新
        BankAccountDO updateObj = BankAccountConvert.INSTANCE.convert(updateReqVO);
        bankAccountMapper.updateById(updateObj);
    }

    @Override
    public void deleteBankAccount(Long id) {
        // 校验存在
        validateBankAccountExists(id);
        // 删除
        bankAccountMapper.deleteById(id);
    }

    private void validateBankAccountExists(Long id) {
        if (bankAccountMapper.selectById(id) == null) {
            throw exception(BANK_ACCOUNT_NOT_EXISTS);
        }
    }

    @Override
    public BankAccountDO getBankAccount(Long id) {
        return bankAccountMapper.selectById(id);
    }

    @Override
    public List<BankAccountDO> getBankAccountList(Collection<Long> ids) {
        return bankAccountMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BankAccountDO> getBankAccountPage(BankAccountPageReqVO pageReqVO) {
        return bankAccountMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BankAccountDO> getBankAccountList(BankAccountExportReqVO exportReqVO) {
        return bankAccountMapper.selectList(exportReqVO);
    }

}
