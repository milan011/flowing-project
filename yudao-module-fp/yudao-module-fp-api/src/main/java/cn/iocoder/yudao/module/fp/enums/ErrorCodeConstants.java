package cn.iocoder.yudao.module.fp.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Bpm 错误码枚举类
 * <p>
 * bpm 系统，使用 1-009-000-000 段
 */
public interface ErrorCodeConstants {

    ErrorCode BANK_ACCOUNT_NOT_EXISTS = new ErrorCode(1_010_000_001, "银行账户不存在");

    ErrorCode BANK_ACCOUNT_NAME_DUPLICATE = new ErrorCode(1_010_000_002, "已经存在该卡号");
    ErrorCode PROJECT_NOT_EXISTS = new ErrorCode(1_010_001_001, "项目不存在");

    ErrorCode PROJECT_DUPLICATE = new ErrorCode(1_010_001_002, "已经存在该项目");

    ErrorCode CONTRACT_NOT_EXISTS = new ErrorCode(1_010_002_001, "合同不存在");

    ErrorCode CONTRACT_DUPLICATE = new ErrorCode(1_010_002_002, "已经存在该合同");

    ErrorCode FLOWING_NOT_EXISTS = new ErrorCode(1_010_003_001, "流水明细不存在");

    ErrorCode FLOWING_DUPLICATE = new ErrorCode(1_010_003_002, "已经存在该流水");
}
