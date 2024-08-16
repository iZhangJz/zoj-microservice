package com.zjz.zojbackendjudgeservice.consumer;

import com.zjz.model.dto.judge.JudgeResultResponse;
import com.zjz.model.entity.QuestionSubmit;
import com.zjz.zojbackendjudgeservice.service.JudgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 监听代码提交队列
 */
@Component
@Slf4j
public class CodeSubmitConsumer {

    @Resource
    private JudgeService judgeService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "code.queue",durable = "true"),
            exchange = @Exchange(name = "code.submit.direct")
    ))
    public JudgeResultResponse doJudge(QuestionSubmit questionSubmit){
        log.info("接收到代码提交请求: {}", questionSubmit.getId());
        return judgeService.doJudge(questionSubmit);
    }
}
