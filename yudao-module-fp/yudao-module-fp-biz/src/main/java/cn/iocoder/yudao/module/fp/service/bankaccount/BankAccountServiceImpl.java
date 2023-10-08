package cn.iocoder.yudao.module.fp.service.bankaccount;

import cn.iocoder.yudao.module.fp.dal.dataobject.project.ProjectDO;
import org.springframework.cache.annotation.CacheEvict;
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
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.POST_NAME_DUPLICATE;

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

    // 校验正确性
    validateBankAccountForCreateOrUpdate(null, createReqVO.getAccountNumber());

    // 插入
    BankAccountDO bankAccount = BankAccountConvert.INSTANCE.convert(createReqVO);
    bankAccountMapper.insert(bankAccount);
    // 返回
    return bankAccount.getId();
  }

  @Override
  public void updateBankAccount(BankAccountUpdateReqVO updateReqVO) {
    // 校验存在
    //validateBankAccountExists(updateReqVO.getId());
    // 校验正确性
    validateBankAccountForCreateOrUpdate(updateReqVO.getId(),updateReqVO.getAccountNumber());
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
    if (id == null) {
      return;
    }
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

  @Override
  public List<BankAccountDO> getActiveBankAccountList() {
    return bankAccountMapper.selectActiveList();
  }

  @Override
  public void updateBankAccountStatus(Long id, Integer status) {
    // 校验是否可以更新
    //validateBankAccountForUpdate(id);

    // 更新状态
    BankAccountDO updateObj = new BankAccountDO().setId(id).setStatus(status);
    bankAccountMapper.updateById(updateObj);
  }

  private void validateBankAccountForCreateOrUpdate(Long id, String accountNumber) {
    // 校验自己存在
    validateBankAccountExists(id);
    // 校验岗位名的唯一性
    validateBankAccountNameUnique(id, accountNumber);

  }

  private void validateBankAccountNameUnique(Long id, String name) {
    BankAccountDO bankAccount = bankAccountMapper.selectByName(name);
    if (bankAccount == null) {
      return;
    }
    // 如果 id 为空，说明不用比较是否为相同 id 的岗位
    if (id == null) {
      throw exception(BANK_ACCOUNT_NAME_DUPLICATE);
    }
    if (!bankAccount.getId().equals(id)) {
      throw exception(BANK_ACCOUNT_NAME_DUPLICATE);
    }
  }

}
