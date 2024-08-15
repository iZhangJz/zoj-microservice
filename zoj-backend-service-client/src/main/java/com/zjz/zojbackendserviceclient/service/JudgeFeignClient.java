package com.zjz.zojbackendserviceclient.service;


import com.zjz.model.dto.judge.JudgeResultResponse;
import com.zjz.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "zoj-judge-service",path = "/api/judge/inner")
public interface JudgeFeignClient {

    /**
     * 调用judge服务进行判题
     * @param questionSubmit 提交的题目
     * @return 判题结果
     */
    @PostMapping("/judge")
    JudgeResultResponse doJudge(@RequestBody QuestionSubmit questionSubmit);
}
