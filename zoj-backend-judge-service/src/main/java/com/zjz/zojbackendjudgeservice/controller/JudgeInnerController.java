package com.zjz.zojbackendjudgeservice.controller;

import com.zjz.common.common.ErrorCode;
import com.zjz.common.exception.BusinessException;
import com.zjz.model.dto.judge.JudgeResultResponse;
import com.zjz.model.entity.QuestionSubmit;
import com.zjz.zojbackendjudgeservice.service.JudgeService;
import com.zjz.zojbackendserviceclient.service.JudgeFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 判题服务接口
 */
@RestController
@RequestMapping("/inner")
@Slf4j
public class JudgeInnerController implements JudgeFeignClient {

    @Resource
    private JudgeService judgeService;


    @Override
    public JudgeResultResponse doJudge(@RequestBody QuestionSubmit questionSubmit) {
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return judgeService.doJudge(questionSubmit);
    }
}
