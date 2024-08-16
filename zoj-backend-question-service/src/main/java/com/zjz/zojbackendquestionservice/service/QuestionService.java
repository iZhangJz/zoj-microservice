package com.zjz.zojbackendquestionservice.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjz.model.dto.question.QuestionQueryRequest;
import com.zjz.model.entity.Question;
import com.zjz.model.vo.QuestionVO;


import javax.servlet.http.HttpServletRequest;

/**
 * 题目服务
 *
 */
public interface QuestionService extends IService<Question> {

    /**
     * 校验数据
     *
     * @param Question
     * @param add 对创建的数据进行校验
     */
    void validQuestion(Question Question, boolean add);

    /**
     * 获取查询条件
     *
     * @param QuestionQueryRequest
     * @return
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest QuestionQueryRequest);
    
    /**
     * 获取题目封装
     *
     * @param Question
     * @param request
     * @return
     */
    QuestionVO getQuestionVO(Question Question, HttpServletRequest request);

    /**
     * 分页获取题目封装
     *
     * @param QuestionPage
     * @param request
     * @return
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> QuestionPage, HttpServletRequest request);

    /**
     * 增加提交次数
     * @param questionId
     */
    void addSubmitCount(Long questionId);

    /**
     * 增加通过次数
     * @param questionId
     */
    void addPassCount(Long questionId);
}
