package cn.iocoder.yudao.module.fp.controller.admin.bankaccount;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.fp.controller.admin.bankaccount.vo.*;
import cn.iocoder.yudao.module.fp.convert.bankaccount.BankAccountConvert;
import cn.iocoder.yudao.module.fp.dal.dataobject.bankaccount.BankAccountDO;
import cn.iocoder.yudao.module.fp.service.bankaccount.BankAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 银行账户")
@RestController
@RequestMapping("/fp/bank-account")
@Validated
public class BankAccountController {
	
	@Resource
	private BankAccountService bankAccountService;
	
	@PostMapping("/create")
	@Operation(summary = "创建银行账户")
	@PreAuthorize("@ss.hasPermission('fp:bank-account:create')")
	public CommonResult<Long> createBankAccount(@Valid @RequestBody BankAccountCreateReqVO createReqVO) {
		return success(bankAccountService.createBankAccount(createReqVO));
	}
	
	@PutMapping("/update")
	@Operation(summary = "更新银行账户")
	@PreAuthorize("@ss.hasPermission('fp:bank-account:update')")
	public CommonResult<Boolean> updateBankAccount(@Valid @RequestBody BankAccountUpdateReqVO updateReqVO) {
		bankAccountService.updateBankAccount(updateReqVO);
		return success(true);
	}
	
	@DeleteMapping("/delete")
	@Operation(summary = "删除银行账户")
	@Parameter(name = "id", description = "编号", required = true)
	@PreAuthorize("@ss.hasPermission('fp:bank-account:delete')")
	public CommonResult<Boolean> deleteBankAccount(@RequestParam("id") Long id) {
		bankAccountService.deleteBankAccount(id);
		return success(true);
	}
	
	@GetMapping("/get")
	@Operation(summary = "获得银行账户")
	@Parameter(name = "id", description = "编号", required = true, example = "1024")
	@PreAuthorize("@ss.hasPermission('fp:bank-account:query')")
	public CommonResult<BankAccountRespVO> getBankAccount(@RequestParam("id") Long id) {
		BankAccountDO bankAccount = bankAccountService.getBankAccount(id);
		return success(BankAccountConvert.INSTANCE.convert(bankAccount));
	}
	
	@GetMapping("/list")
	@Operation(summary = "获得银行账户列表")
	@Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
	@PreAuthorize("@ss.hasPermission('fp:bank-account:query')")
	public CommonResult<List<BankAccountRespVO>> getBankAccountList(@RequestParam("ids") Collection<Long> ids) {
		List<BankAccountDO> list = bankAccountService.getBankAccountList(ids);
		return success(BankAccountConvert.INSTANCE.convertList(list));
	}
	
	@GetMapping("/page")
	@Operation(summary = "获得银行账户分页")
	@PreAuthorize("@ss.hasPermission('fp:bank-account:query')")
	public CommonResult<PageResult<BankAccountRespVO>> getBankAccountPage(@Valid BankAccountPageReqVO pageVO) {
		PageResult<BankAccountDO> pageResult = bankAccountService.getBankAccountPage(pageVO);
		return success(BankAccountConvert.INSTANCE.convertPage(pageResult));
	}
	
	@GetMapping("/export-excel")
	@Operation(summary = "导出银行账户 Excel")
	@PreAuthorize("@ss.hasPermission('fp:bank-account:export')")
	@OperateLog(type = EXPORT)
	public void exportBankAccountExcel(@Valid BankAccountExportReqVO exportReqVO,
																		 HttpServletResponse response) throws IOException {
		List<BankAccountDO> list = bankAccountService.getBankAccountList(exportReqVO);
		// 导出 Excel
		List<BankAccountExcelVO> datas = BankAccountConvert.INSTANCE.convertList02(list);
		ExcelUtils.write(response, "银行账户.xls", "数据", BankAccountExcelVO.class, datas);
	}
	
}
