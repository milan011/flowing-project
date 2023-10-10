package cn.iocoder.yudao.module.fp.service.bankaccount;

import java.util.*;
import javax.validation.*;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.fp.controller.admin.bankaccount.vo.*;
import cn.iocoder.yudao.module.fp.dal.dataobject.bankaccount.BankAccountDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.fp.dal.dataobject.project.ProjectDO;

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
     * 获得有效账户列表
     * @return 账户列表
     */
    List<BankAccountDO> getActiveBankAccountList();

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

    /**
     * 更新账户状态
     *
     * @param id 账户ID
     * @param status 状态
     */
    void updateBankAccountStatus(Long id, Integer status);
    
    /**
     * 获得指定编号的账号 Map
     *
     * @param ids 账号数组
     * @return 账号 Map
     */
    default Map<Long, BankAccountDO> getAccountMap(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyMap();
        }
        List<BankAccountDO> list = getAccountList(ids);
        return CollectionUtils.convertMap(list, BankAccountDO::getId);
    }
    
    /**
     * 获得账号信息数组
     *
     * @param ids 账号编号数组
     * @return 账号信息数组
     */
    List<BankAccountDO> getAccountList(Collection<Long> ids);

}
