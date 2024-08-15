package com.zjz.zojbackendjudgeservice.service;


import com.zjz.model.dto.judge.JudgeResultResponse;
import com.zjz.model.entity.QuestionSubmit;

public interface JudgeService {

    JudgeResultResponse doJudge(QuestionSubmit questionSubmit);
}
