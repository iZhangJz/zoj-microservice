package com.zjz.zojbackendjudgeservice.service;


import com.zjz.model.dto.judge.JudgeResultResponse;
import com.zjz.model.dto.judge.VerifyContext;

/**
 * 判题策略接口
 */
public interface VerifyStrategyService {

    /**
     * 执行判题
     *
     * @param verifyContext 判题上下文
     * @return
     */
    JudgeResultResponse doVerify(VerifyContext verifyContext);
}
