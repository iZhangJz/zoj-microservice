package com.zjz.zojbackendquestionservice.controller;

import cn.hutool.core.util.ObjectUtil;
import com.zjz.common.common.ErrorCode;
import com.zjz.common.exception.BusinessException;
import com.zjz.common.exception.ThrowUtils;
import com.zjz.model.entity.Question;
import com.zjz.zojbackendquestionservice.service.QuestionService;
import com.zjz.zojbackendserviceclient.service.QuestionFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/inner")
@Slf4j
public class QuestionInnerController implements QuestionFeignClient {

    @Resource
    private QuestionService questionService;

    /**
     * 根据 id 获取题目
     * @param id 题目 id
     * @return 题目
     */
    @Override
    public Question getQuestionById(long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        Question question = questionService.getById(id);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return question;
    }

    /**
     * 增加题目提交数量
     * @param questionId 题目 ID
     */
    @Override
    public void addSubmitCount(@RequestBody Long questionId){
        if (ObjectUtil.isNull(questionId)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        questionService.addSubmitCount(questionId);
    }

    /**
     * 增加题目通过数量
     * @param questionId 题目 ID
     */
    @Override
    public void addPassCount(Long questionId) {
        if (ObjectUtil.isNull(questionId) || questionId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        questionService.addPassCount(questionId);
    }




}
