package cn.iocoder.yudao.module.fp.service.bankaccount;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.fp.controller.admin.bankaccount.vo.*;
import cn.iocoder.yudao.module.fp.dal.dataobject.bankaccount.BankAccountDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 银行账户 Service 接口
 *
 * @author 芋道源码
 */
public interface BankAccountService {

    /**
     * 创建银行账户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBankAccount(@Valid BankAccountCreateReqVO createReqVO);

    /**
     * 更新银行账户
     *
     * @param updateReqVO 更新信息
     */
    void updateBankAccount(@Valid BankAccountUpdateReqVO updateReqVO);

    /**
     * 删除银行账户
     *
     * @param id 编号
     */
    void deleteBankAccount(Long id);

    /**
     * 获得银行账户
     *
     * @param id 编号
     * @return 银行账户
     */
    BankAccountDO getBankAccount(Long id);

    /**
     * 获得银行账户列表
     *
     * @param ids 编号
     * @return 银行账户列表
     */
    List<BankAccountDO> getBankAccountList(Collection<Long> ids);

    /**
     * 获得银行账户分页
     *
     * @param pageReqVO 分页查询
     * @return 银行账户分页
     */
    PageResult<BankAccountDO> getBankAccountPage(BankAccountPageReqVO pageReqVO);

    /**
     * 获得银行账户列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 银行账户列表
     */
    List<BankAccountDO> getBankAccountList(BankAccountExportReqVO exportReqVO);

}
