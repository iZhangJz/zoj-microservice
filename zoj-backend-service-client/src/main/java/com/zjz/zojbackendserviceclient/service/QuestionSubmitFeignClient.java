package com.zjz.zojbackendserviceclient.service;


import com.zjz.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 题目提交服务
 *
 */
@FeignClient(name = "zoj-submit-service",path = "/api/submit/inner")
public interface QuestionSubmitFeignClient {

    @PutMapping("/update")
    Boolean updateQuestionSubmit(@RequestBody QuestionSubmit questionSubmit);

}
