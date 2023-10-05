package cn.iocoder.yudao.module.fp.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Bpm 错误码枚举类
 * <p>
 * bpm 系统，使用 1-009-000-000 段
 */
public interface ErrorCodeConstants {

    ErrorCode BANK_ACCOUNT_NOT_EXISTS = new ErrorCode(1_010_000_001, "银行账户不存在");
    ErrorCode PROJECT_NOT_EXISTS = new ErrorCode(1_010_000_002, "项目不存在");
}
