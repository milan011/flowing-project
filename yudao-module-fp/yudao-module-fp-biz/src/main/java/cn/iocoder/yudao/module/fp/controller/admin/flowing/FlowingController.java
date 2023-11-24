package cn.iocoder.yudao.module.fp.controller.admin.flowing;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.fp.dal.dataobject.bankaccount.BankAccountDO;
import cn.iocoder.yudao.module.fp.dal.dataobject.contract.ContractDO;
import cn.iocoder.yudao.module.fp.dal.dataobject.project.ProjectDO;
import cn.iocoder.yudao.module.fp.service.bankaccount.BankAccountService;
import cn.iocoder.yudao.module.fp.service.contract.ContractService;
import cn.iocoder.yudao.module.fp.service.project.ProjectService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;

import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.fp.controller.admin.flowing.vo.*;
import cn.iocoder.yudao.module.fp.dal.dataobject.flowing.FlowingDO;
import cn.iocoder.yudao.module.fp.convert.flowing.FlowingConvert;
import cn.iocoder.yudao.module.fp.service.flowing.FlowingService;

@Tag(name = "管理后台 - 流水明细")
@RestController
@RequestMapping("/fp/flowing")
@Validated
public class FlowingController {

    @Resource
    private FlowingService flowingService;
    
    @Resource
    private BankAccountService accountService;
    
    @Resource
    private ProjectService projectService;
    
    @Resource
    private ContractService contractService;

    @PostMapping("/create")
    @Operation(summary = "创建流水明细")
    @PreAuthorize("@ss.hasPermission('fp:flowing:create')")
    public CommonResult<Long> createFlowing(@Valid @RequestBody FlowingCreateReqVO createReqVO) {
        return success(flowingService.createFlowing(createReqVO));
    }

    @PostMapping("/create-virtually")
    @Operation(summary = "创建流水明细")
    @PreAuthorize("@ss.hasPermission('fp:flowing:create')")
    public CommonResult<Long> createVirtuallyFlowing(@Valid @RequestBody FlowingCreateReqVO createReqVO) {
        return success(flowingService.createVirtuallyFlowing(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新流水明细")
    @PreAuthorize("@ss.hasPermission('fp:flowing:update')")
    public CommonResult<Boolean> updateFlowing(@Valid @RequestBody FlowingUpdateReqVO updateReqVO) {
        flowingService.updateFlowing(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除流水明细")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('fp:flowing:delete')")
    public CommonResult<Boolean> deleteFlowing(@RequestParam("id") Long id) {
        flowingService.deleteFlowing(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得流水明细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('fp:flowing:query')")
    public CommonResult<FlowingRespVO> getFlowing(@RequestParam("id") Long id) {
        FlowingDO flowing = flowingService.getFlowing(id);
        return success(FlowingConvert.INSTANCE.convert(flowing));
    }

    @GetMapping("/list")
    @Operation(summary = "获得流水明细列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('fp:flowing:query')")
    public CommonResult<List<FlowingRespVO>> getFlowingList(@RequestParam("ids") Collection<Long> ids) {
        List<FlowingDO> list = flowingService.getFlowingList(ids);
        return success(FlowingConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得流水明细分页")
    @PreAuthorize("@ss.hasPermission('fp:flowing:query')")
    public CommonResult<PageResult<FlowingRespVO>> getFlowingPage(@Valid FlowingPageReqVO pageVO) {
        PageResult<FlowingDO> pageResult = flowingService.getFlowingPage(pageVO);
    
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(new PageResult<>(pageResult.getTotal())); // 返回空
        }
    
        // 获得拼接需要的数据
        Collection<Long> accIds = convertList(pageResult.getList(), FlowingDO::getAccId);
        Map<Long, BankAccountDO> accountMap = accountService.getAccountMap(accIds);
    
        Collection<Long> conIds = convertList(pageResult.getList(), FlowingDO::getConId);
        Map<Long, ContractDO> contractMap = contractService.getContractMap(conIds);
    
        Collection<Long> proIds = convertList(pageResult.getList(), FlowingDO::getProId);
        Map<Long, ProjectDO> projectMap = projectService.getProjectMap(proIds);
    
        // 拼接结果返回
        List<FlowingRespVO> flowingList = new ArrayList<>(pageResult.getList().size());
    
        /*pageResult.getList().forEach(user -> {
            UserPageItemRespVO respVO = UserConvert.INSTANCE.convert(user);
            respVO.setDept(UserConvert.INSTANCE.convert(deptMap.get(user.getDeptId())));
            userList.add(respVO);
        });*/
    
        pageResult.getList().forEach(flowing -> {
            FlowingRespVO respVO = FlowingConvert.INSTANCE.convert(flowing);
            respVO.setAccount(accountMap.get(flowing.getAccId()));
            respVO.setContract(contractMap.get(flowing.getConId()));
            respVO.setProject(projectMap.get(flowing.getProId()));
            flowingList.add(respVO);
        });
    
        return success(new PageResult<>(flowingList, pageResult.getTotal()));
        
        //PageResult<FlowingRespVO> pageResult = flowingService.getFlowingWithAccountPage(pageVO);

        //return success(FlowingConvert.INSTANCE.convertPage(pageResult));
        //return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出流水明细 Excel")
    @PreAuthorize("@ss.hasPermission('fp:flowing:export')")
    @OperateLog(type = EXPORT)
    public void exportFlowingExcel(@Valid FlowingExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<FlowingDO> list = flowingService.getFlowingList(exportReqVO);
        // 导出 Excel
        List<FlowingExcelVO> datas = FlowingConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "流水明细.xls", "数据", FlowingExcelVO.class, datas);
    }

}
