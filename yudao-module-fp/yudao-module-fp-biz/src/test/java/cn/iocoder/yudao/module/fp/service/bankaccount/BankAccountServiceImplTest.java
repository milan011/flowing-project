package cn.iocoder.yudao.module.fp.service.bankaccount;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.fp.controller.admin.bankaccount.vo.*;
import cn.iocoder.yudao.module.fp.dal.dataobject.bankaccount.BankAccountDO;
import cn.iocoder.yudao.module.fp.dal.mysql.bankaccount.BankAccountMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.fp.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link BankAccountServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(BankAccountServiceImpl.class)
public class BankAccountServiceImplTest extends BaseDbUnitTest {

    @Resource
    private BankAccountServiceImpl bankAccountService;

    @Resource
    private BankAccountMapper bankAccountMapper;

    @Test
    public void testCreateBankAccount_success() {
        // 准备参数
        BankAccountCreateReqVO reqVO = randomPojo(BankAccountCreateReqVO.class);

        // 调用
        Long bankAccountId = bankAccountService.createBankAccount(reqVO);
        // 断言
        assertNotNull(bankAccountId);
        // 校验记录的属性是否正确
        BankAccountDO bankAccount = bankAccountMapper.selectById(bankAccountId);
        assertPojoEquals(reqVO, bankAccount);
    }

    @Test
    public void testUpdateBankAccount_success() {
        // mock 数据
        BankAccountDO dbBankAccount = randomPojo(BankAccountDO.class);
        bankAccountMapper.insert(dbBankAccount);// @Sql: 先插入出一条存在的数据
        // 准备参数
        BankAccountUpdateReqVO reqVO = randomPojo(BankAccountUpdateReqVO.class, o -> {
            o.setId(dbBankAccount.getId()); // 设置更新的 ID
        });

        // 调用
        bankAccountService.updateBankAccount(reqVO);
        // 校验是否更新正确
        BankAccountDO bankAccount = bankAccountMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, bankAccount);
    }

    @Test
    public void testUpdateBankAccount_notExists() {
        // 准备参数
        BankAccountUpdateReqVO reqVO = randomPojo(BankAccountUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> bankAccountService.updateBankAccount(reqVO), BANK_ACCOUNT_NOT_EXISTS);
    }

    @Test
    public void testDeleteBankAccount_success() {
        // mock 数据
        BankAccountDO dbBankAccount = randomPojo(BankAccountDO.class);
        bankAccountMapper.insert(dbBankAccount);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbBankAccount.getId();

        // 调用
        bankAccountService.deleteBankAccount(id);
       // 校验数据不存在了
       assertNull(bankAccountMapper.selectById(id));
    }

    @Test
    public void testDeleteBankAccount_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> bankAccountService.deleteBankAccount(id), BANK_ACCOUNT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBankAccountPage() {
       // mock 数据
       BankAccountDO dbBankAccount = randomPojo(BankAccountDO.class, o -> { // 等会查询到
           o.setAccountNumber(null);
           o.setAccountBelong(null);
           o.setBank(null);
           o.setBankBelong(null);
           o.setBalance(null);
           o.setStatus(null);
           o.setMainBody(null);
           o.setRemark(null);
           o.setCreateTime(null);
           o.setUpdateTime(null);
       });
       bankAccountMapper.insert(dbBankAccount);
       // 测试 accountNumber 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setAccountNumber(null)));
       // 测试 accountBelong 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setAccountBelong(null)));
       // 测试 bank 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setBank(null)));
       // 测试 bankBelong 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setBankBelong(null)));
       // 测试 balance 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setBalance(null)));
       // 测试 status 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setStatus(null)));
       // 测试 mainBody 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setMainBody(null)));
       // 测试 remark 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setCreateTime(null)));
       // 测试 updateTime 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setUpdateTime(null)));
       // 准备参数
       BankAccountPageReqVO reqVO = new BankAccountPageReqVO();
       reqVO.setAccountNumber(null);
       reqVO.setAccountBelong(null);
       reqVO.setBank(null);
       reqVO.setBankBelong(null);
       reqVO.setBalance(null);
       reqVO.setStatus(null);
       reqVO.setMainBody(null);
       reqVO.setRemark(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setUpdateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<BankAccountDO> pageResult = bankAccountService.getBankAccountPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbBankAccount, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBankAccountList() {
       // mock 数据
       BankAccountDO dbBankAccount = randomPojo(BankAccountDO.class, o -> { // 等会查询到
           o.setAccountNumber(null);
           o.setAccountBelong(null);
           o.setBank(null);
           o.setBankBelong(null);
           o.setBalance(null);
           o.setStatus(null);
           o.setMainBody(null);
           o.setRemark(null);
           o.setCreateTime(null);
           o.setUpdateTime(null);
       });
       bankAccountMapper.insert(dbBankAccount);
       // 测试 accountNumber 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setAccountNumber(null)));
       // 测试 accountBelong 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setAccountBelong(null)));
       // 测试 bank 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setBank(null)));
       // 测试 bankBelong 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setBankBelong(null)));
       // 测试 balance 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setBalance(null)));
       // 测试 status 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setStatus(null)));
       // 测试 mainBody 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setMainBody(null)));
       // 测试 remark 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setCreateTime(null)));
       // 测试 updateTime 不匹配
       bankAccountMapper.insert(cloneIgnoreId(dbBankAccount, o -> o.setUpdateTime(null)));
       // 准备参数
       BankAccountExportReqVO reqVO = new BankAccountExportReqVO();
       reqVO.setAccountNumber(null);
       reqVO.setAccountBelong(null);
       reqVO.setBank(null);
       reqVO.setBankBelong(null);
       reqVO.setBalance(null);
       reqVO.setStatus(null);
       reqVO.setMainBody(null);
       reqVO.setRemark(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setUpdateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<BankAccountDO> list = bankAccountService.getBankAccountList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbBankAccount, list.get(0));
    }

}
