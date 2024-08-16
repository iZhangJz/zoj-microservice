package com.zjz.zojbackendserviceclient.service;


import com.zjz.model.entity.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 题目服务
 *
 */
@FeignClient(name = "zoj-question-service", path = "/api/question/inner")
public interface QuestionFeignClient {


    @GetMapping("/get")
    Question getQuestionById(@RequestParam("id") long id);


    @PutMapping("/add/count")
    void addSubmitCount(@RequestParam("questionId") Long questionId);


    @PutMapping("/add/pass")
    void addPassCount(@RequestParam("questionId") Long questionId);
}
