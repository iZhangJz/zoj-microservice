package com.zjz.zojbackendsubmitservice.controller;


import com.zjz.common.common.ErrorCode;
import com.zjz.common.exception.BusinessException;
import com.zjz.model.entity.QuestionSubmit;
import com.zjz.zojbackendserviceclient.service.QuestionSubmitFeignClient;
import com.zjz.zojbackendsubmitservice.service.QuestionSubmitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 题目提交内部接口
 *
 */
@RestController
@RequestMapping("/inner")
@Slf4j
public class QuestionSubmitInnerController implements QuestionSubmitFeignClient {

    @Resource
    private QuestionSubmitService questionSubmitService;


    @Override
    public Boolean updateQuestionSubmit(@RequestBody QuestionSubmit questionSubmit) {
        if (questionSubmit == null || questionSubmit.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = questionSubmitService.updateById(questionSubmit);
        return result;
    }
}
