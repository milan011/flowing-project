package cn.iocoder.yudao.module.fp.service.contract;

import cn.iocoder.yudao.module.fp.dal.dataobject.project.ProjectDO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.fp.controller.admin.contract.vo.*;
import cn.iocoder.yudao.module.fp.dal.dataobject.contract.ContractDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.fp.convert.contract.ContractConvert;
import cn.iocoder.yudao.module.fp.dal.mysql.contract.ContractMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.fp.enums.ErrorCodeConstants.*;

/**
 * 合同 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ContractServiceImpl implements ContractService {

    @Resource
    private ContractMapper contractMapper;

    @Override
    public Long createContract(ContractCreateReqVO createReqVO) {
        // 校验正确性
        validateContractForCreateOrUpdate(null, createReqVO.getName());
        // 插入
        ContractDO contract = ContractConvert.INSTANCE.convert(createReqVO);
        contractMapper.insert(contract);
        // 返回
        return contract.getId();
    }

    @Override
    public void updateContract(ContractUpdateReqVO updateReqVO) {
        // 校验存在
        validateContractForCreateOrUpdate(updateReqVO.getId(),updateReqVO.getName());
        // 更新
        ContractDO updateObj = ContractConvert.INSTANCE.convert(updateReqVO);
        contractMapper.updateById(updateObj);
    }

    @Override
    public void updateContractStatus(Long id, Integer status) {
        // 校验是否可以更新
        //validateContractForUpdate(id);

        // 更新状态
        ContractDO updateObj = new ContractDO().setId(id).setStatus(status);
        contractMapper.updateById(updateObj);
    }

    @Override
    public void deleteContract(Long id) {
        // 校验存在
        validateContractExists(id);
        // 删除
        contractMapper.deleteById(id);
    }

    @Override
    public ContractDO getContract(Long id) {
        return contractMapper.selectById(id);
    }

    @Override
    public List<ContractDO> getContractList(Collection<Long> ids) {
        return contractMapper.selectBatchIds(ids);
    }

    @Override
    public List<ContractDO> getActiveContractList() {
        return contractMapper.selectActiveList();
    }

    @Override
    public PageResult<ContractDO> getContractPage(ContractPageReqVO pageReqVO) {
        return contractMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ContractDO> getContractList(ContractExportReqVO exportReqVO) {
        return contractMapper.selectList(exportReqVO);
    }

    private void validateContractForCreateOrUpdate(Long id, String name) {
        // 校验自己存在
        validateContractExists(id);
        // 校验岗位名的唯一性
        validateContractNameUnique(id, name);

    }

    private void validateContractExists(Long id) {
        if (id == null) {
            return;
        }
        if (contractMapper.selectById(id) == null) {
            throw exception(CONTRACT_NOT_EXISTS);
        }
    }

    private void validateContractNameUnique(Long id, String name) {
        ContractDO contract = contractMapper.selectByName(name);
        if (contract == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的岗位
        if (id == null) {
            throw exception(CONTRACT_DUPLICATE);
        }
        if (!contract.getId().equals(id)) {
            throw exception(CONTRACT_DUPLICATE);
        }
    }

}
